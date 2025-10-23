package com.example.foodstore.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.IdGeneratorType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String mail;
    private int celular;
    private String contrasenia;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @OneToMany
    @JoinColumn
    @Builder.Default
    private List<Pedido> pedidos = new ArrayList<>();
}
