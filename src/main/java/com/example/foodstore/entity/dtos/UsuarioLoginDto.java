package com.example.foodstore.entity.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioLoginDto {
    private String mail;
    private String contrasenia;
}
