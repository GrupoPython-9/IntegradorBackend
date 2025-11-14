package com.example.foodstore.controller;

import com.example.foodstore.Services.Pedido.PedidoService;
import com.example.foodstore.entity.dtos.Pedido.PedidoCreate;
import com.example.foodstore.entity.dtos.Pedido.PedidoDto;
import com.example.foodstore.entity.dtos.Pedido.PedidoEdit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPedido(
            @PathVariable Long id,
            @RequestBody PedidoEdit pedidoEdit
    ) {
        try {
            PedidoDto actualizado = pedidoService.actualizar(id, pedidoEdit);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPedido(@PathVariable Long id) {
        try {
            pedidoService.eliminar(id);

            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("estado", 200);
            respuesta.put("mensaje", "Pedido eliminado");

            return ResponseEntity.ok(respuesta);

        } catch (Exception e) {

            Map<String, Object> error = new HashMap<>();
            error.put("estado", 400);
            error.put("mensaje", e.getMessage());

            return ResponseEntity.badRequest().body(error);
        }
    }

    // CONFIRMAR PEDIDO → RESTAR STOCK
    // -------------------------------------------------------
    @PutMapping("/{id}/confirmar")
    public ResponseEntity<PedidoDto> confirmarPedido(@PathVariable Long id) {
        PedidoDto dto = pedidoService.confirmarPedidoDesc(id);
        return ResponseEntity.ok(dto);
    }

    // -------------------------------------------------------
    // CANCELAR PEDIDO → DEVOLVER STOCK SI CORRESPONDE
    // -------------------------------------------------------
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<PedidoDto> cancelarPedido(@PathVariable Long id) {
        PedidoDto dto = pedidoService.cancelarPedido(id);
        return ResponseEntity.ok(dto);
    }

}