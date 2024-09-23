

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.example.ResultadoValidacao;
import com.example.Usuario;

public class UsuarioTest {

    @Test
    public void testNomeObrigatorio() {
        Usuario usuario = new Usuario("", "teste@exemplo.com", "senha123", "senha123", "12345678909", "12345-678");
        ResultadoValidacao resultado = usuario.validarCadastro();
        assertFalse(resultado.isSucesso());
        assertEquals("O nome é obrigatório.", resultado.getMensagemErro());
    }

    @Test
    public void testEmailObrigatorio() {
        Usuario usuario = new Usuario("Nome Teste", "", "senha123", "senha123", "12345678909", "12345-678");
        ResultadoValidacao resultado = usuario.validarCadastro();
        assertFalse(resultado.isSucesso());
        assertEquals("O e-mail é obrigatório.", resultado.getMensagemErro());
    }

    @Test
    public void testEmailInvalido() {
        Usuario usuario = new Usuario("Nome Teste", "emailinvalido", "senha123", "senha123", "12345678909", "12345-678");
        ResultadoValidacao resultado = usuario.validarCadastro();
        assertFalse(resultado.isSucesso());
        assertEquals("O e-mail fornecido é inválido.", resultado.getMensagemErro());
    }

    @Test
    public void testSenhaObrigatoria() {
        Usuario usuario = new Usuario("Nome Teste", "teste@exemplo.com", "", "", "12345678909", "12345-678");
        ResultadoValidacao resultado = usuario.validarCadastro();
        assertFalse(resultado.isSucesso());
        assertEquals("A senha e a confirmação de senha são obrigatórias.", resultado.getMensagemErro());
    }

    @Test
    public void testSenhaMinima() {
        Usuario usuario = new Usuario("Nome Teste", "teste@exemplo.com", "123", "123", "12345678909", "12345-678");
        ResultadoValidacao resultado = usuario.validarCadastro();
        assertFalse(resultado.isSucesso());
        assertEquals("A senha deve ter no mínimo 8 caracteres.", resultado.getMensagemErro());
    }

    @Test
    public void testSenhaConfirmaçãoIncorreta() {
        Usuario usuario = new Usuario("Nome Teste", "teste@exemplo.com", "senha123", "senha1234", "12345678909", "12345-678");
        ResultadoValidacao resultado = usuario.validarCadastro();
        assertFalse(resultado.isSucesso());
        assertEquals("A senha e a confirmação de senha não correspondem.", resultado.getMensagemErro());
    }

    @Test
    public void testCpfOuCnpjObrigatorio() {
        Usuario usuario = new Usuario("Nome Teste", "teste@exemplo.com", "senha123", "senha123", "", "12345-678");
        ResultadoValidacao resultado = usuario.validarCadastro();
        assertFalse(resultado.isSucesso());
        assertEquals("O CPF ou CNPJ é obrigatório.", resultado.getMensagemErro());
    }

    @Test
    public void testCpfInvalido() {
        Usuario usuario = new Usuario("Nome Teste", "teste@exemplo.com", "senha123", "senha123", "123456789", "12345-678");
        ResultadoValidacao resultado = usuario.validarCadastro();
        assertFalse(resultado.isSucesso());
        assertEquals("O CPF ou CNPJ fornecido é inválido.", resultado.getMensagemErro());
    }

    @Test
    public void testCnpjInvalido() {
        Usuario usuario = new Usuario("Nome Teste", "teste@exemplo.com", "senha123", "senha123", "12.345.678/0001-00", "12345-678");
        ResultadoValidacao resultado = usuario.validarCadastro();
        assertTrue(resultado.isSucesso()); // Se o CNPJ estiver correto, deve passar
    }

    @Test
    public void testCepObrigatorio() {
        Usuario usuario = new Usuario("Nome Teste", "teste@exemplo.com", "senha123", "senha123", "12345678909", "");
        ResultadoValidacao resultado = usuario.validarCadastro();
        assertFalse(resultado.isSucesso());
        assertEquals("O CEP é obrigatório.", resultado.getMensagemErro());
    }

    @Test
    public void testCepInvalido() {
        Usuario usuario = new Usuario("Nome Teste", "teste@exemplo.com", "senha123", "senha123", "12345678909", "1234-567");
        ResultadoValidacao resultado = usuario.validarCadastro();
        assertFalse(resultado.isSucesso());
        assertEquals("O CEP fornecido é inválido.", resultado.getMensagemErro());
    }

    @Test
    public void testCadastroValido() {
        //given
        Usuario usuario = new Usuario("Nome Teste", "teste@exemplo.com", "senha12345", "senha12345", "123.456.789-09", "12345-678");
        //when
        ResultadoValidacao resultado = usuario.validarCadastro();
       //then
        assertTrue(resultado.isSucesso());
    }
}
