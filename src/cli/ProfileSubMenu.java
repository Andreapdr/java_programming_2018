package cli;

public class ProfileSubMenu extends SubMenu{
    @Override
    public void run() {
        clearScreen();
        System.out.println("Di seguito le informazioni sul tuo profilo:");
        System.out.format(MenuStrings.profileName, user.getName());
        System.out.format(MenuStrings.profileSurname, user.getSurname());
        System.out.format(MenuStrings.profileBirthDate, user.getBirthDate());

        promptThenReturnToMainMenu();
    }

    @Override
    public void handleMenuOptions(int option) {

    }
}
