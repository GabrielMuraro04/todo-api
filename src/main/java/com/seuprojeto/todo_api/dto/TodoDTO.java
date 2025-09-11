package com.seuprojeto.todo_api.dto;

import com.seuprojeto.todo_api.model.Todo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private boolean concluida;
    private LocalDateTime dataCriacao;

    // Construtor que recebe a entidade Todo
    public TodoDTO(Todo todo) {
        this.id = todo.getId();
        this.titulo = todo.getTitulo();
        this.descricao = todo.getDescricao();
        this.concluida = todo.isConcluida();
        this.dataCriacao = todo.getDataCriacao();
    }
}
