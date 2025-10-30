package com.example.foodstore.Services.Categoria;

import com.example.foodstore.entity.dtos.Categoria.CategoriaCreate;
import com.example.foodstore.entity.dtos.Categoria.CategoriaDto;
import com.example.foodstore.entity.dtos.Categoria.CategoriaEdit;
import com.example.foodstore.entity.dtos.Usuario.UsuarioEdit;

public interface CategoriaService {

    public CategoriaDto crear(CategoriaCreate c);
    public CategoriaDto actualizar (Long id, CategoriaEdit c);
    public CategoriaDto leer (Long id);
    public void eliminar (Long id);
}
