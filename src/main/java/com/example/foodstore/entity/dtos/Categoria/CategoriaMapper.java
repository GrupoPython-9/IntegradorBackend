package com.example.foodstore.entity.dtos.Categoria;

import com.example.foodstore.entity.Categoria;
import com.example.foodstore.entity.Producto;
import com.example.foodstore.entity.dtos.Producto.ProductoDto;
import com.example.foodstore.entity.dtos.Producto.ProductoMapper;

import java.util.ArrayList;
import java.util.List;

public class CategoriaMapper {

    public static CategoriaDto toDto(Categoria categoria){
        List<ProductoDto> productosDto = new ArrayList<>();
        if (categoria.getProductos() != null) {
            for (Producto producto : categoria.getProductos()) {
                ProductoDto productoDto = ProductoMapper.toDto(producto);
                productosDto.add(productoDto);
            }
        }
        return new CategoriaDto.CategoriaDtoBuilder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .productos(productosDto)
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
