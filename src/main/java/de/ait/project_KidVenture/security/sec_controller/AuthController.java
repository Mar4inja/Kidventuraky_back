package de.ait.project_KidVenture.security.sec_controller;
import de.ait.project_KidVenture.entity.User;
import de.ait.project_KidVenture.repository.UserRepository;
import de.ait.project_KidVenture.security.sec_dto.RefreshRequestDto;
import de.ait.project_KidVenture.security.sec_dto.TokenResponseDto;
import de.ait.project_KidVenture.security.sec_service.AuthService;
import jakarta.security.auth.message.AuthException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class AuthController {

    private final AuthService service;
    private final UserRepository userRepo;

    public AuthController(AuthService service, UserRepository userRepo) {
        this.service = service;
        this.userRepo = userRepo;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user, HttpServletResponse response) throws AuthException {

        TokenResponseDto tokenDto = service.login(user);

        Cookie accessTokenCookie = new Cookie("Access-Token", tokenDto.getAccessToken());
        accessTokenCookie.setPath("/");
        accessTokenCookie.setHttpOnly(true);
        response.addCookie(accessTokenCookie);

        return ResponseEntity.ok(tokenDto);

    }

    @PostMapping("/access")
    public ResponseEntity<Object> getNewAccessToken(@RequestBody RefreshRequestDto request, HttpServletResponse response) {
        try {
            TokenResponseDto tokenDto = service.getAccessToken(request.getRefreshToken());
            Cookie cookie = new Cookie("Access-Token", tokenDto.getAccessToken());
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return ResponseEntity.ok(tokenDto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> Logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("Access-Token", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok("Logout successful");
    }
}