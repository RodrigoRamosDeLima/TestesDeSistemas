package com.example.fazenda;

import com.example.fazenda.entities.Fazenda;
import com.example.fazenda.services.FazendaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class FazendaApplicationTests {

    @Autowired
    private FazendaService fazendaService;

    private Fazenda fazendaExistente;

    @BeforeEach
    public void setUp() throws Exception {
        fazendaExistente = new Fazenda(null, "Fazenda Teste", "12345", 100.0, 60.0, 40.0, "12345678901", -15.0, -47.0);
        fazendaExistente = fazendaService.create(fazendaExistente);
    }

    @Test
    void testeCriarFazendaSucesso() throws Exception {
        Fazenda novaFazenda = new Fazenda(null, "Fazenda Nova", "54321", 200.0, 120.0, 80.0, "10987654321", -14.5, -46.5);
        Fazenda fazendaCriada = fazendaService.create(novaFazenda);

        assertNotNull(fazendaCriada.getId());
        assertEquals("Fazenda Nova", fazendaCriada.getNome());
    }

    @Test
    void testeCriarFazendaComDadosIncompletos() {
        Fazenda fazendaInvalida = new Fazenda(null, "Fazenda Incompleta", "54321", null, null, null, null, null, null);

        Exception exception = assertThrows(Exception.class, () -> fazendaService.create(fazendaInvalida));
        assertEquals("Erro de validação no cadastro da fazenda", exception.getMessage());
    }

    @Test
    void testeLerFazendaPorIdSucesso() {
        Fazenda fazenda = fazendaService.read(fazendaExistente.getId());

        assertNotNull(fazenda);
        assertEquals("Fazenda Teste", fazenda.getNome());
    }

    @Test
    void testeLerFazendaPorIdInexistente() {
        assertThrows(NoSuchElementException.class, () -> fazendaService.read(99999L));
    }

    @Test
    void testeAtualizarFazendaSucesso() throws Exception {
        fazendaExistente.setNome("Fazenda Atualizada");

        Fazenda fazendaAtualizada = fazendaService.update(fazendaExistente);

        assertEquals("Fazenda Atualizada", fazendaAtualizada.getNome());
    }

    @Test
    void testeAtualizarFazendaNaoExistente() {
        Fazenda fazendaNaoExistente = new Fazenda(99999L, "Inexistente", "99999", 50.0, 30.0, 20.0, "01234567890", -15.0, -47.0);

        Exception exception = assertThrows(NoSuchElementException.class, () -> fazendaService.update(fazendaNaoExistente));
        assertEquals("Fazenda não encontrada", exception.getMessage());
    }

    @Test
    void testeDeletarFazendaSucesso() {
        fazendaService.delete(fazendaExistente.getId());

        assertThrows(NoSuchElementException.class, () -> fazendaService.read(fazendaExistente.getId()));
    }

    @Test
    void testeDeletarFazendaNaoExistente() {
        assertThrows(NoSuchElementException.class, () -> fazendaService.delete(99999L));
    }
}
