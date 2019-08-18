package backend;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;

public class EntryManager {

    private static EntryManager entryManager = new EntryManager();

    private EntryManager(){
        loadSerializedData();
    }

    private ArrayList<Entry> entriesList = new ArrayList<>();

    public static EntryManager getInstance(){
        return entryManager;
    }

    // aggiunge Ingresso singolo
    public Entry addEntry(Calendar calendar, double price) {
        User user = LoginManager.getInstance().getLoggedUser();
        if (user != null && user.hasPermission(Permissions.WriteEntry)) {
            Entry entry = new Entry(calendar, price);
            entriesList.add(entry);

            Collections.sort(entriesList);
            return entry;
        }

        throw new UnauthorizedException(user, Permissions.WriteEntry);

    }

    // aggiunge ingresso Abbonato
    public Entry addMemeberEntry(Calendar calendar, Member member) {
        User user = LoginManager.getInstance().getLoggedUser();
        if (user != null && user.hasPermission(Permissions.WriteEntry)) {
            Entry memberEntries = new Entry(calendar, member);
            entriesList.add(memberEntries);
            member.addMemberEntry(memberEntries);

            Collections.sort(entriesList);
            return memberEntries;
        }

        throw new UnauthorizedException(user, Permissions.WriteEntry);

    }

    // ritorna entriesList
    public ArrayList<Entry> getEntriesList(){
        return entriesList;
    }

    // ottiene ultimi 10 ingressi
    public ArrayList<Entry> getLast10Entries() {
        User user = LoginManager.getInstance().getLoggedUser();
        if (user != null && user.hasPermission(Permissions.ReadEntry)) {
            return new ArrayList<>(entriesList.subList(Math.max(entriesList.size() - 10, 0), entriesList.size()));
        }

        throw new UnauthorizedException(user, Permissions.ReadEntry);
    }

    // ottiene lista ingressi in mese specifico
    public ArrayList<Entry> getMonthlyEntriesList(int month){
        User user = LoginManager.getInstance().getLoggedUser();
        if (user != null && user.hasPermission(Permissions.ReadEntry)){
            ArrayList<Entry> monthlyEntries = new ArrayList<>();
            for (Entry entry : entriesList){
                if (entry.getMonth() == month) {
                    monthlyEntries.add(entry);
                }
        }
        return monthlyEntries;

        }

        throw new UnauthorizedException(user, Permissions.ReadEntry);
    }

    // ottiene lista ingressi in giorno specifico
    public ArrayList<Entry> getDailyEntriesList(int day, int month){
        User user = LoginManager.getInstance().getLoggedUser();
        if (user != null && user.hasPermission(Permissions.ReadEntry)){
            ArrayList<Entry> dailyEntries = new ArrayList<>();
            for (Entry entry : entriesList){
                if (entry.getMonth() == month && entry.getDay() == day){
                    dailyEntries.add(entry);
                }
            }
            return dailyEntries;
        }

        throw new UnauthorizedException(user, Permissions.ReadEntry);
    }

    // calcola Fatturato su lista ingressi presa come argomento
    private double getRevenue(ArrayList<Entry> entriesListToBill) {
        double revenue = 0;
        for (Entry entry : entriesListToBill){
            revenue += entry.getPrice();
        } return revenue;
    }

    // ottiene fatturato totale
    public double getTotalRevenue(){
        return getRevenue(entriesList);
    }

    // ottiene fatturato mensile
    public double getMonthlyRevenue(int month){
        ArrayList<Entry> monthlyEntries = getMonthlyEntriesList(month);
        return getRevenue(monthlyEntries);
    }

    // ottiene fatturato giornaliero
    public double getDailyRevenue(int day, int month){
        ArrayList<Entry> dailyEntries = getDailyEntriesList(day, month);
        return getRevenue(dailyEntries);
    }

    // importa entriesList da serializzazione
    private void loadSerializedData() {
        try {
            FileInputStream fileIn = new FileInputStream("arr_ingressi.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            entriesList = (ArrayList<Entry>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
