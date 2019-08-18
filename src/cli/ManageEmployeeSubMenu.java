package cli;

import backend.UserManager;
import backend.Employee;
import backend.User;

import java.util.HashMap;
import java.util.Scanner;


public class ManageEmployeeSubMenu extends SubMenu {

    @Override
    public void run(){
        clearScreen();
        System.out.println("Digita il numero corrispondente all'azione desiderata, poi premi invio:");
        System.out.println(MenuStrings.manageEmployeeSubMenu);
        handleMenuOptions(new Scanner(System.in).nextInt());
    }

    @Override
    public void handleMenuOptions(int options){
        switch (options) {
            case 1:
                addEmployee();
                break;
            case 2:
                showEmployee();
                break;
            default:
                System.out.println(MenuStrings.badChoice);
                run();
                return;
        }
        backToMenuOrHome();
    }

    private void addEmployee() {
        UserManager initUtente = UserManager.getInstance();
        HashMap<String, User> usersList = initUtente.getUsersMap();
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println(MenuStrings.addEmployeeName);
            String name = scanner.nextLine();

            System.out.println(MenuStrings.addEmployeeSurname);
            String surname = scanner.nextLine();

            // controlla che non esista già un impiegato con lo stesso cognome: evita la sovrascrittura degli utenti.
            if (usersList.containsKey("Imp" + surname)) {
                System.out.println("Spiacente, utente già esistente");
                return;
            }

            System.out.println(MenuStrings.addEmployeeBirthDate);
            int birthDate = scanner.nextInt();
            // evita che "enter" venga passato come argomento al metodo successivo (nextLine):
            // https://stackoverflow.com/questions/24770531/java-scanner-skips-over-my-last-nextline-request
            scanner.nextLine();

            System.out.println(MenuStrings.setPassword);
            String password = scanner.nextLine();

            initUtente.addEmployee(name, surname, birthDate, password);

        } catch (Exception e){
            System.out.println(MenuStrings.badInput);
        }
    }

    private void showEmployee() {
        UserManager initUser = UserManager.getInstance();
        HashMap<String, User> usersList = initUser.getUsersMap();

        for (String key: usersList.keySet()
        ) {
            if (usersList.get(key) instanceof Employee) {
                ((Employee) usersList.get(key)).showMemberInfo();
            }
        }
    }

}