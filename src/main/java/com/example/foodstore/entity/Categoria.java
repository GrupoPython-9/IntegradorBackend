package com.example.foodstore.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Categoria extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;

    @OneToMany(cascade = CascadeType.ALL) //Todas las operaciones que hagamos se aplican a productos asociados
    @JoinColumn(name = "categoria_id") //Es para guardar la relacion
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto producto) {
        if (!productos.contains(producto)) {
            productos.add(producto);
        }
    }
}