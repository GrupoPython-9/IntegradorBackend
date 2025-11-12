package com.example.foodstore.entity.dtos.DetallePedido;

import com.example.foodstore.entity.DetallePedido;
import com.example.foodstore.entity.dtos.Producto.ProductoDto;
import com.example.foodstore.entity.dtos.Producto.ProductoMapper;

public class DetallePedidoMapper {

    public static DetallePedidoDto toDto(DetallePedido detallePedido) {
        return DetallePedidoDto.builder()
                .id(detallePedido.getId())
                .cantidad(detallePedido.getCantidad())
                .subtotal(detallePedido.getSubtotal())
                .producto(ProductoMapper.toDto(detallePedido.getProducto()))
                .build();
    }

    //Este metodo ya no se usa porque el Producto se busca en el Service usando productoId
    public static DetallePedido toEntitySinProducto(DetallePedidoCreate dc) {
        DetallePedido detallePedido = new DetallePedido();
        detallePedido.setCantidad(dc.getCantidad());
        detallePedido.setSubtotal(dc.getSubtotal());
        return detallePedido;
    }
}