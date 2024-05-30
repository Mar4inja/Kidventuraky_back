package de.ait.project_KidVenture.controller;

import de.ait.project_KidVenture.entity.Kid;
import de.ait.project_KidVenture.services.KidService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/kids")
@RequiredArgsConstructor
public class KidController {


    private final KidService kidService;


    @Operation(summary = "Get All kids")
    @GetMapping("/getAll")
    public ResponseEntity<List<Kid>> getAll() {
        List<Kid> kidsList = kidService.getAll();
        return ResponseEntity.ok(kidsList);
    }

    @Operation(summary = "Delete by ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Kid> deleteById(@PathVariable Long id) {
        kidService.delete(id);
        return ResponseEntity.ok().build();
    }
}
