package br.com.grownext.service;

import br.com.grownext.domain.entity.Pedido;
import br.com.grownext.rest.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar(PedidoDTO pedidoDTO);
}
