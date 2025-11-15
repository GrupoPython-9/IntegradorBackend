package com.example.foodstore.entity.dtos.Pedido;

import com.example.foodstore.entity.Estado;
import com.example.foodstore.entity.Pedido;
import com.example.foodstore.entity.PedidoInfoEntrega;
import com.example.foodstore.entity.dtos.DetallePedido.DetallePedidoMapper;

import java.time.LocalDate;

public class PedidoMapper {
    public static PedidoDto toDto(Pedido pedido) {
        return PedidoDto.builder()
                .id(pedido.getId())
                .fecha(pedido.getFecha())
                .estado(pedido.getEstado().name())
                .total(pedido.getTotal())
                .infoEntrega(pedido.getInfoEntrega())
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
        //pedido.setTotal(pedidoCreate.getTotal());
        pedido.setFecha(LocalDate.now()); // o pedidoCreate.getFecha() si lo incluís en el DTO

        // Los DetallePedido se construyen en el servicio, porque necesitan buscar Producto por ID
        // Así que acá no se asignan aún
        //Nuevo
        if (pedidoCreate.getInfoEntrega() != null) {

            PedidoInfoEntrega info = PedidoInfoEntrega.builder()
                    .direccion(pedidoCreate.getInfoEntrega().getDireccion())
                    .telefono(pedidoCreate.getInfoEntrega().getTelefono())
                    .metodoPago(pedidoCreate.getInfoEntrega().getMetodoPago())
                    .nota(pedidoCreate.getInfoEntrega().getNota())
                    .build();

            pedido.setInfoEntrega(info);
        }
        //_>
        return pedido;
    }
}