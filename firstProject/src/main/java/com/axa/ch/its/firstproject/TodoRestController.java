package com.axa.ch.its.firstproject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("todos")
public class TodoRestController {
    private final TodoRepository TodoRepository;

    public TodoRestController(TodoRepository TodoRepository) {
        this.TodoRepository = TodoRepository;
    }
    @GetMapping("")
    public ResponseEntity<List<Todo>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(TodoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Todo>> getUser(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(TodoRepository.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Todo>> deleteUser(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(TodoRepository.deleteById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<Todo>> updateUser(@PathVariable Long id, @RequestBody Todo todo) {
        return ResponseEntity.status(HttpStatus.OK).body(TodoRepository.updateTodo(id, todo));
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Todo> complete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(TodoRepository.complete(id));
    }

    @PatchMapping("/{id}/close")
    public ResponseEntity<Todo> close(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(TodoRepository.close(id));
    }

    @PatchMapping("{id}/deadline")
    public ResponseEntity<Todo> patchDeadline(@PathVariable Long id, @RequestParam String date) {
        LocalDateTime.parse(date);
        Optional<Todo> todoToChange = TodoRepository.updateDeadline(id, LocalDateTime.parse(date));

        return todoToChange.map(todo -> ResponseEntity.status(HttpStatus.OK).body(todo)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @PostMapping("")
    public ResponseEntity<List<Todo>> addTodo(@RequestBody Todo todo) {
        return ResponseEntity.status(HttpStatus.CREATED)  // HTTP 201
                .body(TodoRepository.save(todo));
    }



}
