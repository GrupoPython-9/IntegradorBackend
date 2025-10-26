package com.example.foodstore.entity.dtos;

import com.example.foodstore.entity.Rol;
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
    private String celular;
    private Rol rol; //Para el front





}
