package projeto.api.utils.domain.user;

import jakarta.validation.constraints.NotBlank;

public record UserRegisterData(
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String password) {
}
