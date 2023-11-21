package com.sa.fitlink.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Comentario {
    @Id
    private Long id;
    private String comentario;
    private String data_criacao;
    private Integer conta_id;
    private Integer publicacao;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
  
}
