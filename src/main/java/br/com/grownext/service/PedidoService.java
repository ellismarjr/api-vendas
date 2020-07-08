package br.com.grownext.service;

import br.com.grownext.domain.entity.Pedido;
import br.com.grownext.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO pedidoDTO);

    Optional<Pedido> obterPedidoCompleto(Integer id);
}
