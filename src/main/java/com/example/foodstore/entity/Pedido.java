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

    @Builder.Default
    private LocalDate fecha = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private Estado estado;

    private double total;

    // -------------------------------
    //   RELACIÓN UNIDIRECCIONAL 1:N
    // -------------------------------
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "pedido_id")  // FK en tabla DETALLEPEDIDO
    @Builder.Default
    private List<DetallePedido> detallePedidos = new ArrayList<>();


    // -------------------------------
    //       INFO DE ENTREGA
    // -------------------------------
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "info_entrega_id")
    private PedidoInfoEntrega infoEntrega;


    // -------------------------------
    //     CÁLCULO DEL TOTAL
    // -------------------------------
    public double calcularTotal() {
        this.total = 0;
        for (DetallePedido detalle : detallePedidos) {
            this.total += detalle.getSubtotal();
        }
        return this.total;
    }

}
