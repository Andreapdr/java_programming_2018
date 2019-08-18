package cli;

import backend.Entry;
import backend.EntryManager;
import backend.UserManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ManageEntriesSubMenu extends SubMenu {
    private UserManager initUser;
    private EntryManager initEntries;
    private Calendar calendar;
    private SimpleDateFormat sdf;

    ManageEntriesSubMenu() {
        super();
        initUser = UserManager.getInstance();
        initEntries = EntryManager.getInstance();
        calendar = Calendar.getInstance();
        sdf = new SimpleDateFormat("H:m d/M/y");
    }

    @Override
    public void run() {
        clearScreen();
        System.out.println("Digita il numero corrispondente all'azione desiderata, poi premi invio:");
        System.out.println(MenuStrings.employeeEntriesSubMenu);
        handleMenuOptions(new Scanner(System.in).nextInt());
    }

    @Override
    public void handleMenuOptions(int option) {
        switch (option) {
            case 1:
                memberEntry();
                break;
            case 2:
                nonMemberEntry();
                break;
            case 3:
                returnToMainMenu();
            default:
                System.out.println(MenuStrings.badChoice);
                run();
                return;
        }
        backToMenuOrHome();
    }

    private void memberEntry() {
        String id;
        try {
            String[] timeDate = askTimeDate();
            calendar.setTime(sdf.parse(timeDate[0] + " " + timeDate[1]));

            do {
                id = askMember();
            } while(id == null);

            Entry entry = initEntries.addMemeberEntry(calendar, initUser.getMembersMap().get(id));
            System.out.println("Ingresso abbonato aggiunto: " + entry);
        } catch (ParseException e){
            System.out.println("ERRORE INTERNO!!!");
        }
    }

    private void nonMemberEntry() {
        Double price;
        try {
            String[] timeDate = askTimeDate();
            String time = timeDate[0] + " " + timeDate[1];
            calendar.setTime(sdf.parse(time));

            do {
                price = askPrice();
            } while (price == null);
            Entry entry = initEntries.addEntry(calendar, price);
            System.out.println(MenuStrings.entryAdded);
            System.out.println(entry);
        } catch (ParseException e) {
            System.out.println("ERRORE INTERNO!!!\n\nPremi invio per continuare");
            new Scanner(System.in).nextLine();
        }

    }

    private String[] askTimeDate(){
        String time, date;
        do {
            time = askTime();
        } while (time == null);

        do {
            date = askDate();
        } while (date == null);

        return new String[]{time, date};
    }

    private String askTime() {
        System.out.println(MenuStrings.timeEntry);
        String time = new Scanner(System.in).nextLine();
        if (!time.matches("[0-9]{2}:[0-9]{2}")) {
            System.out.println(MenuStrings.badInput);
            return null;
        }
        return time;
    }

    private String askDate() {
        System.out.println(MenuStrings.dateEntry);
        String date = new Scanner(System.in).nextLine();
        if (!date.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
            System.out.println(MenuStrings.badInput);
            return null;
        }
        return date;
    }

    private String askMember() {
        System.out.println(MenuStrings.memberName);
        String id = new Scanner(System.in).nextLine();
        if (!id.matches("Abb[A-Z][a-z]+")){
            System.out.println(MenuStrings.badInput);
            return null;
        }
        if (initUser.getMembersMap().get(id) == null){
            System.out.println(MenuStrings.badUser);
            return null;
        }
        return id;
    }

    private Double askPrice() {
        try {
            System.out.println(MenuStrings.entryPrice);
            return new Scanner(System.in).nextDouble();
        } catch (InputMismatchException e) {
            return null;
        }
    }
}
