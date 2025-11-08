package com.example.foodstore.Services.DetallePedido;


import com.example.foodstore.entity.DetallePedido;
import com.example.foodstore.entity.dtos.DetallePedido.DetallePedidoCreate;
import com.example.foodstore.entity.dtos.DetallePedido.DetallePedidoDto;
import com.example.foodstore.entity.dtos.DetallePedido.DetallePedidoEdit;


import java.util.List;

public interface DetallePedidoService  {


    public DetallePedidoDto crear(DetallePedidoCreate d);
    public DetallePedidoDto actualizar(Long id, DetallePedidoEdit d);
    public DetallePedidoDto buscaId(Long id);
    public List<DetallePedidoDto> buscaTodos();
    public void eliminar(Long id);




}
