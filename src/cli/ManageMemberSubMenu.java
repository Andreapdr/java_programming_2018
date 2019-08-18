package cli;

import backend.Member;
import backend.UnauthorizedException;
import backend.User;
import backend.UserManager;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;


public class ManageMemberSubMenu extends SubMenu {
    private UserManager userManager;
    private HashMap<String, User> usersMap;
    private HashMap<String, Member> membersMap;

    ManageMemberSubMenu() {
        userManager = UserManager.getInstance();
        usersMap = userManager.getUsersMap();
        membersMap = userManager.getMembersMap();
    }

    @Override
    public void run(){
        clearScreen();
        System.out.println("Digita il numero corrispondente all'azione desiderata, poi premi invio:");
        System.out.println(MenuStrings.manageMemberSubMenu);
        handleMenuOptions(new Scanner(System.in).nextInt());
    }

    @Override
    public void handleMenuOptions(int options){
        switch (options) {
            case 1:
                subscribeNewMember();
                break;
            case 2:
                showMembers();
                break;
            default:
                System.out.println(MenuStrings.badChoice);
                run();
                return;
        }
        backToMenuOrHome();
    }

    private void subscribeNewMember() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println(MenuStrings.subscribeMemberName);
            String name = scanner.nextLine();

            System.out.println(MenuStrings.subscribeMemberSurname);
            String surname = scanner.nextLine();

            // controlla che non esista già un abbonato con lo stesso cognome: evita la sovrascrittura degli utenti.
            if (usersMap.containsKey("Abb" + surname)) {
                System.out.println("Spiacente, utente già esistente.");
                return;
            }

            System.out.println(MenuStrings.subscribeMemberBirthDate);
            int birthDate = scanner.nextInt();

            System.out.println(MenuStrings.subscribeMemberMembershipDate);
            int membershipDate = scanner.nextInt();

            System.out.println(MenuStrings.setPassword);
            // evita che "enter" venga passato come argomento al metodo successivo (nextLine)
            // https://stackoverflow.com/questions/24770531/java-scanner-skips-over-my-last-nextline-request
            scanner.nextLine();
            String password = scanner.nextLine();

            userManager.subscribeMember(name, surname, birthDate, membershipDate, password);
        } catch (InputMismatchException e) {
            System.out.println(MenuStrings.badInput);
        } catch (UnauthorizedException e) {
            System.out.println(MenuStrings.unauthorized);
        }
    }

    private void showMembers() {
        System.out.println("Lista abbonati piscina Monella: nome, cognome, stagione abbonamento:\n");
        for (String key: membersMap.keySet()) {
            membersMap.get(key).showMemberInfo();
        }
    }

}
