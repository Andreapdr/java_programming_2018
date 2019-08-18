package backend;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class UserManager {

    private UserManager(){
        loadSerializedData();
    }

    private static UserManager userManager = new UserManager();

    private HashMap<String, User> usersMap = new HashMap<>();

    private HashMap<String, Member> membersMap = new HashMap<>();

    private HashMap<String, Admin> adminsMap = new HashMap<>();

    public static UserManager getInstance() {
        return userManager;
    }

    public Member subscribeMember(String name, String surname, int birthDate, int membershipDate, String password) {
        User user = LoginManager.getInstance().getLoggedUser();
        if (user != null && user.hasPermission(Permissions.AppendMembers)) {
            Member member = new Member(name, surname, birthDate, membershipDate, password);

            membersMap.put(member.getID(), member);
            usersMap.put(member.getID(), member);

            return member;
        }

        throw new UnauthorizedException(user, Permissions.AppendMembers);

    }

    public Employee addEmployee(String name, String surname, int birthDate, String password) {
        User user = LoginManager.getInstance().getLoggedUser();
        if (user != null && user.hasPermission(Permissions.WriteEmployees)) {
            Employee employee = new Employee(name, surname, birthDate, password);
            usersMap.put(employee.getID(), employee);
            return employee;
        }

        throw new UnauthorizedException(user, Permissions.WriteEmployees);
    }

    public Admin addAdmin(String name, String surname, int birthDate, String password) {
        User user = LoginManager.getInstance().getLoggedUser();
        if (user != null && user.hasPermission(Permissions.AppendAdmin)) {
            Admin admin = new Admin(name, surname, birthDate, password);
            adminsMap.put(admin.getId(), admin);
            usersMap.put(admin.getId(), admin);
            return  admin;
        }

        throw new UnauthorizedException(user, Permissions.AppendAdmin);
    }

    public HashMap<String, Member> getMembersMap() {
        return membersMap;
    }

    public HashMap<String, User> getUsersMap() {
        return usersMap;
    }

    public ArrayList<Entry> getMemberEntriesList(String id){
        User user = LoginManager.getInstance().getLoggedUser();
        if (user != null && user.hasPermission(Permissions.ReadEntry)){
            return membersMap.get(id).getMemberEntriesList();
        }
        throw new UnauthorizedException(user, Permissions.ReadEntry);
    }

    private void loadSerializedData() {
        // Deserializza Utenti
        try {
            FileInputStream fileIn = new FileInputStream("hashmap_utenti.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            usersMap = (HashMap) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Deserializza Abbonati
        try {
            FileInputStream fileIn = new FileInputStream("hashmap_abbonati.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            membersMap = (HashMap) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
