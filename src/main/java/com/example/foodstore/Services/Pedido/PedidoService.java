package com.example.foodstore.Services.Pedido;

import com.example.foodstore.entity.dtos.Pedido.PedidoCreate;
import com.example.foodstore.entity.dtos.Pedido.PedidoDto;
import com.example.foodstore.entity.dtos.Pedido.PedidoEdit;

import java.util.List;

public interface PedidoService {
    PedidoDto crear(PedidoCreate pedidoCreate);
    List<PedidoDto> buscaTodos();
    PedidoDto buscaId(Long id);
    PedidoDto actualizar(Long id, PedidoEdit pedidoEdit);

}