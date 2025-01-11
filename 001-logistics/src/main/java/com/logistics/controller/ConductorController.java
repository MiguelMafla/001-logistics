package com.logistics.controller;
import com.logistics.model.ConductorModel;
import com.logistics.service.ConductorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/conductores")
public class ConductorController {

    private final ConductorService conductorService;

    public ConductorController(ConductorService conductorService) {
        this.conductorService = conductorService;
    }

    // POST /conductores - Crear un nuevo conductor
    @PostMapping
    public ResponseEntity<ConductorModel> crearConductor(@RequestBody ConductorModel conductor) {
        ConductorModel nuevoConductor = conductorService.crearConductor(conductor);
        return ResponseEntity.ok(nuevoConductor);
    }

    // GET /conductores - Listar todos los conductores
    @GetMapping
    public ResponseEntity<List<ConductorModel>> listarConductores() {
        return ResponseEntity.ok(conductorService.listarConductores());
    }

    // GET /conductores/{id} - Obtener un conductor por ID
    @GetMapping("/{id}")
    public ResponseEntity<ConductorModel> obtenerConductorPorId(@PathVariable Long id) {
        ConductorModel conductor = conductorService.obtenerConductorPorId(id);
        return ResponseEntity.ok(conductor);
    }

    // PUT /conductores/{id} - Actualizar un conductor existente
    @PutMapping("/{id}")
    public ResponseEntity<ConductorModel> actualizarConductor(@PathVariable Long id, @RequestBody ConductorModel conductor) {
        return ResponseEntity.ok(conductorService.actualizarConductor(id, conductor));
    }

    // DELETE /conductores/{id} - Eliminar un conductor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarConductor(@PathVariable Long id) {
        conductorService.eliminarConductor(id);
        return ResponseEntity.noContent().build();
    }
}


