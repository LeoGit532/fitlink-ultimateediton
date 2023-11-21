package com.sa.fitlink.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Publicacao {
    @Id
    private Long id;
    private String legenda;
    private String data_criacao;
    private Integer foto_id;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
