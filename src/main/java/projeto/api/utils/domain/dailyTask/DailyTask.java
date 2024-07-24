package projeto.api.utils.domain.dailyTask;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import projeto.api.utils.domain.user.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "daily_task")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public class DailyTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @CreatedDate
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime expirationDate;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @JoinColumn(referencedColumnName = "id", columnDefinition = "user_id")
    @ManyToOne
    private User user;

    public DailyTask(DailyTaskRegisterDataDTO data, User user) {
        this.name = data.name();
        this.description = data.description();
        this.expirationDate = data.expirationDate();
        this.priority = data.priority();
        this.user = user;
    }
}
