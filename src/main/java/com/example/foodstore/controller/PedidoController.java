package com.example.foodstore.controller;

import com.example.foodstore.Services.Pedido.PedidoService;
import com.example.foodstore.entity.dtos.Pedido.PedidoCreate;
import com.example.foodstore.entity.dtos.Pedido.PedidoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin (
        origins = "http://localhost:5173",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE},
        allowedHeaders = "*"
)
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("")
    public ResponseEntity<?> crearPedido(@RequestBody PedidoCreate pedidoCreate) {
        try{
        PedidoDto nuevoPedido = pedidoService.crear(pedidoCreate);
        return ResponseEntity.ok(nuevoPedido);

        }catch (Exception e) {
         return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<PedidoDto>> obtenerPedidos() {
        List<PedidoDto> pedidos = pedidoService.buscaTodos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> obtenerPedidoPorId(@PathVariable Long id) {
        PedidoDto pedido = pedidoService.buscaId(id);
        if (pedido != null) return ResponseEntity.ok(pedido);
        return ResponseEntity.notFound().build();
    }
}