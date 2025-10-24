package com.example.foodstore.entity.dtos;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter


public class UsuarioCreate {


    private String nombre;
    private String apellido;
    private String mail;
    private int celular;
    private String contrasenia;

    private List<Long>id_pedidos;
}
