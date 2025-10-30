package com.example.foodstore.Services.Producto;

import com.example.foodstore.entity.Producto;
import com.example.foodstore.entity.dtos.Producto.ProductoCreate;
import com.example.foodstore.entity.dtos.Producto.ProductoDto;
import com.example.foodstore.entity.dtos.Producto.ProductoEdit;
import com.example.foodstore.entity.dtos.Usuario.UsuarioCreate;
import com.example.foodstore.entity.dtos.Usuario.UsuarioDto;
import com.example.foodstore.entity.dtos.Usuario.UsuarioEdit;

import java.util.List;

public interface ProductoService {

    public ProductoDto crear(ProductoCreate p);
    public ProductoDto actualizar(Long id, ProductoEdit p);
    public ProductoDto buscaId(Long id);
    public List<ProductoDto> buscaTodos();
    public void eliminar(Long id);
}
