package com.seuprojeto.todo_api.dto;

import jakarta.validation.constraints.NotBlank;

// DTO para expor apenas os campos necessários da entidade Todo
public class TodoDTO {

    private Long id;

    @NotBlank(message = "O título não pode estar vazio")
    private String titulo;

    private String descricao;

    private boolean concluida;

    // Construtor padrão
    public TodoDTO() {
    }

    // Construtor com campos
    public TodoDTO(Long id, String titulo, String descricao, boolean concluida) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluida = concluida;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }
}
