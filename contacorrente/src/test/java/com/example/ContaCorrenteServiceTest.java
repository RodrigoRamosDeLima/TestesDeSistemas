package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContaCorrenteServiceTest{
    private ContaCorrente maria;
    private ContaCorrente jose;

    @BeforeEach
    public void setUp() {
        maria = new ContaCorrente("Maria", 200);
        jose = new ContaCorrente("José", 100);
    }

    @Test
    public void testContasCriadasCorretamente() {
        assertNotNull(maria);
        assertNotNull(jose);
    }

    @Test
    public void testSaldoDiferente() {
        assertNotEquals(maria.getSaldo(), jose.getSaldo());
    }

    @Test
    public void testSaqueMaria() {
        maria.saque(100);
        assertEquals(jose.getSaldo(), maria.getSaldo());
    }

    @Test
    public void testDepositoJose() {
        jose.deposito(50);
        assertEquals(maria.getSaldo() + 50, jose.getSaldo());
    }

    @Test
    public void testSaqueAcimaDoSaldo() {
        assertFalse(maria.saque(300)); // Tentativa de saque maior que o saldo
    }

    @Test
    public void testSaqueDisponivel() {
        assertTrue(jose.saque(50)); // Saque válido
        assertEquals(50, jose.getSaldo()); // Saldo após o saque
    }
}
