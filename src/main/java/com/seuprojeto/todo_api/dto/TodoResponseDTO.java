package com.seuprojeto.todo_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TodoResponseDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private boolean concluida;
    private LocalDateTime dataCriacao;

}
