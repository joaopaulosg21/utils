package projeto.api.utils.domain.monthlyexpense;

import java.math.BigDecimal;
import java.time.Month;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import projeto.api.utils.domain.user.User;

@Entity
@Table(name = "monthly_expense")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MonthlyExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Month month;

    private BigDecimal amount;

    private String description;

    @JoinColumn(referencedColumnName = "id", columnDefinition = "user_id")
    @ManyToOne
    private User user;

    public MonthlyExpense(Month month, BigDecimal amount, String description, User user) {
        this.month = month;
        this.amount = amount;
        this.description = description;
        this.user = user;
    }
}
