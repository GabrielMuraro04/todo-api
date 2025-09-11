package com.seuprojeto.todo_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TodoRequestDTO {
    @NotBlank(message = "O título não pode ser vazio")
    private String titulo;
    private String descricao;
    private boolean concluida = false;

}
