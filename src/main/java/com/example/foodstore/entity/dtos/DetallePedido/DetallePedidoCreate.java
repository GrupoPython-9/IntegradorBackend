package com.example.foodstore.entity.dtos.DetallePedido;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DetallePedidoCreate {
    private int cantidad;
    //private double subtotal;
    private Long productoId;
}