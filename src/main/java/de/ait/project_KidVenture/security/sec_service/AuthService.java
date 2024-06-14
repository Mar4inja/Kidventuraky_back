package de.ait.project_KidVenture.security.sec_service;

import de.ait.project_KidVenture.entity.User;
import de.ait.project_KidVenture.security.sec_dto.TokenResponseDto;
import de.ait.project_KidVenture.services.interfaces.UserService;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final UserService userService;
    private final TokenService tokenService;
    private final Map<String, String> refreshStorage;
    private final BCryptPasswordEncoder encoder;

    public AuthService(BCryptPasswordEncoder encoder, TokenService tokenService, UserService userService) {
        this.encoder = encoder;
        this.tokenService = tokenService;
        this.userService = userService;
        this.refreshStorage = new HashMap<>();
    }

    public TokenResponseDto login(@NonNull User inboundUser) throws AuthException {
        String username = inboundUser.getEmail();
        logger.info("Attempting to log in user: {}", username);
        User foundUser = userService.findByEmail(username);

        if (foundUser == null) {
            logger.warn("User not found: {}", username);
            throw new AuthException("User not found");
        }

        if (!isRegistrationConfirmed(foundUser)) {
            logger.warn("E-mail confirmation not completed for user: {}", username);
            throw new AuthException("E-mail confirmation was not completed");
        }

        if (encoder.matches(inboundUser.getPassword(), foundUser.getPassword())) {
            String accessToken = tokenService.generateAccessToken(foundUser);
            String refreshToken = tokenService.generateRefreshToken(foundUser);
            refreshStorage.put(username, refreshToken);
            logger.info("Login successful for user: {}", username);
            return new TokenResponseDto(accessToken, refreshToken, foundUser);
        } else {
            logger.warn("Incorrect password for user: {}", username);
            throw new AuthException("Password is incorrect");
        }
    }

    public TokenResponseDto getAccessToken(@NonNull String inboundRefreshToken) {
        logger.info("Generating access token using refresh token");
        Claims refreshClaims = tokenService.getRefreshClaims(inboundRefreshToken);
        String username = refreshClaims.getSubject();
        String savedRefreshToken = refreshStorage.get(username);

        if (inboundRefreshToken.equals(savedRefreshToken)) {
            User user = userService.findByEmail(username);
            String accessToken = tokenService.generateAccessToken(user);
            logger.info("Access token generated successfully for user: {}", username);
            return new TokenResponseDto(accessToken, null);
        }
        logger.warn("Invalid refresh token for user: {}", username);
        return new TokenResponseDto(null, null);
    }

    private boolean isRegistrationConfirmed(User user) {
        boolean isConfirmed = user.isActive();
        if (!isConfirmed) {
            logger.warn("Registration not confirmed for user: {}", user.getEmail());
        }
        return isConfirmed;
    }
}
