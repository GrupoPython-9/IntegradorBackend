package com.example.foodstore.Services.Pedido;

import com.example.foodstore.Repository.PedidoRepository;
import com.example.foodstore.Repository.ProductoRepository;
import com.example.foodstore.Repository.DetallePedidoRepository;
import com.example.foodstore.entity.*;
import com.example.foodstore.entity.dtos.DetallePedido.DetallePedidoMapper;
import com.example.foodstore.entity.dtos.Pedido.PedidoCreate;
import com.example.foodstore.entity.dtos.Pedido.PedidoDto;
import com.example.foodstore.entity.dtos.Pedido.PedidoEdit;
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

        // Crear pedido base
        Pedido pedido = PedidoMapper.toEntity(pedidoCreate);
        pedido.setFecha(LocalDate.now());
        pedido.setEstado(Estado.PENDIENTE);

        // Inicializar lista
        List<DetallePedido> detalles = new ArrayList<>();
        double total = 0;

        // Procesar cada detalle recibido
        for (DetallePedidoCreate d : pedidoCreate.getDetallePedidos()) {

            Producto producto = productoRepository.findById(d.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            if (producto.getStock() < d.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para " + producto.getNombre());
            }

            // Crear el detalle SIN asignar pedido (unidireccional)
            DetallePedido detalle = new DetallePedido();
            detalle.setProducto(producto);
            detalle.setCantidad(d.getCantidad());
            detalle.setSubtotal(producto.getPrecio() * d.getCantidad());

            detalles.add(detalle);
            total += detalle.getSubtotal();
        }

        // Asignar detalles y total al pedido
        pedido.setDetallePedidos(detalles);
        pedido.setTotal(total);

        // Guardar SOLO el pedido (cascada guarda detalles)
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

    @Override
    public PedidoDto actualizar(Long id, PedidoEdit pedidoEdit) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        String nuevoEstado = pedidoEdit.getEstado();

        // Validar estados permitidos
        try {
            Estado estadoEnum = Estado.valueOf(nuevoEstado.toUpperCase());
            pedido.setEstado(estadoEnum);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Estado inválido: " + nuevoEstado);
        }

        pedido = pedidoRepository.save(pedido);

        return PedidoMapper.toDto(pedido);
    }

    @Override
    public void eliminar(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        pedidoRepository.delete(pedido);
    }




    //                  CONFIRMAR PEDIDO → RESTAR STOCK
    // ------------------------------------------------------------------
    @Override
    public PedidoDto confirmarPedidoDesc(Long id) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe pedido"));

        // Impedir doble resta
        if (pedido.getEstado() == Estado.CONFIRMADO) {
            throw new IllegalStateException("El pedido ya está confirmado");
        }

        // RESTAR STOCK usando los detalles del pedido en BD
        for (DetallePedido det : pedido.getDetallePedidos()) {

            Producto producto = det.getProducto();
            int cantidad = det.getCantidad();

            if (producto.getStock() < cantidad) {
                throw new IllegalStateException(
                        "Stock insuficiente para el producto: " + producto.getNombre()
                );
            }

            producto.setStock(producto.getStock() - cantidad);
            productoRepository.save(producto);
        }

        pedido.setEstado(Estado.CONFIRMADO);
        pedidoRepository.save(pedido);

        return PedidoMapper.toDto(pedido);
    }

    // ------------------------------------------------------------------
    //                  CANCELAR PEDIDO → DEVOLVER STOCK
    // ------------------------------------------------------------------
    @Override
    public PedidoDto cancelarPedido(Long id) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe pedido"));

        if (pedido.getEstado() == Estado.CANCELADO) {
            throw new IllegalStateException("El pedido ya está cancelado");
        }

        // SOLO devolver stock si antes ya había sido descontado
        if (pedido.getEstado() == Estado.CONFIRMADO) {

            for (DetallePedido det : pedido.getDetallePedidos()) {

                Producto producto = det.getProducto();
                int cantidad = det.getCantidad();

                producto.setStock(producto.getStock() + cantidad);
                productoRepository.save(producto);
            }
        }

        pedido.setEstado(Estado.CANCELADO);
        pedidoRepository.save(pedido);

        return PedidoMapper.toDto(pedido);
    }

   }