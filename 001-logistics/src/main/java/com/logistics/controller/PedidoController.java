package com.logistics.controller;
import com.logistics.model.PedidoModel;
import com.logistics.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // POST /pedidos - Crear un nuevo pedido
    @PostMapping
    public ResponseEntity<PedidoModel> crearPedido(@RequestBody PedidoModel pedido) {
        PedidoModel nuevoPedido = pedidoService.crearPedido(pedido);
        return ResponseEntity.ok(nuevoPedido);
    }

    // GET /pedidos - Listar todos los pedidos
    @GetMapping
    public ResponseEntity<List<PedidoModel>> listarPedidos() {
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }

    // GET /pedidos/{id} - Consultar un pedido por su ID
    @GetMapping("/{id}")
    public ResponseEntity<PedidoModel> obtenerPedidoPorId(@PathVariable Long id) {
        PedidoModel pedido = pedidoService.obtenerPedidoPorId(id);
        return ResponseEntity.ok(pedido);
    }

    // PUT /pedidos/{id} - Actualizar un pedido existente
    @PutMapping("/{id}")
    public ResponseEntity<PedidoModel> actualizarPedido(@PathVariable Long id, @RequestBody PedidoModel pedido) {
        return ResponseEntity.ok(pedidoService.actualizarPedido(id, pedido));
    }

    // DELETE /pedidos/{id} - Eliminar un pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
