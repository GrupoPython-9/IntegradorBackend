package com.example.foodstore.entity.dtos.Categoria;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CategoriaEdit {
    private String nombre;
    private String descripcion;
    private String imagen;
}
