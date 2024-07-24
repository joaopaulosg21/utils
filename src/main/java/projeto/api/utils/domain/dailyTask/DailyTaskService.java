package projeto.api.utils.domain.dailyTask;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projeto.api.utils.domain.user.User;

@Service
@RequiredArgsConstructor
public class DailyTaskService {

    private final DailyTaskRepository repository;

    public DailyTask create(User user, DailyTaskRegisterDataDTO data) {
        DailyTask dailyTask = new DailyTask(data,user);
        return repository.save(dailyTask);
    }
}
