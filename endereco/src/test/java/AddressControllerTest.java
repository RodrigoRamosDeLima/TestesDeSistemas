
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.Address;
import com.example.AddressController;
import com.example.AddressService;

public class AddressControllerTest {
    private AddressController addressController;
    private AddressService addressService;

    @BeforeEach
    public void setUp() {
        addressService = Mockito.mock(AddressService.class);
        addressController = new AddressController(addressService);
    }

    @Test
    public void testGetAddress_ValidCep() {
        String validCep = "12345678";
        Address expectedAddress = new Address("Rua Exemplo", "Cidade Exemplo", "Estado Exemplo");

        // Configurando o mock para retornar um endereço válido para o CEP fornecido
        when(addressService.getAddressByCep(validCep)).thenReturn(expectedAddress);

        // Chamando o método e verificando o resultado
        Address actualAddress = addressController.getAddress(validCep);
        
        assertNotNull(actualAddress);
        assertEquals(expectedAddress.getStreet(), actualAddress.getStreet());
        assertEquals(expectedAddress.getCity(), actualAddress.getCity());
        assertEquals(expectedAddress.getState(), actualAddress.getState());
    }

    @Test
    public void testGetAddress_InvalidCep() {
        String invalidCep = "00000000";

        // Configurando o mock para retornar null para um CEP inválido
        when(addressService.getAddressByCep(invalidCep)).thenReturn(null);

        // Chamando o método e verificando o resultado
        Address actualAddress = addressController.getAddress(invalidCep);
        
        assertNull(actualAddress); // Verifica se o endereço retornado é nulo
    }
}
