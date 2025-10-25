package com.example.foodstore.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173") // Permite conexión desde tu frontend
public class TestController {

    @Autowired
    private DataSource dataSource;  // Se inyecta automáticamente según tu configuración en application.properties

    // Endpoint básico para probar el backend
    @GetMapping("/test")
    public String testConnection() {
        return "Conexión exitosa con el backend 🚀";
    }

    // Endpoint para probar la conexión a PostgreSQL
    @GetMapping("/dbtest")
    public String testDatabaseConnection() {
        try (Connection connection = dataSource.getConnection()) {
            if (connection.isValid(2)) {
                return "✅ Conexión exitosa con PostgreSQL";
            } else {
                return "⚠️ No se pudo conectar a la base de datos";
            }
        } catch (SQLException e) {
            return "❌ Error de conexión: " + e.getMessage();
        }
    }



}

