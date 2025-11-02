package com.example.foodstore.Services.Auth;

import com.example.foodstore.entity.Usuario;
import com.example.foodstore.entity.dtos.Usuario.UsuarioDto;
import com.example.foodstore.entity.dtos.Usuario.UsuarioLoginDto;

public interface AuthService {
    UsuarioDto login(UsuarioLoginDto usuarioLoginDto) throws Exception;
}
