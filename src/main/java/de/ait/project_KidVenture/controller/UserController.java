package de.ait.project_KidVenture.controller;

import de.ait.project_KidVenture.entity.User;
import de.ait.project_KidVenture.services.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

    @Operation(summary = "Save user")
    @PostMapping("/register")
    public ResponseEntity<User> save(@RequestBody User user) {
        try {
            User savedUser = userService.saveUser(user);
            return ResponseEntity.ok(savedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // или другой статус в зависимости от ошибки
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // конфликт, например, уже существующий email
        }
    }

    @Operation(summary = "Update data")
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateData(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = userService.update(id, updatedUser);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Delete by ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
