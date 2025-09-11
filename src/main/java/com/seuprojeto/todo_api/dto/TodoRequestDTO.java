package com.seuprojeto.todo_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequestDTO {

    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    private String descricao;

    private boolean concluida = false;
}
