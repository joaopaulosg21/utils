package projeto.api.utils.domain.user.validations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import projeto.api.utils.domain.user.User;
import projeto.api.utils.domain.user.UserRegisterData;
import projeto.api.utils.domain.user.UserRepository;
import projeto.api.utils.infra.exception.ValidationException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmailAlreadyUsedValidationTest {

    @Mock
    private UserRepository repository;

    private EmailAlreadyUsedValidation validation;

    @BeforeEach
    void setup() {
        this.validation = new EmailAlreadyUsedValidation(repository);
    }

    @Test
    void validTest() {
        UserRegisterData data = new UserRegisterData("Test name","test@email.com","123");

        when(repository.findByEmail(anyString())).thenReturn(Optional.of(new User(data,data.password())));

        ValidationException exception = Assertions.assertThrows(ValidationException.class, () -> validation.valid(data));

        assertThat(exception.getMessage()).isEqualTo("Email already used");
    }
}