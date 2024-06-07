package projeto.api.utils.domain.list;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ListRegisterData(@NotBlank String name, @NotNull List<Item> items) {
}
