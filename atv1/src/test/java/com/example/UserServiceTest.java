import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.service.UserService;
import com.example.service.UserRepository;

public class UserServiceTest {
    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    public void testAddUser_UserDoesNotExist() {
        // Given
        User user = new User("Maria", "maria@example.com");

        // When
        when(userRepository.userExists(user.getEmail())).thenReturn(false);
        String result = userService.addUser(user);

        // Then
        verify(userRepository, times(1)).addUser(user);
        assertEquals("User added successfully.", result);
    }

    @Test
    public void testAddUser_UserExists() {
        // Given
        User user = new User("José", "jose@example.com");

        // When
        when(userRepository.userExists(user.getEmail())).thenReturn(true);
        String result = userService.addUser(user);

        // Then
        verify(userRepository, never()).addUser(user);
        assertEquals("User already exists.", result);
    }
}
