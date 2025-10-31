package com.example.foodstore.Repository;

import com.example.foodstore.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findAllByIsEliminadoFalse();
    //Para ver los eliminados
    List<Categoria> findAllByIsEliminadoTrue();

    Optional<Categoria> findById(Long id);

    Categoria save (Categoria categoria);

    Optional<Categoria> findByIdAndIsEliminadoFalse(Long id);

    boolean existsByNombreAndIsEliminadoFalse(String nombre);

    Optional<Categoria> findByIdAndIsEliminadoTrue(Long id);

    void delete(Categoria categoria);

}
