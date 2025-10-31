package com.example.foodstore.Repository;


import com.example.foodstore.entity.Producto;
import com.example.foodstore.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findAllByIsEliminadoFalse();
}
