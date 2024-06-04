package projeto.api.utils.domain.user;

public record UserDataDetails(Long id, String name, String email) {

    public  UserDataDetails(User user) {
        this(user.getId(),user.getName(),user.getEmail());
    }
}
