package backend;

public class LoginManager {

    private static LoginManager loginManager = new LoginManager();
    private User userLoggato;

    // Impedisci che questa classe possa essere istanziata
    private LoginManager(){}

    public static LoginManager getInstance() {
        return loginManager;
    }

    public boolean doLogin(String userId, String password) {
        User user = UserManager.getInstance().getUsersMap().get(userId);
        if (user == null) {
           return false;
        }

        if (!password.equals(user.getPassword())) {
            return false;
        }
        userLoggato = user;
        return true;

    }

    public User getLoggedUser() {
        return userLoggato;
    }

}