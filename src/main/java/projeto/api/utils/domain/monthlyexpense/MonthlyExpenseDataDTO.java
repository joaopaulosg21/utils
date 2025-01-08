package projeto.api.utils.domain.monthlyexpense;

import java.math.BigDecimal;
import java.time.Month;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MonthlyExpenseDataDTO(
    @NotNull
    Month month,
    @NotNull
    BigDecimal amount,
    @NotBlank
    String description) {

}
