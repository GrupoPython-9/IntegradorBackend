package com.example.foodstore.Services;

import com.example.foodstore.entity.Usuario;
import com.example.foodstore.entity.dtos.UsuarioLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.foodstore.Repository.UsuarioRepository;

import java.util.Optional;

@Service
public class AuthServiceImp implements AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario login(UsuarioLoginDto usuarioLoginDto) throws Exception {

        Optional<Usuario> usuarioOptional = usuarioRepository.findByMail(usuarioLoginDto.getMail());

        if (usuarioOptional.isEmpty()) {
            throw new Exception("Usuario no encontrado");
        }

        Usuario usuario = usuarioOptional.get();

        // Por ahora comparación simple de contraseñas (después usar hash)
        if (!usuario.getContrasenia().equals(usuarioLoginDto.getContrasenia())) {
            throw new Exception("Contraseña incorrecta");
        }
        return usuario;
    }
}
