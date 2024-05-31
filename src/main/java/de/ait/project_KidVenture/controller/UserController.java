package de.ait.project_KidVenture.controller;

import de.ait.project_KidVenture.entity.User;
import de.ait.project_KidVenture.repository.UserRepository;
import de.ait.project_KidVenture.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
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
        User savedUser = userService.save(user);
        return ResponseEntity.ok(savedUser);
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
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
