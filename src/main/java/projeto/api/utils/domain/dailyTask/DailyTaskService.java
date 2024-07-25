package projeto.api.utils.domain.dailyTask;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projeto.api.utils.domain.dailyTask.validations.DailyTaskValidations;
import projeto.api.utils.domain.user.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyTaskService {

    private final DailyTaskRepository repository;

    private final List<DailyTaskValidations> validations;

    public DailyTask create(User user, DailyTaskRegisterDataDTO data) {
        validations.forEach(v -> v.valid(data,user));

        DailyTask dailyTask = new DailyTask(data,user);
        return repository.save(dailyTask);
    }
}
