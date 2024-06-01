package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.repository;

import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
