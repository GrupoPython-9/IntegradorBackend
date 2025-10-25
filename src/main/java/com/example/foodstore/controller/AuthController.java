package com.example.foodstore.controller;

import com.example.foodstore.Services.AuthService;
import com.example.foodstore.entity.Usuario;
import com.example.foodstore.entity.dtos.UsuarioLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioLoginDto usuarioLoginDto) {
        try {
            // Llama al servicio de autenticación
            Usuario usuario = authService.login(usuarioLoginDto);

            // Devuelve el usuario o un token si luego implementas JWT
            return ResponseEntity.ok().body(usuario);

        } catch (Exception e) {
            // Maneja errores de login (usuario no encontrado o contraseña incorrecta)
            return ResponseEntity.badRequest().body("Ocurrió un error: " + e.getMessage());
        }
    }
}

