package projeto.api.utils.domain.dailyTask.validations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import projeto.api.utils.domain.dailyTask.DailyTask;
import projeto.api.utils.domain.dailyTask.DailyTaskRegisterDataDTO;
import projeto.api.utils.domain.dailyTask.DailyTaskRepository;
import projeto.api.utils.domain.user.User;
import projeto.api.utils.infra.exception.ValidationException;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DailyTaskAlreadyRegistered implements DailyTaskValidations {

    private final DailyTaskRepository repository;

    @Override
    public void valid(DailyTaskRegisterDataDTO data, User user) {
        Optional<DailyTask> optionalDailyTask = repository.findByNameAndExpirationDateAndUser(data.name(),data.expirationDate(),user);

        if(optionalDailyTask.isPresent()) {
            throw new ValidationException("Daily Task already registered with this name and expiration date");
        }
    }
}
