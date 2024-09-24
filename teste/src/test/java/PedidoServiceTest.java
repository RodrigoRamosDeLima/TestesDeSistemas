import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.NotificacaoService;
import com.example.PagamentoService;
import com.example.Pedido;
import com.example.PedidoService;

public class PedidoServiceTest {

    @Test
    public void testProcessarPedidoIntegracao() {
        PagamentoService pagamentoService = new PagamentoService();
        NotificacaoService notificacaoService = new NotificacaoService();

        PedidoService pedidoService = new PedidoService(pagamentoService, notificacaoService);
        Pedido pedido = new Pedido("123", 250.00);
        boolean resultado = pedidoService.processarPedido(pedido);

        assertTrue(resultado);
        assertTrue(notificacaoService.enviarConfirmacao(pedido));
    }

    @Test
    public void testProcessarPedidoIntegracaoMock() {
        PagamentoService pagamentoService = Mockito.mock(PagamentoService.class);
        NotificacaoService notificacaoService = Mockito.mock(NotificacaoService.class);

        PedidoService pedidoService = new PedidoService(pagamentoService, notificacaoService);

        when(pagamentoService.processarPagamento(any())).thenReturn(true);
        when(notificacaoService.enviarConfirmacao(any())).thenReturn(true);

        Pedido pedido = new Pedido("123", 250.00);
        boolean resultado = pedidoService.processarPedido(pedido);

        assertTrue(resultado);
        assertTrue(notificacaoService.enviarConfirmacao(pedido));
    }

}
