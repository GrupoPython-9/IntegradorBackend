package com.example.foodstore.controller;

import com.example.foodstore.Services.Producto.ProductoService;

import com.example.foodstore.entity.dtos.Producto.ProductoCreate;
import com.example.foodstore.entity.dtos.Producto.ProductoDto;
import com.example.foodstore.entity.dtos.Producto.ProductoEdit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(
        origins = "http://localhost:5173",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE},
        allowedHeaders = "*"
)
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    ProductoService productoService;

    @PostMapping("")
    public ResponseEntity<?> crear (@RequestBody ProductoCreate p){
        try {
            ProductoDto productoCreado = productoService.crear(p);
            if (productoCreado == null) {
                return ResponseEntity.badRequest().body("El producto ya está registrado");
            }
            return ResponseEntity.ok(productoCreado);

        }catch (Exception e){
            //prueba
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Ocurrio un error: " + e.getMessage());

        }
    }

    @PutMapping ("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody ProductoEdit productoEdit){
        try {
            return ResponseEntity.ok().body(productoService.actualizar(id,productoEdit));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocurrio un error: " + e.getMessage());
        }

    }

    @DeleteMapping("/{id}")

    public ResponseEntity<?>borrar(@PathVariable Long id){
        try {
            productoService.eliminar(id);
            return ResponseEntity.ok().body("Entidad eliminada");//Cambiar por algo del producto
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrio un error " +e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<?> buscaTodos(){ //Hacer que no muestre las eliminadas
        try {
            return ResponseEntity.ok().body(productoService.buscaTodos());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrio un error " +e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscaId(@PathVariable Long id){
        try{
            return ResponseEntity.ok(productoService.buscaId(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrio un error " +e.getMessage());
        }
    }
}

