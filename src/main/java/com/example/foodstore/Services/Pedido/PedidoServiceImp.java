package com.example.foodstore.Services.Pedido;

import com.example.foodstore.Repository.PedidoRepository;
import com.example.foodstore.Repository.ProductoRepository;
import com.example.foodstore.Repository.DetallePedidoRepository;
import com.example.foodstore.entity.*;
import com.example.foodstore.entity.dtos.DetallePedido.DetallePedidoMapper;
import com.example.foodstore.entity.dtos.Pedido.PedidoCreate;
import com.example.foodstore.entity.dtos.Pedido.PedidoDto;
import com.example.foodstore.entity.dtos.Pedido.PedidoMapper;
import com.example.foodstore.entity.dtos.DetallePedido.DetallePedidoCreate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServiceImp implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Override
    public PedidoDto crear(PedidoCreate pedidoCreate) {
        System.out.println("Pedido recibido");
        System.out.println(pedidoCreate.toString());
        // Usamos el mapper para construir el Pedido base
        Pedido pedido = PedidoMapper.toEntity(pedidoCreate);
        pedido.setFecha(LocalDate.now()); // Si no lo incluís en el DTO

        List<DetallePedido> detalles = new ArrayList<>();
        double total = 0;

        for (DetallePedidoCreate d : pedidoCreate.getDetallePedidos()) {
            Producto producto = productoRepository.findById(d.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            // Usamos el mapper para construir el DetallePedido sin producto
            DetallePedido detalle = DetallePedidoMapper.toEntitySinProducto(d);
            detalle.setProducto(producto);

            detalle = detallePedidoRepository.save(detalle);
            detalles.add(detalle);
            total += detalle.getSubtotal();
        }

        pedido.setDetallePedidos(detalles);
        pedido.setTotal(total);

        pedido = pedidoRepository.save(pedido);
        return PedidoMapper.toDto(pedido);
    }

    @Override
    public List<PedidoDto> buscaTodos() {
        return pedidoRepository.findAll().stream()
                .map(PedidoMapper::toDto)
                .toList();
    }

    @Override
    public PedidoDto buscaId(Long id) {
        return pedidoRepository.findById(id)
                .map(PedidoMapper::toDto)
                .orElse(null);
    }
}