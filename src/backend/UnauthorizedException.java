package backend;

public class UnauthorizedException extends RuntimeException{
    private User user;
    private Permissions permission;

    public UnauthorizedException(User user, Permissions permissions) {
        super();
        this.user = user;
        this.permission = permissions;
    }

    @Override
    public String getMessage() {
        return String.format("L'user %s non possiede il permission %s necessario", user, permission);
    }
}
