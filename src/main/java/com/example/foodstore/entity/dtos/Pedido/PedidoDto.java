package com.example.foodstore.entity.dtos.Pedido;

import com.example.foodstore.entity.PedidoInfoEntrega;
import com.example.foodstore.entity.dtos.DetallePedido.DetallePedidoDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoDto {
    private Long id;
    private LocalDate fecha;
    private String estado;
    private double total;
    private List<DetallePedidoDto> detallePedidos;
    private PedidoInfoEntrega infoEntrega;
}