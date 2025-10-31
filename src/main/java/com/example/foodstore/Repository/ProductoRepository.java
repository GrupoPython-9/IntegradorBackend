package com.example.foodstore.Repository;


import com.example.foodstore.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    //findByIdAndEliminadoFalse
}
