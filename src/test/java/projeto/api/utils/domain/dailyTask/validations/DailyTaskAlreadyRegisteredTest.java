package projeto.api.utils.domain.dailyTask.validations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import projeto.api.utils.domain.dailyTask.DailyTask;
import projeto.api.utils.domain.dailyTask.DailyTaskRegisterDataDTO;
import projeto.api.utils.domain.dailyTask.DailyTaskRepository;
import projeto.api.utils.domain.dailyTask.Priority;
import projeto.api.utils.domain.user.User;
import projeto.api.utils.infra.exception.ValidationException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DailyTaskAlreadyRegisteredTest {

    private DailyTaskAlreadyRegistered dailyTaskAlreadyRegistered;

    @Mock
    private DailyTaskRepository repository;

    @BeforeEach
    void setup() {
        this.dailyTaskAlreadyRegistered = new DailyTaskAlreadyRegistered(repository);
    }

    @Test
    void validTest() {
        DailyTaskRegisterDataDTO data = new DailyTaskRegisterDataDTO("test name","description test",
                LocalDateTime.now().plusHours(1), Priority.LOW);
        User user = new User();

        when(repository.findByNameAndExpirationDateAndUser(anyString(),any(LocalDateTime.class),any(User.class)))
                .thenReturn(Optional.of(new DailyTask()));

        ValidationException exception = Assertions.assertThrows(ValidationException.class,
                () -> dailyTaskAlreadyRegistered.valid(data,user));

        Assertions.assertEquals("Daily Task already registered with this name and expiration date",exception.getMessage());
    }
}