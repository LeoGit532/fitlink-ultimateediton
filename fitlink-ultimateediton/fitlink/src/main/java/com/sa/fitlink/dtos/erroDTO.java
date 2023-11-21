package com.sa.fitlink.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @Data @NoArgsConstructor
public class erroDTO {
    private String field;
    private String error;
}
