package com.example.inicial1.controllers;

import com.example.inicial1.entities.Mutant;
import com.example.inicial1.services.MutantServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/mutant/")
public class MutantController {

    @Autowired
    protected MutantServices servicio;

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Mutant entity) {
        try {
            Mutant entidad = servicio.save(entity);
            if (entidad.getEsMutant()) {
                return ResponseEntity.status(HttpStatus.OK).body("Efectivamente es mutante");
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"error\":\"Error, por favor intente más tarde\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        Map<String, Object> stats = servicio.getStats();
        return ResponseEntity.status(HttpStatus.OK).body(stats);
    }

}