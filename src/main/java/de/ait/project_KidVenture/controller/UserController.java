package de.ait.project_KidVenture.controller;

import de.ait.project_KidVenture.entity.User;
import de.ait.project_KidVenture.services.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


    @PutMapping("/auth/me")
    public ResponseEntity<User> updateData(Authentication authentication, @RequestBody User updatedUser) {
        User user = userService.update(authentication, updatedUser);
        return ResponseEntity.ok(userService.getUserInfo(authentication));
    }

    @Operation(summary = "Delete by ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
