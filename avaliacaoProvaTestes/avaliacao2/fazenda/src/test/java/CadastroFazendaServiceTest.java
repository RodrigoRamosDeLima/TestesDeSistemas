import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.example.CadastroFazendaService;
import com.example.Fazenda;

public class CadastroFazendaServiceTest {

    private final CadastroFazendaService service = new CadastroFazendaService();

    @Test
    public void testNomeValido() {
        Fazenda fazenda = new Fazenda("Fazenda Teste", 300, "12345678901", 100, 200, 10, 20);
        assertTrue(service.validarFazenda(fazenda));
    }

    @Test
    public void testNomeVazio() {
        Fazenda fazenda = new Fazenda("", 300, "12345678901", 100, 200, 10, 20);
        assertFalse(service.validarFazenda(fazenda));
    }

    @Test
    public void testNomeNulo() {
        Fazenda fazenda = new Fazenda(null, 300, "12345678901", 100, 200, 10, 20);
        assertFalse(service.validarFazenda(fazenda));
    }

    @Test
    public void testAreaValida() {
        Fazenda fazenda = new Fazenda("Fazenda Teste", 300, "12345678901", 100, 200, 10, 20);
        assertTrue(service.validarFazenda(fazenda));
    }

    @Test
    public void testAreaNegativa() {
        Fazenda fazenda = new Fazenda("Fazenda Teste", -100, "12345678901", 100, 200, 10, 20);
        assertFalse(service.validarFazenda(fazenda));
    }

    @Test
    public void testCpfValido() {
        Fazenda fazenda = new Fazenda("Fazenda Teste", 300, "12345678901", 100, 200, 10, 20);
        assertTrue(service.validarFazenda(fazenda));
    }

    @Test
    public void testCpfMenosDe11Digitos() {
        Fazenda fazenda = new Fazenda("Fazenda Teste", 300, "123456789", 100, 200, 10, 20);
        assertFalse(service.validarFazenda(fazenda));
    }

    @Test
    public void testCpfNulo() {
        Fazenda fazenda = new Fazenda("Fazenda Teste", 300, null, 100, 200, 10, 20);
        assertFalse(service.validarFazenda(fazenda));
    }

    @Test
    public void testCpfCaracteresInvalidos() {
        Fazenda fazenda = new Fazenda("Fazenda Teste", 300, "1234567890a", 100, 200, 10, 20);
        assertFalse(service.validarFazenda(fazenda));
    }

    @Test
    public void testLatitudeValida() {
        Fazenda fazenda = new Fazenda("Fazenda Teste", 300, "12345678901", 100, 200, 45, 20);
        assertTrue(service.validarFazenda(fazenda));
    }

    @Test
    public void testLatitudeForaDoIntervalo() {
        Fazenda fazenda = new Fazenda("Fazenda Teste", 300, "12345678901", 100, 200, 100, 20);
        assertFalse(service.validarFazenda(fazenda));
    }

    @Test
    public void testLongitudeValida() {
        Fazenda fazenda = new Fazenda("Fazenda Teste", 300, "12345678901", 100, 200, 10, 150);
        assertTrue(service.validarFazenda(fazenda));
    }

    @Test
    public void testLongitudeForaDoIntervalo() {
        Fazenda fazenda = new Fazenda("Fazenda Teste", 300, "12345678901", 100, 200, 10, 200);
        assertFalse(service.validarFazenda(fazenda));
    }

    @Test
    public void testAreaTotalValida() {
        Fazenda fazenda = new Fazenda("Fazenda Teste", 200, "12345678901", 100, 100, 10, 20);
        assertTrue(service.validarFazenda(fazenda));
    }

    @Test
    public void testAreaTotalInvalida() {
        Fazenda fazenda = new Fazenda("Fazenda Teste", 200, "12345678901", 150, 150, 10, 20);
        assertFalse(service.validarFazenda(fazenda));
    }
}
