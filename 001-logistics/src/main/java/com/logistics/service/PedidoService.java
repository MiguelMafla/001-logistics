package com.logistics.service;

import com.logistics.exception.BadRequestException;
import com.logistics.exception.ResourceNotFoundException;
import com.logistics.model.PedidoModel;
import com.logistics.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public PedidoModel crearPedido(PedidoModel pedido) {
        return pedidoRepository.save(pedido);
    }

    public List<PedidoModel> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public PedidoModel obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado"));
    }

    public PedidoModel actualizarPedido(Long id, PedidoModel pedidoActualizado) {
        PedidoModel pedidoExistente = obtenerPedidoPorId(id);

        // Regla de negocio: solo actualizar a EN_TRANSITO si tiene un conductor asignado
        if ("EN_TRANSITO".equals(pedidoActualizado.getEstado()) && pedidoActualizado.getConductorId() == null) {
            throw new BadRequestException("Un pedido solo puede estar EN_TRANSITO si tiene un conductor asignado");
        }

        pedidoExistente.setDescripcion(pedidoActualizado.getDescripcion());
        pedidoExistente.setEstado(pedidoActualizado.getEstado());
        pedidoExistente.setConductorId(pedidoActualizado.getConductorId());

        return pedidoRepository.save(pedidoExistente);
    }

    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}
