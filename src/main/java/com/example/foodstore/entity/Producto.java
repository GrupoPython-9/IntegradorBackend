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

    //@OneToMany(cascade = CascadeType.ALL) //Todas las operaciones que hagamos se aplican a productos asociados
    //@JoinColumn(name = "producto_id")
    //Lo dejo comentado para marcar la diferencia ↑ ↓
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)//Corregí el @JoinColumn ya que va del lado @ManyToOne en DetallePedido
    private List<DetallePedido> detallePedidos = new ArrayList<>();

    //g

    public void agregarDetalleProducto(DetallePedido detallePedido) {
        if (!detallePedidos.contains(detallePedido)) {
            detallePedidos.add(detallePedido);
        }
    }

    public int actualizarStock() {
        if (detallePedidos != null && !detallePedidos.isEmpty()) {
            int cantidad = detallePedidos.get(detallePedidos.size() - 1).getCantidad();
            if (stock >= cantidad) {
                stock -= cantidad;
            } else {
                throw new IllegalStateException("Stock insuficiente");
            }
        }
        return stock;
    }

}
