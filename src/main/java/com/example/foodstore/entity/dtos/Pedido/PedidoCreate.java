// PedidoCreate.java
package com.example.foodstore.entity.dtos.Pedido;

import com.example.foodstore.entity.dtos.DetallePedido.DetallePedidoCreate;
import com.example.foodstore.entity.dtos.PedidoInfoEntrega.PedidoInfoEntregaCreate;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PedidoCreate {
    private List<DetallePedidoCreate> detallePedidos;
    //private double total;
    private String estado; // "PENDIENTE"

    private PedidoInfoEntregaCreate infoEntrega; // <--- NUEVO
}