package com.example.foodstore.Repository;

import com.example.foodstore.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findAllByEliminadoFalse();
    //Para ver los eliminados
    List<Categoria> findAllByEliminadoTrue();

    Optional<Categoria> findById(Long id);

    Categoria save (Categoria categoria);

    Optional<Categoria> findByIdAndEliminadoFalse(Long id);

    boolean existsByNombreAndEliminadoFalse(String nombre);

    Optional<Categoria> findByIdAndEliminadoTrue(Long id);

    void delete(Categoria categoria);

}
