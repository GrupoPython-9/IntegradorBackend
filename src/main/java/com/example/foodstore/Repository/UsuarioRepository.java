package com.example.foodstore.Repository;

import com.example.foodstore.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    List<Usuario> findAllByEliminadoFalse();

    // Buscar un usuario por mail (para login)
    Optional<Usuario> findByMail(String mail);

    // Verificar si un mail ya existe (para registro)
    boolean existsByMail(String mail);

}
