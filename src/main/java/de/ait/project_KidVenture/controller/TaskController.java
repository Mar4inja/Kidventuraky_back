package de.ait.project_KidVenture.controller;

import de.ait.project_KidVenture.entity.Task;
import de.ait.project_KidVenture.services.interfaces.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final RestTemplateBuilder restTemplateBuilder;

    @Operation(summary = "Get All tasks")
    @GetMapping("/getAllTasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @Operation(summary = "Get task by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @Operation(summary = "Find task by difficulty. (Low)-(Medium)-(High)")
    @GetMapping("/difficulty")
    public ResponseEntity<List<Task>> getTasksByDifficulty(@RequestParam String difficulty) {
        return ResponseEntity.ok( taskService.searchTaskByDifficulty(difficulty));
    }

    @Operation(summary = "Find task by type (practical)-(theoretical)")
    @GetMapping("/type")
    public ResponseEntity<List<Task>> getTasksByType(@RequestParam String type) {
        return ResponseEntity.ok(taskService.searchByType(type));
    }

    @Operation(summary = "Create new task")
    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @Operation(summary = "Update task")
    @PutMapping("/update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable long id, @RequestBody Task task) {
        task.setId(id);
        return ResponseEntity.ok(taskService.updateTask(task));
    }

    @Operation(summary = "Delete task by ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable long id) {
       taskService.deleteTaskById(id);
       return ResponseEntity.ok("Task deleted successfully");
    }


}
