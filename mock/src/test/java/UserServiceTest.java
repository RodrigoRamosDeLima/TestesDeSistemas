

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.User;
import com.example.UserRepository;
import com.example.UserService;

public class UserServiceTest {
    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    public void testAddUser_UserDoesNotExist() {
        User user = new User("Maria", "maria@exemplo.com");

        // Configurando o mock para retornar false ao verificar se o usuário existe
        when(userRepository.userExists(user.getEmail())).thenReturn(false);

        String result = userService.addUser(user);

        // Verifica se o retorno é correto e se o usuário foi adicionado
        assertEquals("User added successfully.", result);
        verify(userRepository).addUser(user); // Verifica se addUser foi chamado
    }

    @Test
    public void testAddUser_UserExists() {
        User user = new User("José", "jose@exemplo.com");

        // Configurando o mock para retornar true ao verificar se o usuário existe
        when(userRepository.userExists(user.getEmail())).thenReturn(true);

        String result = userService.addUser(user);

        // Verifica se o retorno é correto e se o usuário não foi adicionado
        assertEquals("User already exists.", result);
        verify(userRepository, never()).addUser(user); // Verifica se addUser NUNCA foi chamado
    }
}
