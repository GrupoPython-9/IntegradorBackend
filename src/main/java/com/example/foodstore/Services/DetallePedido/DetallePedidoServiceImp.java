package com.example.foodstore.Services.DetallePedido;

import com.example.foodstore.Repository.DetallePedidoRepository;
import com.example.foodstore.Repository.ProductoRepository;
import com.example.foodstore.entity.DetallePedido;
import com.example.foodstore.entity.Producto;
import com.example.foodstore.entity.dtos.DetallePedido.DetallePedidoCreate;
import com.example.foodstore.entity.dtos.DetallePedido.DetallePedidoDto;
import com.example.foodstore.entity.dtos.DetallePedido.DetallePedidoEdit;
import com.example.foodstore.entity.dtos.DetallePedido.DetallePedidoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetallePedidoServiceImp implements DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public DetallePedidoDto crear(DetallePedidoCreate d) {
        // Buscar el producto por ID

        
        Producto producto = productoRepository.findById(d.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Crear la entidad sin producto
        DetallePedido detallePedido = DetallePedidoMapper.toEntitySinProducto(d);
        detallePedido.setProducto(producto);

        // Guardar en la base de datos
        detallePedido = detallePedidoRepository.save(detallePedido);

        // Retornar DTO
        return DetallePedidoMapper.toDto(detallePedido);
    }

    @Override
    public DetallePedidoDto actualizar(Long id, DetallePedidoEdit d) {
        Optional<DetallePedido> detallePedidoOptional = detallePedidoRepository.findById(id);

        if (detallePedidoOptional.isPresent()) {
            DetallePedido detallePedido = detallePedidoOptional.get();
            detallePedido.setCantidad(d.getCantidad());

            detallePedido = detallePedidoRepository.save(detallePedido);
            return DetallePedidoMapper.toDto(detallePedido);
        }

        return null;
    }

    @Override
    public DetallePedidoDto buscaId(Long id) {
        Optional<DetallePedido> detallePedidoOptional = detallePedidoRepository.findById(id);
        if (detallePedidoOptional.isPresent()) {
            if (!detallePedidoOptional.get().isEliminado())
                return DetallePedidoMapper.toDto(detallePedidoOptional.get());
        }
        return null;
    }

    @Override
    public List<DetallePedidoDto> buscaTodos() {
        List<DetallePedido> detallePedidos = detallePedidoRepository.findAllByEliminadoFalse();
        return detallePedidos.stream()
                .map(DetallePedidoMapper::toDto)
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        Optional<DetallePedido> detallePedido = detallePedidoRepository.findById(id);
        if (detallePedido.isPresent()) {
            DetallePedido d = detallePedido.get();
            d.setEliminado(true);
            detallePedidoRepository.save(d);
        }
    }
}