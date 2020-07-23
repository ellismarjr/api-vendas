package br.com.grownext.excepetion;

public class PedidoNaoEncontradoExpection extends RuntimeException {
    public PedidoNaoEncontradoExpection() {
        super("Pedido não encontrado.");
    }
}
