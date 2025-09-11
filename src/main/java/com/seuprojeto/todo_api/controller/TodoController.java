package com.seuprojeto.todo_api.controller;

import com.seuprojeto.todo_api.dto.TodoDTO;
import com.seuprojeto.todo_api.dto.TodoRequestDTO;
import com.seuprojeto.todo_api.exception.ResourceNotFoundException;
import com.seuprojeto.todo_api.model.Todo;
import com.seuprojeto.todo_api.repository.TodoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // Listar todos
    @GetMapping
    public List<TodoDTO> getTodos() {
        return todoRepository.findAll()
                .stream()
                .map(TodoDTO::new) // converte cada Todo em TodoDTO
                .toList();
    }

    // Buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<TodoDTO> getTodoById(@PathVariable Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada com id: " + id));
        return ResponseEntity.ok(new TodoDTO(todo));
    }

    // Criar
    @PostMapping
    public ResponseEntity<TodoDTO> createTodo(@Valid @RequestBody TodoRequestDTO dto) {
        Todo todo = new Todo();
        todo.setTitulo(dto.getTitulo());
        todo.setDescricao(dto.getDescricao());
        todo.setConcluida(dto.isConcluida());

        Todo savedTodo = todoRepository.save(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(new TodoDTO(savedTodo));
    }

    // Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<TodoDTO> updateTodo(@PathVariable Long id, @Valid @RequestBody TodoRequestDTO dto) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada com id: " + id));

        todo.setTitulo(dto.getTitulo());
        todo.setDescricao(dto.getDescricao());
        todo.setConcluida(dto.isConcluida());

        Todo updatedTodo = todoRepository.save(todo);
        return ResponseEntity.ok(new TodoDTO(updatedTodo));
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
    public List<TodoDTO> filtrarPorTitulo(@RequestParam String palavra) {
        return todoRepository.findByTituloContaining(palavra)
                .stream()
                .map(TodoDTO::new)
                .toList();
    }
}