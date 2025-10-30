package com.example.foodstore.entity.dtos.Usuario;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class UsuarioCreate {

    private String nombre;
    private String apellido;
    private String mail;
    private String celular;
    private String contrasenia;

    //private List<Long>id_pedidos;
}
