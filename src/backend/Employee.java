package backend;

import java.util.HashMap;

public class Employee extends User {

    private String ID;

    private static String generalId = "Imp";
    private static HashMap<Permissions, Boolean> permissions = Permissions.getEmployeePermissions();

    public Employee(String name, String surname, int birthDate, String password){
        super(name, surname, birthDate, permissions, password);
        this.ID = generalId + surname;
    }

    public String getID() {
        return ID;
    }

    public void showMemberInfo() {
        System.out.println(getName() + " " + getSurname());
    }

}
