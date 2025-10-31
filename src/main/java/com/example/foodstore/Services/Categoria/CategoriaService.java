package com.example.foodstore.Services.Categoria;

import com.example.foodstore.entity.dtos.Categoria.CategoriaCreate;
import com.example.foodstore.entity.dtos.Categoria.CategoriaDto;
import com.example.foodstore.entity.dtos.Categoria.CategoriaEdit;
import com.example.foodstore.entity.dtos.Producto.ProductoCreate;

import java.util.List;


public interface CategoriaService {

    public CategoriaDto crear(CategoriaCreate c);//POST
    public CategoriaDto actualizar (Long id, CategoriaEdit c); //PUT/PATCH
    public CategoriaDto leer (Long id); //GET
    public void eliminar (Long id); //DELETE
    public List<CategoriaDto> listar(); //GET
    public CategoriaDto restaurar(Long id);
    public CategoriaDto agregarProducto (Long categoriaId, Long productoId);

}
