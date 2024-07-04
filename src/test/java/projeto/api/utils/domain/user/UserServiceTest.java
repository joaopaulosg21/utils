package projeto.api.utils.domain.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import projeto.api.utils.domain.user.validations.UserValidations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private UserService service;

    @Mock
    private UserRepository repository;

    @Mock
    private List<UserValidations> validations;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        this.service = new UserService(repository,validations,passwordEncoder);
    }

    @Test
    void registerTest() {
        UserRegisterData data = new UserRegisterData("Test name","test@email.com","123");

        when(repository.save(any())).thenReturn(new User(data,data.password()));

        UserDataDetails details = service.register(data);

        assertThat(details.email()).isEqualTo(data.email());
    }
}