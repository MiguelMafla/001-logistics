package com.logistics.repository;
import com.logistics.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface PedidoRepository extends JpaRepository<PedidoModel, Long> {
    List<PedidoModel> findByConductorId(Long conductorId);
}
