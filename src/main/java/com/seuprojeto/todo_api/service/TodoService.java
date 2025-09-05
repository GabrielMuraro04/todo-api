package com.seuprojeto.todo_api.service;

import com.seuprojeto.todo_api.model.Todo;
import com.seuprojeto.todo_api.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // Criar/Atualizar tarefa
    public Todo salvar(Todo todo) {
        return todoRepository.save(todo);
    }

    // Buscar todas as tarefas
    public List<Todo> listarTodos() {
        return todoRepository.findAll();
    }

    // Buscar tarefa por ID
    public Optional<Todo> buscarPorId(Long id) {
        return todoRepository.findById(id);
    }

    // Deletar tarefa por ID
    public void deletar(Long id) {
        todoRepository.deleteById(id);
    }
}
