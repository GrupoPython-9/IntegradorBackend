package com.example.foodstore.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Consultar
    @Builder.Default
    private LocalDate fecha = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private Estado estado;
    private double total;

    @OneToMany
    @JoinColumn
    @Builder.Default
    private List<DetallePedido> detallePedidos = new ArrayList<>();

    //InfoPedido
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "info_entrega_id")
    private PedidoInfoEntrega infoEntrega;

}
