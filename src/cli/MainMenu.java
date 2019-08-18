package cli;

import backend.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu extends Menu {

    public void run() {
        clearScreen();
        String menu = MenuStrings.memberMenu;
        if (user instanceof Employee) {
            menu = MenuStrings.employeeMenu;
        }
        if (user instanceof Admin) {
            menu = MenuStrings.adminMenu;
        }

        System.out.println(String.format(MenuStrings.mainMenu, user, menu));
        try {
            handleMenuOptions(new Scanner(System.in).nextInt());
        } catch (InputMismatchException e) {
            System.out.println(MenuStrings.badInput);
            run();
        }
    }


    @Override
    public void handleMenuOptions(int option) {
        // MENU Admin
        if (user instanceof Admin) {
            switch (option) {
                case 1:
                    // Gestisci Ingressi (Sottomenu: 1) Aggiungi Entry Member; 2) Aggiungi Entry singolo)
                    new ManageEntriesSubMenu().run();
                    break;
                case 2:
                    // Gestione abbonati
                    new ManageMemberSubMenu().run();
                    break;
                case 3:
                    // Visualizza Ingressi (Sottomenu: 1)Ingressi per mese; 2)Ing. per giorno; 3)Ing. per Member
                    new ShowEntriesSubMenu().run();
                    break;
                case 4:
                    // Visualizza Fatturato (Sottomenu: 1)TOtale; 2)Per giorno; 3)Per mese
                    new RevenueSubMenu().run();
                    break;
                case 5:
                    // Registra nuovo impiegato (Sottomenu: 1)Registra impiegato; 2)Visualizza listaImpiegati)
                    new ManageEmployeeSubMenu().run();
                    break;
                case 6:
                    // Exit
                    save();
                    System.out.println(MenuStrings.farewell);
                    System.exit(0);
                default:
                    // rinvia l'user al metodo run
                    System.out.println(MenuStrings.badChoice);
                    run();
            }
        } else if (user instanceof Employee) {
            switch (option) {
                case 1:
                    // Gestisci Ingressi (Sottomenu: 1) Aggiungi Entry Member; 2) Aggiungi Entry singolo)
                    new ManageEntriesSubMenu().run();
                    break;
                case 2:
                    // Gestione abbonati
                    new ManageMemberSubMenu().run();
                    break;
                case 3:
                    // Visualizza Ingressi (Sottomenu: 1)Ingressi per mese; 2)Ing. per giorno; 3)Ing. per Member
                    new ShowEntriesSubMenu().run();
                    break;
                case 4:
                    // Visualizza Fatturato (Sottomenu: 1)TOtale; 2)Per giorno; 3)Per mese
                    new RevenueSubMenu().run();
                    break;
                case 5:
                    // Exit
                    save();
                    System.out.println(MenuStrings.farewell);
                    System.exit(0);
                default:
                    // rinvia l'user al metodo run
                    System.out.println(MenuStrings.badChoice);
                    run();
            }
        } else {
            // MENU Member
            switch (option) {
                case 1:
                    // Visualizza Dati Profilo user
                    new ProfileSubMenu().run();
                    break;
                case 2:
                    // Visualizza Ingressi
                    new ShowEntriesSubMenu().run();
                    break;
                case 3:
                    // Exit
                    System.out.println(MenuStrings.farewell);
                    System.exit(0);
                default:
                    // rinvia l'user al metodo run
                    System.out.println(MenuStrings.badChoice);
                    run();
            }
        }
    }

    private void save(){

        HashMap<String, User> serUsers = UserManager.getInstance().getUsersMap();
        ArrayList<Entry> serEntries = EntryManager.getInstance().getEntriesList();
        HashMap<String, Member> serMembers = UserManager.getInstance().getMembersMap();

        // Serializzazione Utenti
        try {
            FileOutputStream fileOut = new FileOutputStream("hashmap_utenti.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(serUsers);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Serializzazione listaIngressi
        try {
            FileOutputStream fileOut = new FileOutputStream("arr_ingressi.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(serEntries);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Serializzazione Abbonati
        try {
            FileOutputStream fileOut = new FileOutputStream("hashmap_abbonati.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(serMembers);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

