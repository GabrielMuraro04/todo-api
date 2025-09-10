package com.seuprojeto.todo_api.controller;

import com.seuprojeto.todo_api.model.Todo;
import com.seuprojeto.todo_api.repository.TodoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    @PostMapping
    public Todo criarTodo(@RequestBody Todo todo) {
        todo.setDataCriacao(LocalDateTime.now());
        return todoRepository.save(todo);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo todoAtualizado) {
        Optional<Todo> todoExistente = todoRepository.findById(id);
        if (todoExistente.isPresent()) {
            Todo todo = todoExistente.get();
            todo.setTitulo(todoAtualizado.getTitulo());
            todo.setDescricao(todoAtualizado.getDescricao());
            todo.setConcluida(todoAtualizado.isConcluida());
            return todoRepository.save(todo);
        } else {
            throw new RuntimeException("Todo com ID " + id + " não encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public String deleteTodo(@PathVariable Long id) {
        if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id);
            return "Todo com ID " + id + " removido com sucesso!";
        } else {
            return "Todo com ID " + id + " não encontrado!";
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        return todoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
