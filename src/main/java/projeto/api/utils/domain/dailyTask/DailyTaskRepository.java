package projeto.api.utils.domain.dailyTask;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.api.utils.domain.user.User;

import java.time.LocalDateTime;
import java.util.Optional;

public interface DailyTaskRepository extends JpaRepository<DailyTask,Long> {
    Optional<DailyTask> findByNameAndExpirationDateAndUser(String name, LocalDateTime localDateTime, User user);
}
