package com.example.foodstore.entity.dtos.Producto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class ProductoCreate {


    private String nombre;
    private double precio;
    private String descripcion;
    private int stock;
}
