package com.example.foodstore.entity.dtos;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class UsuarioDto {

    private Long id;
    private String nombre;
    private String apellido;
    private String mail;
    private int celular;
    private String contrasenia;

    private List<String> pedidos_nombres;



}
