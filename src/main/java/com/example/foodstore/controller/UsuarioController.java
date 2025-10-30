package com.example.foodstore.controller;

import com.example.foodstore.Services.UsuarioService;
import com.example.foodstore.entity.dtos.Usuario.UsuarioCreate;
import com.example.foodstore.entity.dtos.Usuario.UsuarioDto;
import com.example.foodstore.entity.dtos.Usuario.UsuarioEdit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(
        origins = "http://localhost:5173",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE},
        allowedHeaders = "*"
)
@RequestMapping ("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @PostMapping("")
     public ResponseEntity<?> crear (@RequestBody UsuarioCreate u){
        try {
            UsuarioDto usuarioCreado = usuarioService.crear(u);
            if (usuarioCreado == null) {
                return ResponseEntity.badRequest().body("El correo ya está registrado");
            }
            return ResponseEntity.ok(usuarioCreado);

        }catch (Exception e){
            //prueba
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Ocurrio un error: " + e.getMessage());

        }
    }

    @PutMapping ("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody UsuarioEdit usuarioEdit){
      try {
          return ResponseEntity.ok().body(usuarioService.actualizar(id,usuarioEdit));
      } catch (Exception e) {
          return ResponseEntity.badRequest().body("Ocurrio un error: " + e.getMessage());
      }

    }

    @DeleteMapping("/{id}")

    public ResponseEntity<?>borrar(@PathVariable Long id){
       try {
           usuarioService.eliminar(id);
           return ResponseEntity.ok().body("Entidad eliminada");
       }catch (Exception e){
           return ResponseEntity.badRequest().body("Ocurrio un error " +e.getMessage());
       }
    }

    @GetMapping("")
    public ResponseEntity<?> buscaTodos(){
        try {
            return ResponseEntity.ok().body(usuarioService.buscaTodos());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrio un error " +e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscaId(@PathVariable Long id){
        try{
            return ResponseEntity.ok(usuarioService.buscaId(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrio un error " +e.getMessage());
        }
    }
}
