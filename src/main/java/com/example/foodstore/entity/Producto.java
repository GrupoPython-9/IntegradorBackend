package com.example.foodstore.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Producto extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private double precio;
    private String descripcion;
    private int stock;
    private String imagen;

    @OneToMany(cascade = CascadeType.ALL) //Todas las operaciones que hagamos se aplican a productos asociados
    @JoinColumn(name = "poducto_id")

    g

    public void agregarDetalleProducto(DetallePedido detallePedido) {
        if (!detallePedidos.contains(detallePedido)) {
            detallePedidos.add(detallePedido);
        }
    }




    public int actualizarStock() {
        if (detallePedidos != null && !detallePedidos.isEmpty()) {
            stock -= detallePedidos.last().getCantidad();
        }
        return stock;
    }






}
