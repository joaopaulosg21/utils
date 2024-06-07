package projeto.api.utils.domain.list;

import projeto.api.utils.domain.user.UserDataDetails;

import java.util.List;

public record ListDataDetails(Long id, String name, List<Item> items, UserDataDetails user) {
}
