package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.repository;

import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
