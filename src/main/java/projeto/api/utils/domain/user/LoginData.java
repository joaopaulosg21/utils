package projeto.api.utils.domain.user;

import jakarta.validation.constraints.NotBlank;

public record LoginData(@NotBlank String email, @NotBlank String password) {
}
