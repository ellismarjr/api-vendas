package br.com.grownext.domain.repository;

import br.com.grownext.domain.entity.Cliente;
import br.com.grownext.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidosRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente( Cliente cliente);
}
