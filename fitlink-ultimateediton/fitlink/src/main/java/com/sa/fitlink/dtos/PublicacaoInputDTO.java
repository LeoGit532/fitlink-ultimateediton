package com.sa.fitlink.dtos;

import com.sa.fitlink.Entities.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class PublicacaoInputDTO {
  
    private Long id;
    @NotBlank
    private String data_criacao;
    @NotNull
    private Usuario usuario;
}
