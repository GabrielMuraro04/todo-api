package com.seuprojeto.todo_api.exception;

public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException(Long id) {
        super("Todo com id " + id + " não encontrado");
    }
}