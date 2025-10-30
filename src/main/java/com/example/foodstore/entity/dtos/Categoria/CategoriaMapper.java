package com.example.foodstore.entity.dtos.Categoria;

import com.example.foodstore.entity.Categoria;

public class CategoriaMapper {

    public static CategoriaDto toDto(Categoria categoria){
        CategoriaDto categoriaDto = new CategoriaDto.CategoriaDtoBuilder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .productos(categoria.getProductos())
                .build();
        return categoriaDto;
    }

    public static Categoria toEntity(CategoriaCreate categoriaCreate){
        Categoria categoria = new Categoria();
        categoria.setNombre(categoriaCreate.getNombre());
        categoria.setDescripcion(categoriaCreate.getDescripcion());
        return categoria;
    }


}
