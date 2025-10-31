package com.example.foodstore.Repository;


import com.example.foodstore.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findAllByEliminadoFalse();

    Optional<Producto> findByIdAndEliminadoFalse(Long id);
}
