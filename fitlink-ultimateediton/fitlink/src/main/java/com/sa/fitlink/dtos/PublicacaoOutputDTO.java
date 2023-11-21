package com.sa.fitlink.dtos;

import com.sa.fitlink.Entities.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class PublicacaoOutputDTO {

    private Long id;
    private String data_criacao;
    private Usuario usuario;
}
