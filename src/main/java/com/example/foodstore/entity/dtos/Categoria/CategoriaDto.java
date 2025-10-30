package com.example.foodstore.entity.dtos.Categoria;

import com.example.foodstore.entity.Producto;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private List<Producto> productos;
}
