import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.Calculadora;

public class CalculadoraTest {
    @Test
    public void testSoma() {
        Calculadora calc = new Calculadora();
        int resultado = calc.soma(2, 3);
        assertEquals(5, resultado);
    }

    @Test
    public void testSubtracao() {
        Calculadora calc = new Calculadora();
        int resultado = calc.subtracao(5, 3);
        assertEquals(2, resultado);
    }

    @Test
    public void testMultiplicacao() {
        Calculadora calc = new Calculadora();
        int resultado = calc.multiplicacao(4, 3);
        assertEquals(12, resultado);
    }

    @Test
    public void testDivisao() {
        Calculadora calc = new Calculadora();
        int resultado = calc.divisao(6, 3);
        assertEquals(2, resultado);
    }

   
}
