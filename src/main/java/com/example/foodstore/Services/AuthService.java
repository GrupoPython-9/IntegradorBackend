package com.example.foodstore.Services;

import com.example.foodstore.entity.Usuario;
import com.example.foodstore.entity.dtos.UsuarioLoginDto;

public interface AuthService {
    Usuario login(UsuarioLoginDto usuarioLoginDto) throws Exception;
}
