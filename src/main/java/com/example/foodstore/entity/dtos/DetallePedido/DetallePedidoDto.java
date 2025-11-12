package com.example.foodstore.entity.dtos.DetallePedido;

import com.example.foodstore.entity.dtos.Producto.ProductoDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetallePedidoDto {
    private Long id;
    private int cantidad;
    private double subtotal;
    private ProductoDto producto;
}