package de.ait.project_KidVenture.controller;

import de.ait.project_KidVenture.entity.User;
import de.ait.project_KidVenture.services.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get All users")
    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAll() {
        List<User> usersList = userService.getAll();
        return ResponseEntity.ok(usersList);
    }

    @Operation(summary = "Get info about User")
    @GetMapping("/info/{id}")
    public ResponseEntity<User> getInfo(@PathVariable Long id) {
        User userInfo = userService.findById(id).orElse(null);
        return ResponseEntity.ok(userInfo);
    }

    @Operation(summary = "Create new user (Register)")
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody User user) {

        if (userService.emailExists(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }

        User createdUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


    @PutMapping("/auth/me")
    public ResponseEntity<User> updateData(Authentication authentication, @RequestBody User updatedUser) {
        User user = userService.update(authentication, updatedUser);
        return ResponseEntity.ok(userService.getUserInfo(authentication));
    }

    @Operation(summary = "Delete current user (Delete account)")
    @DeleteMapping("/auth/me")
    public ResponseEntity<ErrorResponse> deleteUser(Authentication authentication, HttpServletResponse response) {
        userService.deleteUser(authentication);
        Cookie cookie = new Cookie("Access-Token", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

