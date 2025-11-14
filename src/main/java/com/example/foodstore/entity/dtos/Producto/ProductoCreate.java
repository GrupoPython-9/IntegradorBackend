package com.example.foodstore.entity.dtos.Producto;

import com.example.foodstore.entity.DetallePedido;
import lombok.*;

import java.util.Comparator;
import java.util.TreeSet;

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
    private String imagen;

    /*@Builder.Default
    private TreeSet<DetallePedido> detallePedidos= new TreeSet<>(Comparator.comparingLong(DetallePedido::getId));

    public int actualizarStock() {
        if (detallePedidos != null && !detallePedidos.isEmpty()) {
            stock -= detallePedidos.last().getCantidad();
        }
        return stock;
    }*/




}
