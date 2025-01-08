package projeto.api.utils.domain.monthlyexpense;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import projeto.api.utils.domain.user.User;

@Service
@RequiredArgsConstructor
public class MonthlyExpenseService {
    private final MonthlyExpenseRepository repository;

    public MonthlyExpenseDetailsDTO create(User user, MonthlyExpenseDataDTO data) {
        MonthlyExpense monthlyExpense = new MonthlyExpense(data.month(), data.amount(), data.description(), user);

        return new MonthlyExpenseDetailsDTO(repository.save(monthlyExpense), user);
    }
}
