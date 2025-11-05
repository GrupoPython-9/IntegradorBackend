package com.example.foodstore.entity.dtos.DetallePedido;

import com.example.foodstore.entity.Producto;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class DetallePedidoCreate {


    private int cantidad;
    private double subtotal;
    private Producto producto;

    public double obtenerSubtotal(){

        this.subtotal= cantidad* producto.getPrecio();
        return subtotal;
    }
}
