package com.seuprojeto.todo_api.controller;

import com.seuprojeto.todo_api.model.Todo;
import com.seuprojeto.todo_api.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // Criar nova tarefa
    @PostMapping
    public Todo criar(@RequestBody Todo todo) {
        return todoService.salvar(todo);
    }

    // Listar todas as tarefas
    @GetMapping
    public List<Todo> listar() {
        return todoService.listarTodos();
    }

    // Buscar tarefa por ID
    @GetMapping("/{id}")
    public ResponseEntity<Todo> buscar(@PathVariable Long id) {
        Optional<Todo> tarefa = todoService.buscarPorId(id);
        return tarefa.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Atualizar tarefa
    @PutMapping("/{id}")
    public ResponseEntity<Todo> atualizar(@PathVariable Long id, @RequestBody Todo todoAtualizado) {
        Optional<Todo> tarefaExistente = todoService.buscarPorId(id);
        if (tarefaExistente.isPresent()) {
            Todo t = tarefaExistente.get();
            t.setTitulo(todoAtualizado.getTitulo());
            t.setDescricao(todoAtualizado.getDescricao());
            t.setConcluida(todoAtualizado.isConcluida());
            return ResponseEntity.ok(todoService.salvar(t));
        }
        return ResponseEntity.notFound().build();
    }

    // Deletar tarefa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Optional<Todo> tarefaExistente = todoService.buscarPorId(id);
        if (tarefaExistente.isPresent()) {
            todoService.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
