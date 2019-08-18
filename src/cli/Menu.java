package cli;

import backend.LoginManager;
import backend.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;
import java.util.function.BiConsumer;

public abstract class Menu {
    User user;

    Menu() {
        checkLogin();
        user = LoginManager.getInstance().getLoggedUser();
    }

    public abstract void run();

    public abstract void handleMenuOptions(int option);

    // Pulisce il terminale con il comando clear nel caso ci si trovi su un sistema unix-like
    void clearScreen() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("nix") || os.contains("mac") || os.contains("linux")){
                Process p = Runtime.getRuntime().exec("clear");

                // Si veda https://stackoverflow.com/questions/14915319/get-output-of-terminal-command-using-java
                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                System.out.println(reader.readLine());
            }
        } catch (IOException e){e.printStackTrace();}
    }

    private void checkLogin() {
        String username, password;
        while(LoginManager.getInstance().getLoggedUser() == null) {
            System.out.println(MenuStrings.loginUsername);
            username = new Scanner(System.in).nextLine();

            System.out.println(MenuStrings.loginPassword);
            password = new Scanner(System.in).nextLine();

            if (!LoginManager.getInstance().doLogin(username, password)) {
                System.out.println(MenuStrings.loginFailed);
            }
        }
    }
}
