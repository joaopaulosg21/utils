package projeto.api.utils.domain.dailyTask;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DailyTaskRegisterDataDTO(
        @NotBlank
        String name,
        @NotBlank
        String description,
        @Future @NotNull
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime expirationDate,
        @NotNull
        Priority priority
) {
}
