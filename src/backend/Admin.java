package backend;

import java.util.HashMap;

public class Admin extends User {

    private static HashMap<Permissions, Boolean> permissions = Permissions.getAdminPermissions();

    private String Id;
    private static String generalId = "Amm";

    public Admin(String name, String surname, int birthDate, String password) {
        super(name, surname, birthDate, permissions, password);
        this.Id = generalId + surname;
    }

    public String getId() {
        return Id;
    }
}
