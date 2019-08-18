package cli;

import backend.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ShowEntriesSubMenu extends SubMenu {

    @Override
    public void run(){
        clearScreen();
        System.out.println("Digita il numero corrispondente all'azione desiderata, poi premi invio:");
        String menu = MenuStrings.memberShowEntrySubMenu;
        if (user instanceof Employee || user instanceof Admin) {
            menu = MenuStrings.employeeShowEntrySubMenu;
        }

        System.out.println(menu);
        handleMenuOptions(new Scanner(System.in).nextInt());
    }

    @Override
    public void handleMenuOptions(int option){
        if (user instanceof Employee || user instanceof Admin) {
            switch(option) {
                case 1:
                    // visualizza lista ingressi completa
                    fullEntriesList();
                    break;
                case 2:
                    // visualizza ultimi 10 ingressi:
                    last10Entries();
                case 3:
                    // visualizza ingressi in data(Mese)
                    monthlyEntriesList();
                    break;
                case 4:
                    // visualizza ingressi in data(Giorno)
                    dailyEntriesList();
                    break;
                case 5:
                    // visualizza lista ingressi abbonato
                    memberEntriesList();
                case 6:
                    // Esci
                    returnToMainMenu();
                    break;
                default:
                    // rinvia l'user al metodo run
                    System.out.println(MenuStrings.badChoice);
                    run();
            }

        } else {
            switch (option) {
                case 1:
                    // visualizza lista ingressi user
                    userEntriesList();
                    break;
                case 2:
                    // visualizza ultimi 10 ingressi
                    last10EntriesUser();
                    break;
                case 3:
                    // visualizza ultimi ingresso user
                    lastEntryUser();
                    break;
                default:
                    // rinvia l'user al metodo run
                    System.out.println(MenuStrings.badChoice);
                    run();
            }
        }

        backToMenuOrHome();
    }

    private void last10Entries(){
        System.out.println("Ultimi 10 ingressi effettuati in data - ora:");
        EntryManager initEntries = EntryManager.getInstance();
        ArrayList<Entry> last10Entries = initEntries.getLast10Entries();

        for (Entry entry : last10Entries
        ) {
            System.out.println(entry.showFullDate());

        }
    }

    private void fullEntriesList(){
        System.out.println("Lista completa ingressi in data - ora:");
        EntryManager initIngressi = EntryManager.getInstance();
        for (Entry entry : initIngressi.getEntriesList()) {
            System.out.println(entry.showFullDate());
        }
    }

    private void monthlyEntriesList(){
        EntryManager initIngressi = EntryManager.getInstance();
        System.out.println("Inserire il mese desiderato (es. 1:Gennaio, 2:Febbratio, ecc.):");
        try {
            int mese = new Scanner(System.in).nextInt();
            ArrayList<Entry> listaIngressiMese = initIngressi.getMonthlyEntriesList(mese);

            for (Entry entry : listaIngressiMese) {
                System.out.println(entry.showFullDate());
            }
        } catch (Exception e){
            System.out.println(MenuStrings.badInput);
        }
    }

    private void dailyEntriesList(){
        EntryManager initIngressi = EntryManager.getInstance();
        System.out.println("Inserisci data desiderata (dd/mm/yyyy):");
        String data = new Scanner(System.in).nextLine();
        try {
            String[] dataSplit = data.split("/");

            ArrayList<Entry> listaIngressiGiorno = initIngressi.getDailyEntriesList(Integer.parseInt(dataSplit[0]),
                    Integer.parseInt(dataSplit[1]));

            for (Entry entry : listaIngressiGiorno) {
                System.out.println(entry.showFullDate());
            }

            System.out.println(MenuStrings.redirectToHome);
            new Scanner(System.in).nextLine();
            returnToMainMenu();
        } catch (Exception e){
            System.out.println(MenuStrings.badInput);
        }
    }

    private void memberEntriesList(){
        UserManager initUtente = UserManager.getInstance();
        System.out.println("Inserisci ID abbonato (es. AbbMolinari, AbbPedrotti):");
        String id = new Scanner(System.in).nextLine();
        try {
            if (initUtente.getUsersMap().get(id) == null) {
                System.out.println(MenuStrings.badUser);
                System.out.println(MenuStrings.badInput);
                new Scanner(System.in).nextLine();
                returnToMainMenu();
            }
            ArrayList<Entry> listaIngressiUtente = initUtente.getMemberEntriesList(id);

            for (Entry entry : listaIngressiUtente){
                System.out.println(entry.showFullDate());
            }
        } catch (Exception e){
            System.out.println(MenuStrings.badInput);
        }
    }

    private void userEntriesList(){
        UserManager initUtente = UserManager.getInstance();
        Member member = (Member) user;
        String id = member.getID();
        ArrayList<Entry> listaIngressiUtente = initUtente.getMemberEntriesList(id);
        for (Entry entry : listaIngressiUtente){
            System.out.println(entry.showFullDate());
        }
    }

    private void last10EntriesUser(){
        UserManager initUtente = UserManager.getInstance();
        Member member = (Member) user;
        String id = member.getID();
        ArrayList<Entry> listaIngressiUtente = initUtente.getMemberEntriesList(id);

        ArrayList<Entry> lista10IngressiUtente = new ArrayList<>(listaIngressiUtente.subList(Math.max(listaIngressiUtente.size() - 10, 0),
                listaIngressiUtente.size()));

        for (Entry entry : lista10IngressiUtente){
            System.out.println(entry.showFullDate());
        }
    }

    private void lastEntryUser(){
        UserManager initUtente = UserManager.getInstance();
        Member member = (Member) user;
        String id = member.getID();
        ArrayList<Entry> listaIngressiUtente = initUtente.getMemberEntriesList(id);

        Entry ultimo = listaIngressiUtente.get(listaIngressiUtente.size()-1);
        System.out.println(ultimo.showFullDate());
    }
}

