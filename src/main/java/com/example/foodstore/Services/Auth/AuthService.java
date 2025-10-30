package com.example.foodstore.Services.Auth;

import com.example.foodstore.entity.Usuario;
import com.example.foodstore.entity.dtos.Usuario.UsuarioLoginDto;

public interface AuthService {
    Usuario login(UsuarioLoginDto usuarioLoginDto) throws Exception;
}
