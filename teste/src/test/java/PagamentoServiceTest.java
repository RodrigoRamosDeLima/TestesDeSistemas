import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.example.NotificacaoService;
import com.example.PagamentoService;
import com.example.Pedido;
import com.example.PedidoService;

public class PagamentoServiceTest {
    
    @Test
    public void testProcessarPedidoComSucesso() {
        // Given
        Pedido pedidoDummy = new Pedido("nome", 250.00);
        PagamentoService pagamentoService = mock(PagamentoService.class);
        NotificacaoService notificacaoService = mock(NotificacaoService.class);
        
        PedidoService pedidoService = new PedidoService(pagamentoService, notificacaoService);

        // When        
        when(pagamentoService.processarPagamento(pedidoDummy)).thenReturn(true);
        boolean resultado = pedidoService.processarPedido(pedidoDummy);

        // Then
        verify(notificacaoService, atLeast(1)).enviarConfirmacao(pedidoDummy);
        assertTrue(resultado);
    }

    @Test
    public void testProcessarPedidoComFalha() {
        // Given 
        Pedido pedidoDummy = new Pedido("nome", 250.00);
        PagamentoService pagamentoService = mock(PagamentoService.class);
        NotificacaoService notificacaoService = mock(NotificacaoService.class); 

        PedidoService pedidoService = new PedidoService(pagamentoService, notificacaoService);

        // When
        when(pagamentoService.processarPagamento(pedidoDummy)).thenReturn(false);
        boolean resultado = pedidoService.processarPedido(pedidoDummy);

        // Then
        verify(notificacaoService, never()).enviarConfirmacao(pedidoDummy);
        assertFalse(resultado);
    }
}
