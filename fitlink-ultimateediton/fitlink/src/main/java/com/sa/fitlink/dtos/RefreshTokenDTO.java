package com.sa.fitlink.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @Data @NoArgsConstructor
public class RefreshTokenDTO {
   @NotBlank
   private String refreshToken;
}

