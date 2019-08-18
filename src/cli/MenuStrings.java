package cli;

class MenuStrings {
    // Stringhe Login:
    static String loginUsername = "Inserisci il tuo username: ";
    static String loginPassword = "Inserisci la tua password: ";
    static String loginFailed = "Login fallito, riprova";

    // Stringhe Menu principale:
    static String mainMenu = "#################\n" +
            "Piscina Milazzo\nBenvenuto %s!\n#############\n\n" +
            "Digita il numero corrispondente all'azione desiderata, poi premi invio\n" +
            "%s";
    static String memberMenu = "1) Visualizza informazioni profilo;\n2) Visualizza ingressi\n3) Esci";
    static String employeeMenu = "1) Gestisci ingressi;\n2) Gestisci abbonamenti;\n" +
            "3) Visualizza ingressi;\n4) Fatturato;\n5) Salva ed esci";
    static String adminMenu = "1) Gestisci ingressi;\n2) Gestisci abbonamenti;\n" +
            "3) Visualizza ingressi;\n4) Fatturato;\n5) Gestisci impiegati;\n6) Salva ed esci";

    // Stringhe Exception:
    static String badChoice = "Il numero digitato non corrisponde a nessuna opzione disponibile, riprovare";
    static String badInput = "L'input inserito non rispetta il formato richiesto.";
    static String badUser = "User insesitente";
    static String unauthorized = "Non possiedi dei permessi necessari per eseguire l'operazione";

    // Stringhe redirect:
    static String backOrHome = "Digita 1 e premi invio per tornare al menù precedente oppure premi invio e torna al menù" +
            " principale";
    static String redirectToHome = "Premi invio per tornare al menù principale\n";

    // Stringhe farewell:
    static String farewell = "Ciao!";

    // Stringhe SubMenu Profilo:
    static String profileName = "Nome: %s\n";
    static String profileSurname = "Cognome: %s\n";
    static String profileBirthDate = "Data di nascita: %s\n";

    // Stringhe SubMenu GestisciIngressi:
    static String employeeEntriesSubMenu = "1) Aggiungi ingresso abbonato;\n2) Aggiungi ingresso singolo;\n" +
            "3) Esci";

    // Stringhe SubMenu VisualizzaIngressi:
    static String memberShowEntrySubMenu = "1) Visualizza la tua lista ingressi;\n2) Visualizza i tuoi ultimi 10 ingressi;\n" +
            "3) Visualizza il tuo ultimo ingresso;\n4) Esci";
    static String employeeShowEntrySubMenu = "1) Visualizza lista ingressi;\n2) Visualizza ultimi 10 ingressi;\n" +
            "3) Visualizza lista ingressi in mese specifico;\n4) Visualizza lista ingressi in giorno specifico;\n5) Visualizza ingressi abbonato\n" +
            "6) Esci";

    // Stringhe SubMenu ManageMemberSubMenu:
    static String manageMemberSubMenu = "1) Registra nuovo abbonato;\n2) Visualizza lista abbonati";
    static String subscribeMemberName = "Inserisci nome nuovo abbonato:";
    static String subscribeMemberSurname = "Inserisci cognome nuovo abbonato:";
    static String subscribeMemberBirthDate = "Inserisci l'anno di nascita del nuovo abbonato (yyyy):";
    static String subscribeMemberMembershipDate = "Inserisci la stagione di abbonamento (es. 2018, se l'abbonamento è valido per il 2018):";

    // Stringhe SubMenu ManageEmployeeSubMenu:
    static String manageEmployeeSubMenu = "1) Registra nuovo impiegato;\n2) Visualizza lista impiegati";
    static String addEmployeeName = "Inserisci nome nuovo impiegato:";
    static String addEmployeeSurname = "Inserisci cognome nuovo impiegato:";
    static String addEmployeeBirthDate = "Inserisci l'anno di nascita del nuovo impiegato:";

    // Stinghe SubMenu InserisciIngresso;
    static String memberName = "Inserisci ID (es. \"AbbPedrotti\") Member:";
    static String timeEntry = "Inserisci orario ingresso (HH:MM):";
    static String dateEntry = "Inserisci data ingresso (dd/mm/yyyy):";
    static String entryPrice = "Inserisci il prezzo dell'ingresso (es. 9,99):";
    static String entryAdded = "Ingresso aggiunto con successo, dettagli:";

    static String employeeRevenueSubMenu = "1) Visualizza fatturato totale\n2) Visualizza fatturato per giorno specifico\n" +
            "3) Visualizza fatturato per mese specifico\n4) Esci";

    static String setPassword = "Inserire la password desiderata:";
}