package de.ait.project_KidVenture.controller;

import de.ait.project_KidVenture.services.extraClassPhoto.FileRequest;
import de.ait.project_KidVenture.services.extraClassPhoto.ImageResponse;
import de.ait.project_KidVenture.services.interfaces.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/users")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload-photo")
    public ResponseEntity<ImageResponse> uploadPhoto(@RequestParam("profilePhoto") MultipartFile file) {
        try {
            ImageResponse imageResponse = imageService.uploadPhoto(file);
            return ResponseEntity.ok(imageResponse);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/get-photo/{filename}")
    public ResponseEntity<ImageResponse> getPhoto(@PathVariable String filename) {
        try {
            ImageResponse imageResponse = imageService.getPhoto(filename);
            return ResponseEntity.ok(imageResponse);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete-photo")
    public ResponseEntity<String> deletePhoto(@RequestBody FileRequest request) {
        try {
            imageService.deletePhoto(request);
            return ResponseEntity.ok("Photo deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to delete photo");
        }
    }
}
