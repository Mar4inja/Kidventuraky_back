package de.ait.project_KidVenture.security.sec_dto;



import de.ait.project_KidVenture.entity.User;
import lombok.Data;


@Data
public class TokenResponseDto {

    private String accessToken;
    private String refreshToken;
    private User user;


    public TokenResponseDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public TokenResponseDto(String accessToken, String refreshToken, User user) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.user = user;
    }
}