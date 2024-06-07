package projeto.api.utils.domain.list.validations;

import org.springframework.stereotype.Component;
import projeto.api.utils.domain.list.ListRegisterData;
import projeto.api.utils.infra.exception.ValidationException;

@Component
public class ItemsNotNullValidation implements ShoppingListValidations{
    @Override
    public void valid(ListRegisterData data) {
        if(data.items().isEmpty()) {
            throw new ValidationException("List items cannot have null objects");
        }

        data.items().forEach(i -> {
            if(i.getName() == null || i.getQuantity() == null) {
                throw new ValidationException("Item cannot have null value");
            }

            if(i.getName().isEmpty() || i.getQuantity().isEmpty()) {
                throw new ValidationException("Item cannot have blank value");
            }
        });
    }
}
