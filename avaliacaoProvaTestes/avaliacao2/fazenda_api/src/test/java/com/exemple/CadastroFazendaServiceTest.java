package com.exemple;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.CadastroFazendaService;
import com.example.Fazenda;
import com.example.FazendaApiService;

public class CadastroFazendaServiceTest {
    
    private FazendaApiService fazendaApiService;
    private CadastroFazendaService cadastroFazendaService;

    @BeforeEach
    public void setUp() {
        fazendaApiService = mock(FazendaApiService.class);
        cadastroFazendaService = new CadastroFazendaService(fazendaApiService);
    }

    @Test
    public void testCadastroFazendaComSucesso() {
        // Given
        String codigo = "12345";
        String nome = "Fazenda João";

        when(fazendaApiService.getLatitude(codigo)).thenReturn(-30.0);
        when(fazendaApiService.getLongitude(codigo)).thenReturn(-50.0);
        when(fazendaApiService.getAreaAgricultavel(codigo)).thenReturn(100.0);
        when(fazendaApiService.getAreaNaoAgricultavel(codigo)).thenReturn(50.0);
        when(fazendaApiService.getCpfProprietario(codigo)).thenReturn("123.456.789-00");

        // When
        Fazenda fazenda = cadastroFazendaService.cadastrarFazenda(codigo, nome);

        // Then
        assertEquals(nome, fazenda.getNome());
        assertEquals(codigo, fazenda.getCodigo());
        assertEquals(150.0, fazenda.getAreaTotal());
        assertEquals(100.0, fazenda.getAreaAgricultavel());
        assertEquals(50.0, fazenda.getAreaNaoAgricultavel());
        assertEquals("123.456.789-00", fazenda.getCpfProprietario());
        assertEquals(-30.0, fazenda.getLatitude());
        assertEquals(-50.0, fazenda.getLongitude());
    }

    @Test
    public void testCadastroFazendaComFalhaRecuperarLatitude() {
        // Given
        String codigo = "12345";
        String nome = "Fazenda João";

        when(fazendaApiService.getLatitude(codigo)).thenThrow(new RuntimeException("Erro ao recuperar latitude"));

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cadastroFazendaService.cadastrarFazenda(codigo, nome);
        });

        assertEquals("Erro ao recuperar latitude", exception.getMessage());
    }

    @Test
    public void testCadastroFazendaComFalhaRecuperarLongitude() {
        // Given
        String codigo = "12345";
        String nome = "Fazenda João";

        when(fazendaApiService.getLongitude(codigo)).thenThrow(new RuntimeException("Erro ao recuperar longitude"));

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cadastroFazendaService.cadastrarFazenda(codigo, nome);
        });

        assertEquals("Erro ao recuperar longitude", exception.getMessage());
    }

    @Test
    public void testCadastroFazendaComFalhaRecuperarAreaAgricultavel() {
        // Given
        String codigo = "12345";
        String nome = "Fazenda João";

        when(fazendaApiService.getAreaAgricultavel(codigo)).thenThrow(new RuntimeException("Erro ao recuperar área agricultável"));

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cadastroFazendaService.cadastrarFazenda(codigo, nome);
        });

        assertEquals("Erro ao recuperar área agricultável", exception.getMessage());
    }

    @Test
    public void testCadastroFazendaComFalhaRecuperarAreaNaoAgricultavel() {
        // Given
        String codigo = "12345";
        String nome = "Fazenda João";

        when(fazendaApiService.getAreaNaoAgricultavel(codigo)).thenThrow(new RuntimeException("Erro ao recuperar área não agricultável"));

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cadastroFazendaService.cadastrarFazenda(codigo, nome);
        });

        assertEquals("Erro ao recuperar área não agricultável", exception.getMessage());
    }

    @Test
    public void testCadastroFazendaComFalhaRecuperarCpfProprietario() {
        // Given
        String codigo = "12345";
        String nome = "Fazenda João";

        when(fazendaApiService.getCpfProprietario(codigo)).thenThrow(new RuntimeException("Erro ao recuperar CPF do proprietário"));

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cadastroFazendaService.cadastrarFazenda(codigo, nome);
        });

        assertEquals("Erro ao recuperar CPF do proprietário", exception.getMessage());
    }
}
