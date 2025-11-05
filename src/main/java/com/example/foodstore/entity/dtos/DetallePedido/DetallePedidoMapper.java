package com.example.foodstore.entity.dtos.DetallePedido;

import com.example.foodstore.entity.DetallePedido;
import com.example.foodstore.entity.Producto;
import com.example.foodstore.entity.dtos.Producto.ProductoCreate;
import com.example.foodstore.entity.dtos.Producto.ProductoDto;
import lombok.*;



public class DetallePedidoMapper {


    public static DetallePedidoDto toDto (DetallePedido detallePedido){

        DetallePedidoDto detallePedidoDto=new DetallePedidoDto.DetallePedidoDtoBuilder()
                .id(detallePedido.getId())
                .cantidad(detallePedido.getCantidad())
                .subtotal(detallePedido.obtenerSubtotal()) //ver si esta bien
                .producto_nombre(detallePedido.getProducto().getNombre())
                .build();
        return detallePedidoDto;
    }

    public static DetallePedido toEntity(DetallePedidoCreate dc){
        DetallePedido detallePedido = new DetallePedido();
        detallePedido.setCantidad(dc.getCantidad());
        detallePedido.setProducto(dc.getProducto());
        detallePedido.setSubtotal(dc.obtenerSubtotal());///ver si esta bien




        return detallePedido;
    }



}
