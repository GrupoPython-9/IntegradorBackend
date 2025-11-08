package com.example.foodstore.Services.DetallePedido;

import com.example.foodstore.Repository.DetallePedidoRepository;
import com.example.foodstore.Repository.ProductoRepository;
import com.example.foodstore.entity.DetallePedido;
import com.example.foodstore.entity.Producto;
import com.example.foodstore.entity.dtos.DetallePedido.DetallePedidoCreate;
import com.example.foodstore.entity.dtos.DetallePedido.DetallePedidoDto;
import com.example.foodstore.entity.dtos.DetallePedido.DetallePedidoEdit;
import com.example.foodstore.entity.dtos.DetallePedido.DetallePedidoMapper;
;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class DetallePedidoServiceImp implements DetallePedidoService{


    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Override
    public DetallePedidoDto crear(DetallePedidoCreate d) {
        // Se transforma el DTO de creación en una entidad Persona lista para ser persistida.
        DetallePedido detallePedido = DetallePedidoMapper.toEntity(d);

        // Se guarda la entidad en la base de datos a través del repositorio.
        // save() retorna la entidad con el ID generado.
        detallePedido = detallePedidoRepository.save(detallePedido);

        // Se convierte nuevamente la entidad persistida en un DTO de salida.
        // Esto asegura que solo se expongan los datos necesarios.
        DetallePedidoDto detallePedidoDto = DetallePedidoMapper.toDto(detallePedido);

        // Se retorna el DTO con la información final (incluye ID generado).
        return detallePedidoDto;
    }
    @Override
    public DetallePedidoDto actualizar(Long id, DetallePedidoEdit d) {
        // Se busca en la base de datos la Persona por su ID.
        // El repositorio retorna un Optional, que puede contener la entidad o estar vacío.
        Optional<DetallePedido> detallePedidoOptional = detallePedidoRepository.findById(id);

        //SOLUCION OPTIMA AUN NO EXPLICADA
        //Persona person = personaRepository.findById(id).orElseThrow(()->new NullPointerException("No se encontró el ID del desarrollador");


        if(detallePedidoOptional.isPresent()){
            // Si existe, se obtiene la entidad.
            DetallePedido detallePedido = detallePedidoOptional.get();

            // Se actualizan los campos permitidos con los valores del DTO de edición.
          detallePedido.setCantidad(d.getCantidad());


            // Se guarda nuevamente la entidad actualizada en la base de datos.
            detallePedido = detallePedidoRepository.save(detallePedido);

            // Se convierte la entidad persistida a un DTO de salida para retornarla.
            return DetallePedidoMapper.toDto(detallePedido);
        }

        return null;
    }

    @Override
    public DetallePedidoDto buscaId(Long id) {
        Optional<DetallePedido> detallePedidoOptional = detallePedidoRepository.findById(id);
        if (detallePedidoOptional.isPresent() ){
            if (!detallePedidoOptional.get().isEliminado())
                return DetallePedidoMapper.toDto(detallePedidoOptional.get());
        }
        return null;
    }

    @Override
    public List<DetallePedidoDto> buscaTodos() {
        List<DetallePedido> detallePedidos = detallePedidoRepository.findAllByEliminadoFalse();
        List<DetallePedidoDto> detallePedidoDtos = detallePedidos.stream().map(DetallePedidoMapper::toDto).toList();
        return detallePedidoDtos;
    }

    @Override
    public void eliminar(Long id) {
        Optional<DetallePedido> detallePedido = detallePedidoRepository.findById(id);
        if (detallePedido.isPresent()){
            DetallePedido d = detallePedido.get();
            d.setEliminado(true);
            detallePedidoRepository.save(d);
        }
    }



}
