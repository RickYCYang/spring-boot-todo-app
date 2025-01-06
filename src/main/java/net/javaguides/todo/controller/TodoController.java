package net.javaguides.todo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.AllArgsConstructor;

import net.javaguides.todo.dto.TodoDto;
import net.javaguides.todo.service.TodoService;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@AllArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {

    TodoService todoService;

    // http://localhost:8080/api/todo
    @PreAuthorize("hasRole('ADMIN')") // method level security control
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
        TodoDto savedTodoDto = todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedTodoDto, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/todo/1
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')") // method level security control
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long id) {
        TodoDto todoDto = todoService.getTodo(id);
        return ResponseEntity.ok(todoDto);
    }

    // http://localhost:8080/api/todo
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')") // method level security control
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        List<TodoDto> todoDtos = todoService.getTodos();
        return ResponseEntity.ok(todoDtos);
    }

    // http://localhost:8080/api/todo/1
    @PreAuthorize("hasRole('ADMIN')") // method level security control
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable Long id, @RequestBody TodoDto todoDto) {
        TodoDto updateTodoDto = todoService.updateTodo(id, todoDto);
        return ResponseEntity.ok(updateTodoDto);
    }

    // http://localhost:8080/api/todo/1
    @PreAuthorize("hasRole('ADMIN')") // method level security control
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.ok("Todo sucessfully deleted!");
    }

    // http://localhost:8080/api/todo/1
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')") // method level security control
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable Long id) {
        TodoDto todoDto = todoService.completeTodo(id);
        return ResponseEntity.ok(todoDto);
    }

    // http://localhost:8080/api/todo/1
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')") // method level security control
    @PatchMapping("{id}/in-complete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable Long id) {
        TodoDto todoDto = todoService.inCompleteTodo(id);
        return ResponseEntity.ok(todoDto);
    }
}
