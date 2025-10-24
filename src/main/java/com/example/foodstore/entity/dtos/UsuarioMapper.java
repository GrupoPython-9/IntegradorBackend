package com.example.foodstore.entity.dtos;

import com.example.foodstore.entity.Pedido;
import com.example.foodstore.entity.Usuario;

import java.util.stream.Collectors;

public class UsuarioMapper {

    //Obtenemos datos a traves de la base de datos


   //Usar  en el login
    public static UsuarioDto toDTo(Usuario usuario){
        UsuarioDto usuarioDto = new UsuarioDto.UsuarioDtoBuilder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .mail(usuario.getMail())
                .celular(usuario.getCelular())
                .contrasenia(usuario.getContrasenia())
                .pedidos_nombres (usuario.getPedidos()!= null
                        ?  usuario.getPedidos().stream()
                        .map(pedido -> "Pedido #" + pedido.getId()) // 👈 ejemplo: mostrar ID del pedido
                        .collect(Collectors.toList())
                        : null)

                .build();
        return usuarioDto;
    }               //Utilizar en el registro
       public static Usuario toEntity(UsuarioCreate usuarioCreate){
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioCreate.getNombre());
        usuario.setApellido(usuarioCreate.getApellido());
        usuario.setMail(usuarioCreate.getMail());
        usuario.setCelular(usuarioCreate.getCelular());
        usuario.setContrasenia(usuarioCreate.getContrasenia());
        return usuario;
       }






}
