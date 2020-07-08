package br.com.grownext.service.impl;

import br.com.grownext.domain.entity.Cliente;
import br.com.grownext.domain.entity.ItemPedido;
import br.com.grownext.domain.entity.Pedido;
import br.com.grownext.domain.entity.Produto;
import br.com.grownext.domain.enums.StatusPedido;
import br.com.grownext.domain.repository.ClientesRespository;
import br.com.grownext.domain.repository.ItemPedidoRepository;
import br.com.grownext.domain.repository.PedidosRepository;
import br.com.grownext.domain.repository.ProdutosRepository;
import br.com.grownext.excepetion.RegraNegocioException;
import br.com.grownext.rest.dto.ItemPedidoDTO;
import br.com.grownext.rest.dto.PedidoDTO;
import br.com.grownext.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImp implements PedidoService {

    private final PedidosRepository pedidosRepository;
    private final ClientesRespository clientesRespository;
    private final ProdutosRepository produtosRepository;
    private final ItemPedidoRepository itemPedidoRepository;


    @Override
    @Transactional
    public Pedido salvar(PedidoDTO pedidoDTO) {
        Integer idCliente = pedidoDTO.getCliente();
        Cliente cliente = clientesRespository.findById(idCliente).orElseThrow(() -> new RegraNegocioException("Código de cliente inválido!"));

        Pedido pedido = new Pedido();
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemsPedido = converterItems(pedido, pedidoDTO.getItems());
        pedidosRepository.save(pedido);
        itemPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidosRepository.findByIdFetchItens(id);
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
        if (items.isEmpty()) {
            throw new RegraNegocioException("Não é possível realizar um pedido sem itens.");
        }

        return items
                .stream()
                .map(item -> {
                    Integer idProduto = item.getProduto();
                    Produto produto = produtosRepository.findById(idProduto)
                            .orElseThrow(() -> new RegraNegocioException("Código de produto inválido!"));
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(item.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
