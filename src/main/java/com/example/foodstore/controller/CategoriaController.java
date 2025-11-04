package com.example.foodstore.controller;

import com.example.foodstore.Services.Categoria.CategoriaService;
import com.example.foodstore.entity.dtos.Categoria.CategoriaCreate;
import com.example.foodstore.entity.dtos.Categoria.CategoriaDto;
import com.example.foodstore.entity.dtos.Categoria.CategoriaEdit;
import com.example.foodstore.entity.dtos.Categoria.CategoriaResponse;
import com.example.foodstore.entity.dtos.Producto.ProductoCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin (
        origins = "http://localhost:5173",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE},
        allowedHeaders = "*"
)
@RequestMapping ("/categorias")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @PostMapping("")
    public ResponseEntity<?> crear(@RequestBody CategoriaCreate c) {
        try {
            // Validación de nombre
            if (c.getNombre() == null || c.getNombre().isBlank()) {
                return ResponseEntity
                        .badRequest()
                        .body("El nombre no puede estar en blanco o nulo.");
            }

            // Validación de descripción
            if (c.getDescripcion() != null && c.getDescripcion().length() > 250) {
                return ResponseEntity
                        .badRequest()
                        .body("La descripción no puede superar los 250 caracteres.");
            }

            // Llamada al servicio para crear la categoría
            CategoriaDto nuevaCategoria = categoriaService.crear(c);

            // Retorno con status 201 CREATED
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(nuevaCategoria);

        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body("Ocurrió un error: " + e.getMessage());
        } catch (Exception e) {
            // Errores inesperados
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<CategoriaResponse> listar() {
        try {
            List<CategoriaDto> categorias = categoriaService.listar();
            CategoriaResponse response = new CategoriaResponse(200, "OK", categorias);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            CategoriaResponse response = new CategoriaResponse(500, "Error interno del servidor", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> leer(@PathVariable Long id) {
        try {
            if (id == null) {
                CategoriaResponse response = new CategoriaResponse(400, "ID inválido", null);
                return ResponseEntity.badRequest().body(response);
            }
            CategoriaDto categoria = categoriaService.leer(id);
            CategoriaResponse response = new CategoriaResponse(200, "Categoría encontrada", categoria);
            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            CategoriaResponse response = new CategoriaResponse(404, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (Exception e) {
            CategoriaResponse response = new CategoriaResponse(500, "Error interno del servidor", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> actualizar(@PathVariable Long id, @RequestBody CategoriaEdit categoriaEdit) {
        try {
            if (id == null) {
                CategoriaResponse response = new CategoriaResponse(400, "ID inválido", null);
                return ResponseEntity.badRequest().body(response);
            }
            CategoriaDto categoria = categoriaService.actualizar(id, categoriaEdit);
            CategoriaResponse response = new CategoriaResponse(200, "Se actualizo la categoria", categoria);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            CategoriaResponse response = new CategoriaResponse(404, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (Exception e) {
            CategoriaResponse response = new CategoriaResponse(500, "Error interno del servidor", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoriaResponse> eliminar(@PathVariable Long id) {
        try {
            if (id == null) {
                CategoriaResponse response = new CategoriaResponse(400, "Id invalido", null);
                return ResponseEntity.badRequest().body(response);
            }
            categoriaService.eliminar(id);
            CategoriaResponse response = new CategoriaResponse(200, "Categoria Eliminada correctamente", null);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            CategoriaResponse response = new CategoriaResponse(404, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (Exception e) {
            CategoriaResponse response = new CategoriaResponse(500, "Error interno del servidor", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/{categoriaId}/productos/{productoId}")
    public ResponseEntity<CategoriaResponse> agregarProducto(@PathVariable Long categoriaId, @PathVariable Long productoId) {
        try {
            if (categoriaId == null || productoId == null) {
                CategoriaResponse response = new CategoriaResponse(400, "ID de categoría o producto inválido", null);
                return ResponseEntity.badRequest().body(response);
            }
            CategoriaDto categoriaActualizada = categoriaService.agregarProducto(categoriaId, productoId);
            CategoriaResponse response = new CategoriaResponse(200, "Producto agregado correctamente", categoriaActualizada);
            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            CategoriaResponse response = new CategoriaResponse(404, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (Exception e) {
            CategoriaResponse response = new CategoriaResponse(500, "Error interno del servidor", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
