package projeto.api.utils.domain.dailyTask;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import projeto.api.utils.domain.user.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DailyTaskRepository extends JpaRepository<DailyTask,Long> {
    Optional<DailyTask> findByNameAndExpirationDateAndUser(String name, LocalDateTime localDateTime, User user);

    Page<DailyTask> findAllByUser(Pageable pageable, User user);
}
