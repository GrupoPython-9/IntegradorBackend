package com.example.foodstore.Services.Producto;

import com.example.foodstore.Repository.ProductoRepository;
import com.example.foodstore.entity.Producto;
import com.example.foodstore.entity.dtos.Producto.ProductoCreate;
import com.example.foodstore.entity.dtos.Producto.ProductoDto;
import com.example.foodstore.entity.dtos.Producto.ProductoEdit;
import com.example.foodstore.entity.dtos.Producto.ProductoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImp implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public ProductoDto crear(ProductoCreate p) {
        // Se transforma el DTO de creación en una entidad Persona lista para ser persistida.
        Producto producto = ProductoMapper.toEntity(p);

        // Se guarda la entidad en la base de datos a través del repositorio.
        // save() retorna la entidad con el ID generado.
        producto = productoRepository.save(producto);

        // Se convierte nuevamente la entidad persistida en un DTO de salida.
        // Esto asegura que solo se expongan los datos necesarios.
        ProductoDto productoDto = ProductoMapper.toDto(producto);

        // Se retorna el DTO con la información final (incluye ID generado).
        return productoDto;
    }
    @Override
    public ProductoDto actualizar(Long id, ProductoEdit p) {
        // Se busca en la base de datos la Persona por su ID.
        // El repositorio retorna un Optional, que puede contener la entidad o estar vacío.
        Optional<Producto> productoOptional = productoRepository.findById(id);

        //SOLUCION OPTIMA AUN NO EXPLICADA
        //Persona person = personaRepository.findById(id).orElseThrow(()->new NullPointerException("No se encontró el ID del desarrollador");


        if(productoOptional.isPresent()){
            // Si existe, se obtiene la entidad.
            Producto producto = productoOptional.get();

            // Se actualizan los campos permitidos con los valores del DTO de edición.
            producto.setPrecio(p.getPrecio());
            producto.setStock(p.getStock());

            // Se guarda nuevamente la entidad actualizada en la base de datos.
            producto = productoRepository.save(producto);

            // Se convierte la entidad persistida a un DTO de salida para retornarla.
            return ProductoMapper.toDto(producto);
        }

        return null;
    }

    @Override
    public ProductoDto buscaId(Long id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if (productoOptional.isPresent() ){
            if (!productoOptional.get().isEliminado())
                return ProductoMapper.toDto(productoOptional.get());
        }
        return null;
    }

    @Override
    public List<ProductoDto> buscaTodos() {
        List<Producto> productos = productoRepository.findAllByEliminadoFalse();
        List<ProductoDto> productoDtos = productos.stream().map(ProductoMapper::toDto).toList();
        return productoDtos;
    }

    @Override
    public void eliminar(Long id) {
        Optional<Producto> persona = productoRepository.findById(id);
        if (persona.isPresent()){
            Producto p = persona.get();
            p.setEliminado(true);
            productoRepository.save(p);
        }
    }

}
