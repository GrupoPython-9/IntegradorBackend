package com.example.foodstore.entity.dtos.Usuario;

import com.example.foodstore.entity.Rol;
import com.example.foodstore.entity.Usuario;

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
                .rol(usuario.getRol())
                .build();
        return usuarioDto;
    }
    //Utilizar en el registro
    public static Usuario toEntity(UsuarioCreate usuarioCreate){
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioCreate.getNombre());
        usuario.setApellido(usuarioCreate.getApellido());
        usuario.setMail(usuarioCreate.getMail());
        usuario.setCelular(usuarioCreate.getCelular());
        usuario.setContrasenia(usuarioCreate.getContrasenia());
        usuario.setRol(Rol.USUARIO);
        return usuario;
       }
}
