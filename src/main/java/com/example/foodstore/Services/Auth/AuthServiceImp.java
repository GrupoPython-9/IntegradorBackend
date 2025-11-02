package com.example.foodstore.Services.Auth;

import com.example.foodstore.Utils.HashUtil;
import com.example.foodstore.entity.Usuario;
import com.example.foodstore.entity.dtos.Usuario.UsuarioDto;
import com.example.foodstore.entity.dtos.Usuario.UsuarioLoginDto;
import com.example.foodstore.entity.dtos.Usuario.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.foodstore.Repository.UsuarioRepository;

import java.util.Optional;

@Service
public class AuthServiceImp implements AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDto login(UsuarioLoginDto usuarioLoginDto) throws Exception {

        // Validación del correo
        if (usuarioLoginDto.getMail() == null || usuarioLoginDto.getMail().isBlank()) {
            throw new Exception("Correo inválido");
        }

        // Buscar usuario por mail
        Optional<Usuario> usuarioOptional = usuarioRepository.findByMail(usuarioLoginDto.getMail());

        if (usuarioOptional.isEmpty()) {
            throw new Exception("Usuario no encontrado");
        }

        Usuario usuario = usuarioOptional.get();

        if (!usuario.getContrasenia().equals(HashUtil.sha256(usuarioLoginDto.getContrasenia()))) {
            throw new Exception("Contraseña incorrecta");
        }

        return UsuarioMapper.toDTo(usuario);
    }
}
