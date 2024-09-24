package com.example;

public class PedidoService {

    private final PagamentoService pagamentoService;
    private final NotificacaoService notificacaoService;

    public PedidoService(PagamentoService pagamentoService, NotificacaoService notificacaoService) {
        this.pagamentoService = pagamentoService;
        this.notificacaoService = notificacaoService;
    }

    public boolean processarPedido(Pedido pedido) {
        boolean pagamentoRealizado = pagamentoService.processarPagamento(pedido);

        if (pagamentoRealizado) {
            notificacaoService.enviarConfirmacao(pedido);
            return true;
        }
        return false;
    }
}

