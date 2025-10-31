package com.example.foodstore.entity.dtos.Producto;

import com.example.foodstore.entity.Producto;

public class ProductoMapper {

 public static    ProductoDto toDto(Producto p){
        ProductoDto productoDto=new ProductoDto.ProductoDtoBuilder()
                .id(p.getId())
                .nombre(p.getNombre())
                .descripcion(p.getDescripcion())
                .precio(p.getPrecio())
                .stock(p.getStock())
                .build();
        return productoDto;

    }

   public static Producto toEntity(ProductoCreate pc){
     Producto producto = new Producto();

             producto.setNombre(pc.getNombre());
             producto.setDescripcion(pc.getDescripcion());
             producto.setPrecio(pc.getPrecio());
             producto.setStock(pc.getStock());


          return producto;
   }


}
