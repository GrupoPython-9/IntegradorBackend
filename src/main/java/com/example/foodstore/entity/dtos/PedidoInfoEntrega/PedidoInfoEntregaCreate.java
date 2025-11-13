package com.example.foodstore.entity.dtos.PedidoInfoEntrega;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoInfoEntregaCreate {
    private String direccion;
    private String telefono;
    private String metodoPago;
    private String nota;
}
