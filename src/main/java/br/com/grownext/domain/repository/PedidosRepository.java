package br.com.grownext.domain.repository;

import br.com.grownext.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidosRepository extends JpaRepository<Pedido, Integer> {
}
