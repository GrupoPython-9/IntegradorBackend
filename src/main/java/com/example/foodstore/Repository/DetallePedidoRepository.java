package com.example.foodstore.Repository;

import com.example.foodstore.entity.DetallePedido;
import com.example.foodstore.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido ,Long> {

    List<DetallePedido> findAllByEliminadoFalse();

    Optional<DetallePedido> findByIdAndEliminadoFalse(Long id);
}
