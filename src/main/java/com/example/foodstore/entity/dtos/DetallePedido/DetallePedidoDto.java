package com.example.foodstore.entity.dtos.DetallePedido;

import com.example.foodstore.entity.Producto;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class DetallePedidoDto {



    private Long id;

    private int cantidad;
    private double subtotal;
    private String producto_nombre;
}
