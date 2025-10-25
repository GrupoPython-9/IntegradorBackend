package com.example.foodstore.entity.dtos;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UsuarioEdit {

    //private String nombre;
    //private String apellido;
    private String mail;
    private String celular;
    private String contrasenia;


}
