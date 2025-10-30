package com.example.foodstore.Services;

import com.example.foodstore.entity.dtos.Usuario.UsuarioCreate;
import com.example.foodstore.entity.dtos.Usuario.UsuarioDto;
import com.example.foodstore.entity.dtos.Usuario.UsuarioEdit;

import java.util.List;

public interface UsuarioService {

    public UsuarioDto crear(UsuarioCreate u);
    public UsuarioDto actualizar(Long id, UsuarioEdit u);
    public UsuarioDto buscaId(Long id);
    public List<UsuarioDto> buscaTodos();
    public void eliminar(Long id);


}
