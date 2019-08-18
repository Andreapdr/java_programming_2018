import backend.UserManager;
import cli.MainMenu;

public class Main {


    public static void main(String[] args) {

        // inizilizzazione Admin/Impiegati di default
//        UserManager initUser = UserManager.getInstance();
//        initUser.addAdmin("Paolo", "Milazzo", 1979, "Java2018");
//        initUser.addEmployee("Andrea", "Pedrotti", 1993, "Java2018");
//        initUser.addEmployee("Alessio", "Molinari", 1993, "Java2018");

        runCli();

    }

    private static void runCli() {
        new MainMenu().run();
    }

}




