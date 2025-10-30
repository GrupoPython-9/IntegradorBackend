package com.example.foodstore.controller;

import com.example.foodstore.Services.AuthService;
import com.example.foodstore.entity.Usuario;
import com.example.foodstore.entity.dtos.Usuario.UsuarioLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(
        origins = "http://localhost:5173",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE},
        allowedHeaders = "*"
)
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioLoginDto usuarioLoginDto) {
        try {
            // servicio de autenticación
            Usuario usuario = authService.login(usuarioLoginDto);

            // Devuelve el usuario
            return ResponseEntity.ok().body(usuario);

        } catch (Exception e) {
            // Maneja errores de login (usuario no encontrado o contraseña incorrecta)
            return ResponseEntity.badRequest().body("Ocurrió un error: " + e.getMessage());
        }
    }
}

