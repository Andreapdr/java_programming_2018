package backend;

import java.io.Serializable;
import java.util.HashMap;

public class User implements Serializable {

    private String name;
    private String surname;
    private int birthDate;
    private HashMap<Permissions, Boolean> permissions;
    private String password;

    public User(String name, String surname, int birthDate, HashMap<Permissions, Boolean> permissions, String password) {

        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.permissions = permissions;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public String getPassword() {
        return password;
    }

    public HashMap<Permissions, Boolean> getPermissions() {
        return permissions;
    }

    public boolean hasPermission(Permissions permission) {
        return permissions.get(permission);
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

}
