package com.example.foodstore.Services.Categoria;

import com.example.foodstore.Repository.CategoriaRepository;
import com.example.foodstore.entity.Categoria;
import com.example.foodstore.entity.dtos.Categoria.CategoriaCreate;
import com.example.foodstore.entity.dtos.Categoria.CategoriaDto;
import com.example.foodstore.entity.dtos.Categoria.CategoriaEdit;
import com.example.foodstore.entity.dtos.Categoria.CategoriaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaServiceImp implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

   @Override
    public CategoriaDto crear (CategoriaCreate c){
       if (c.getNombre()==null || categoriaRepository.existsByNombreAndIsEliminadoFalse(c.getNombre())){
           throw new RuntimeException("La categoría ya existe");
       }
       Categoria categoria = CategoriaMapper.toEntity(c);
       categoria = categoriaRepository.save(categoria);
       return CategoriaMapper.toDto(categoria);

   }
//public CategoriaDto actualizar (Long id, CategoriaEdit c)
   @Override
    public CategoriaDto actualizar (Long id, CategoriaEdit c){

       //Validar nombre si es nulo o vacio
       if (c.getNombre() == null || c.getNombre().isBlank()){
           throw new RuntimeException("No puede estar vacio");
       }
       //Buscar la categoria por el id
       Categoria categoria = categoriaRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        //Validar duplicado excluyendo la categoria actual.
       boolean nombreDuplicado = categoriaRepository.existsByNombreAndIsEliminadoFalse(c.getNombre())
               && !categoria.getNombre().equals(c.getNombre());
       if (nombreDuplicado) {
           throw new RuntimeException("Ya existe otra categoría con ese nombre");
       }
       //actualizar campos de la entidad
       categoria.setNombre(c.getNombre());
       categoria.setDescripcion(c.getDescripcion());
       //Guardar los cambios en repository
       categoria = categoriaRepository.save(categoria);
        // Retornar Dto actualizado
       return CategoriaMapper.toDto(categoria);

   }

   //TODO FALTA

    //if (categoria.isEliminado()) {
    //   throw new Exception("Categoría eliminada");
    //}

    //Podés tener un mét//odo en el servicio llamado
    // `deleteLogical(Long id)` que internamente use `findById` + `setEliminado(true)` + `save()`.Podés tener un método en el servicio llamado `deleteLogical(Long id)` que internamente use `findById` + `setEliminado(true)` + `save()`.
}
