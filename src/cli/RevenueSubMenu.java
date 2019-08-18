package cli;

import backend.EntryManager;

import java.util.Scanner;

public class RevenueSubMenu extends SubMenu {
    private EntryManager entryManager;

    RevenueSubMenu(){
        super();
        entryManager = EntryManager.getInstance();
    }

    @Override
    public void run(){
        clearScreen();
        String menu = MenuStrings.employeeRevenueSubMenu;

        System.out.println("Digita il numero corrispondente all'azione desiderata, poi premi invio:");
        System.out.println(menu);
        handleMenuOptions(new Scanner(System.in).nextInt());

    }


    @Override
    public void handleMenuOptions(int option) {
        switch (option) {
            case 1:
                totalRevenue();
                break;
            case 2:
                dailyRevenue();
                break;
            case 3:
                monthlyRevenue();
                break;
            case 4:
                // Exit
                returnToMainMenu();
            default:
                System.out.println(MenuStrings.badChoice);
                run();
                return;
        }
        backToMenuOrHome();
    }

    private void totalRevenue() {
        System.out.println("Fatturato totale: "+ entryManager.getTotalRevenue() + " $$");
    }

    private void dailyRevenue() {
        System.out.println("Inserisci data desiderata (dd/mm/yyyy):");
        String data = new Scanner(System.in).nextLine();
        String[] dataSplit = data.split("/");
        try {
            System.out.println("Fatturato totale: " + entryManager.getDailyRevenue(Integer.parseInt(dataSplit[0]),
                    Integer.parseInt(dataSplit[1])) + " $$");

        } catch (Exception e) {
            System.out.println(MenuStrings.badInput);
            new Scanner(System.in).nextLine();
            returnToMainMenu();
        }
    }

    private void monthlyRevenue() {
        System.out.println("Inserire il mese desiderato (es. 1:Gennaio, 2:Febbratio, ecc.):");
        int month = new Scanner(System.in).nextInt();

        try {
            System.out.println("Fatturato totale per il mese selezionato: " + entryManager.getMonthlyRevenue(month) + " $$");
            System.out.println(MenuStrings.redirectToHome);
            new Scanner(System.in).nextLine();
            returnToMainMenu();
        } catch (Exception e) {
            System.out.println(MenuStrings.badInput);
            new Scanner(System.in).nextLine();
            returnToMainMenu();
        }
    }
}
