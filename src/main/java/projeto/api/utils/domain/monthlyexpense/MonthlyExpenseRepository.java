package projeto.api.utils.domain.monthlyexpense;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto.api.utils.domain.user.User;

public interface MonthlyExpenseRepository extends JpaRepository<MonthlyExpense, Long> {
    public List<MonthlyExpense> findAllByUser(User user);
}
