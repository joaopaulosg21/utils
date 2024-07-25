package projeto.api.utils.domain.dailyTask.validations;

import projeto.api.utils.domain.dailyTask.DailyTaskRegisterDataDTO;
import projeto.api.utils.domain.user.User;

public interface DailyTaskValidations {
    void valid(DailyTaskRegisterDataDTO data, User user);
}
