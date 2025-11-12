package com.example.foodstore.entity.dtos.Pedido;

import com.example.foodstore.entity.Estado;
import com.example.foodstore.entity.Pedido;
import com.example.foodstore.entity.dtos.DetallePedido.DetallePedidoMapper;

import java.time.LocalDate;

public class PedidoMapper {
    public static PedidoDto toDto(Pedido pedido) {
        return PedidoDto.builder()
                .id(pedido.getId())
                .fecha(pedido.getFecha())
                .estado(pedido.getEstado().name())
                .total(pedido.getTotal())
                .detallePedidos(
                        pedido.getDetallePedidos().stream()
                                .map(DetallePedidoMapper::toDto)
                                .toList()
                )
                .build();
    }

    public static Pedido toEntity(PedidoCreate pedidoCreate) {
        Pedido pedido = new Pedido();
        pedido.setEstado(Estado.valueOf(pedidoCreate.getEstado()));
        pedido.setTotal(pedidoCreate.getTotal());
        pedido.setFecha(LocalDate.now()); // o pedidoCreate.getFecha() si lo incluís en el DTO

        // Los DetallePedido se construyen en el servicio, porque necesitan buscar Producto por ID
        // Así que acá no se asignan aún
        return pedido;
    }
}