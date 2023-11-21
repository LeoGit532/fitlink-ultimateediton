package com.sa.fitlink.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Amigo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String data_amizade;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
