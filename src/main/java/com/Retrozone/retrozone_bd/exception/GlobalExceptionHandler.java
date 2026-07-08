package com.Retrozone.retrozone_bd.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

// @RestControllerAdvice intercepta excepciones lanzadas desde CUALQUIER
// @RestController de la app en un solo lugar, en vez de repetir try/catch
// en cada Controller.
@RestControllerAdvice
public class GlobalExceptionHandler {

    // IllegalArgumentException la lanzan los Services para errores de
    // validación de negocio (email duplicado, contraseña con formato
    // inválido, usuario no encontrado, etc.). Sin este manejador, Spring
    // deja pasar estas excepciones como errores no controlados y responde
    // 500 Internal Server Error — engañoso, porque el problema es que el
    // cliente mandó datos inválidos (400), no que el servidor falló.
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
    }
}
