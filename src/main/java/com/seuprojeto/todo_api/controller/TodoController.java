package com.seuprojeto.todo_api.controller;

import com.seuprojeto.todo_api.dto.TodoResponseDTO;
import com.seuprojeto.todo_api.exception.ResourceNotFoundException;
import com.seuprojeto.todo_api.model.Todo;
import com.seuprojeto.todo_api.repository.TodoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // Converter entidade Todo para DTO
    private TodoResponseDTO toResponseDTO(Todo todo) {
        return new TodoResponseDTO(
                todo.getId(),
                todo.getTitulo(),
                todo.getDescricao(),
                todo.isConcluida(),
                todo.getDataCriacao()
        );
    }

    // Listar todos
    @GetMapping
    public List<TodoResponseDTO> getTodos() {
        return todoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDTO> getTodoById(@PathVariable Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada com id: " + id));
        return ResponseEntity.ok(toResponseDTO(todo));
    }

    // Criar
    @PostMapping
    public ResponseEntity<TodoResponseDTO> createTodo(@Valid @RequestBody Todo todo) {
        Todo savedTodo = todoRepository.save(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDTO(savedTodo));
    }

    // Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDTO> updateTodo(@PathVariable Long id, @Valid @RequestBody Todo todoAtualizado) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada com id: " + id));

        todo.setTitulo(todoAtualizado.getTitulo());
        todo.setDescricao(todoAtualizado.getDescricao());
        todo.setConcluida(todoAtualizado.isConcluida());

        Todo updated = todoRepository.save(todo);
        return ResponseEntity.ok(toResponseDTO(updated));
    }

    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada com id: " + id));
        todoRepository.delete(todo);
        return ResponseEntity.noContent().build();
    }

    // Filtrar por palavra no título
    @GetMapping("/filtro/titulo")
    public List<TodoResponseDTO> filtrarPorTitulo(@RequestParam String palavra) {
        return todoRepository.findByTituloContaining(palavra)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
