package com.example.foodstore.entity.dtos.Categoria;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriaResponse {
    private int estado;
    private String mensaje;
    private Object datos;
}
