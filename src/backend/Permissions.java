package backend;

import java.util.HashMap;
import java.util.Map;

public enum Permissions {
    ReadMembers,
    WriteMembers,
    AppendMembers,
    ReadEntry,
    WriteEntry,
    AppendEntries,
    ReadEmployees,
    WriteEmployees,
    AppendEmployees,
    AppendAdmin;

    public static HashMap<Permissions, Boolean> getEmployeePermissions() {
        HashMap<Permissions, Boolean> permissions = getDefaultPermissions();
        permissions.put(Permissions.ReadMembers, true);
        permissions.put(Permissions.WriteMembers, true);
        permissions.put(Permissions.AppendMembers, true);
        permissions.put(Permissions.ReadEntry, true);
        permissions.put(Permissions.WriteEntry, true);
        permissions.put(Permissions.AppendEntries, true);
        permissions.put(Permissions.ReadEmployees, true);
        return permissions;
    }

    public static HashMap<Permissions, Boolean> getAdminPermissions() {
        HashMap<Permissions, Boolean> permissions = getDefaultPermissions();

        // Mette tutti i permessi true, cfr. https://stackoverflow.com/questions/16588492/update-all-values-at-a-time-in-hashmap
        for (Map.Entry<Permissions, Boolean> entry : permissions.entrySet()){
            permissions.put(entry.getKey(), true);
        }

        return permissions;
    }

    public static HashMap<Permissions, Boolean> getDefaultPermissions() {
        HashMap<Permissions, Boolean> permissions = new HashMap<>();
        permissions.put(Permissions.ReadMembers, false);
        permissions.put(Permissions.WriteMembers, false);
        permissions.put(Permissions.AppendMembers, false);
        // gli abbonati possono leggere gli ingressi
        permissions.put(Permissions.ReadEntry, true);
        permissions.put(Permissions.WriteEntry, false);
        permissions.put(Permissions.AppendEntries, false);
        permissions.put(Permissions.ReadEmployees, false);
        permissions.put(Permissions.WriteEmployees, false);
        permissions.put(Permissions.AppendEmployees, false);
        permissions.put(Permissions.AppendAdmin, false);

        return permissions;
    }

}
