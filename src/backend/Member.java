package backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Member extends User {

    private int membershipDate;
    private String ID;
    private ArrayList<Entry> memberEntriesList = new ArrayList<>();
    private static String generalId = "Abb";
    private static HashMap<Permissions, Boolean> permission = Permissions.getDefaultPermissions();

    public Member(String name, String surname, int birthDate, int membershipDate, String password) {
        super(name, surname, birthDate, permission, password);

        this.ID = generalId + surname;
        this.membershipDate = membershipDate;
    }

    public String getID() {
        return ID;
    }

    private int getMembershipDate() {
        return membershipDate;
    }

    public void showMemberInfo() {
        System.out.println(getName() + " " + getSurname() + " - Abbonamento valido per la stagione: " + getMembershipDate());
    }

    public void addMemberEntry(Entry entry) {
        memberEntriesList.add(entry);
        Collections.sort(memberEntriesList);
    }

    public ArrayList<Entry> getMemberEntriesList(){
        return memberEntriesList;
    }
}
