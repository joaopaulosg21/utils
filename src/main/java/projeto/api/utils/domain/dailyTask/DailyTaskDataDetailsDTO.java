package projeto.api.utils.domain.dailyTask;

import projeto.api.utils.domain.user.UserDataDetails;

import java.time.LocalDateTime;

public record DailyTaskDataDetailsDTO(Long id, String name, String description, LocalDateTime createdAt,LocalDateTime expirationDate,Priority priority,
        UserDataDetails user) {
    public DailyTaskDataDetailsDTO(DailyTask task) {
        this(task.getId(),task.getName(), task.getDescription(), task.getCreatedAt(),task.getExpirationDate(),
                task.getPriority(),new UserDataDetails(task.getUser()));
    }

    public DailyTaskDataDetailsDTO(long test, DailyTask task,UserDataDetails details) {
        this(test,task.getName(), task.getDescription(), task.getCreatedAt(),task.getExpirationDate(),
                task.getPriority(),details);
    }
}
