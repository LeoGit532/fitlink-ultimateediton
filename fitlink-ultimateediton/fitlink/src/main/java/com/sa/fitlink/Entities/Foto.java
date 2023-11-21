package com.sa.fitlink.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Foto {
    @Id
    private Long id;
    private String link;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
