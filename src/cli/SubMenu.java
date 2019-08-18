package cli;

import java.util.Scanner;

public abstract class SubMenu extends Menu{
    void promptThenReturnToMainMenu() {
        System.out.println(MenuStrings.redirectToHome);
        new Scanner(System.in).nextLine();
        returnToMainMenu();
    }

    void backToMenuOrHome() {
        System.out.println(MenuStrings.backOrHome);
        String input = new Scanner(System.in).nextLine();
        if (input.equals("1")) {
            this.run();
        } else {
            returnToMainMenu();
        }
    }

    void returnToMainMenu() {
        new MainMenu().run();
    }
}
