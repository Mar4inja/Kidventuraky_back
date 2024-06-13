package de.ait.project_KidVenture.security.sec_dto;



import de.ait.project_KidVenture.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class TokenResponseDto {

    private String accessToken;
    private String refreshToken;
    private User user;


    public TokenResponseDto(Object accessToken, Object refreshToken) {
    }
}
