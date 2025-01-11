package com.logistics.service;

import com.logistics.exception.BadRequestException;
import com.logistics.exception.ResourceNotFoundException;
import com.logistics.model.ConductorModel;
import com.logistics.repository.ConductorRepository;
import com.logistics.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConductorService {

    private final ConductorRepository conductorRepository;
    private final PedidoRepository pedidoRepository;

    public ConductorService(ConductorRepository conductorRepository, PedidoRepository pedidoRepository) {
        this.conductorRepository = conductorRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public ConductorModel crearConductor(ConductorModel conductor) {
        return conductorRepository.save(conductor);
    }

    public List<ConductorModel> listarConductores() {
        return conductorRepository.findAll();
    }

    public ConductorModel obtenerConductorPorId(Long id) {
        return conductorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conductor no encontrado"));
    }

    public ConductorModel actualizarConductor(Long id, ConductorModel conductorActualizado) {
        ConductorModel conductorExistente = obtenerConductorPorId(id);
        conductorExistente.setNombre(conductorActualizado.getNombre());
        conductorExistente.setLicencia(conductorActualizado.getLicencia());
        return conductorRepository.save(conductorExistente);
    }

    public void eliminarConductor(Long id) {
        if (!pedidoRepository.findByConductorId(id).isEmpty()) {
            throw new BadRequestException("No se puede eliminar un conductor con pedidos asignados");
        }
        else{
            conductorRepository.deleteById(id);
        }
    }
}
