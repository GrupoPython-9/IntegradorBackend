package com.example.foodstore.entity.dtos.Producto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductoDto {
    private Long id;

    private String nombre;
    private double precio;
    private String descripcion;
    private int stock;
    private String imagen;



}
