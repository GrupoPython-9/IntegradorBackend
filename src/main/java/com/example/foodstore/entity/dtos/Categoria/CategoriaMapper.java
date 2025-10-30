package com.example.foodstore.entity.dtos.Categoria;

import com.example.foodstore.entity.Categoria;

public class CategoriaMapper {

    public static CategoriaDto toDto(Categoria categoria){
        return new CategoriaDto.CategoriaDtoBuilder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .productos(categoria.getProductos())
                .build();
    }

    public static Categoria toEntity(CategoriaCreate categoriaCreate){
        return Categoria.builder()
                .nombre(categoriaCreate.getNombre())
                .descripcion(categoriaCreate.getDescripcion())
                .build();
    }

    public static Categoria toEdit(CategoriaEdit categoriaEdit){
        return Categoria.builder()
                .nombre(categoriaEdit.getNombre())
                .descripcion(categoriaEdit.getDescripcion())
                .build();
    }
}
