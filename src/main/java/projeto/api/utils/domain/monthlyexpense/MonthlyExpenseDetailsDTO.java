package projeto.api.utils.domain.monthlyexpense;

import java.math.BigDecimal;
import java.time.Month;

import projeto.api.utils.domain.user.User;
import projeto.api.utils.domain.user.UserDataDetails;

public record MonthlyExpenseDetailsDTO(Long id,Month month, BigDecimal amount, String description,
        UserDataDetails userDataDetails) {
    public MonthlyExpenseDetailsDTO(MonthlyExpense expense, User user) {
        this(expense.getId(),expense.getMonth(), expense.getAmount(), expense.getDescription(), new UserDataDetails(user));
    }
}
