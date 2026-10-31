import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SmartScannerApp {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Map<String, ItemInfo> itemDatabase = createItemDatabase();
        List<String> scanHistory = new ArrayList<>();
        boolean running = true;

        System.out.println("=== SmartScanner ===");
        System.out.println("Scanner-simulering som gir informasjon om ting.");

        while (running) {
            printMenu();
            int choice = readInt("Velg et alternativ: ");

            switch (choice) {
                case 1:
                    scanItem(itemDatabase, scanHistory);
                    break;
                case 2:
                    showKnownItems(itemDatabase);
                    break;
                case 3:
                    showScanHistory(scanHistory);
                    break;
                case 0:
                    running = false;
                    System.out.println("Avslutter SmartScanner.");
                    break;
                default:
                    System.out.println("Ugyldig valg. Proev igjen.");
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Scan en ting");
        System.out.println("2. Vis ting appen kjenner");
        System.out.println("3. Vis scan-historikk");
        System.out.println("0. Avslutt");
    }

    private static Map<String, ItemInfo> createItemDatabase() {
        Map<String, ItemInfo> database = new LinkedHashMap<>();

        ItemInfo headphones = new ItemInfo(
                "Traadloese hodetelefoner",
                "Elektronikk",
                "Brukes til musikk, samtaler og video.",
                "Har batteri og maa lades med USB-C.",
                "Leveres som EE-avfall naar de ikke virker lenger.",
                "Ikke lad dem hele natten hvis de allerede er fulle.");

        ItemInfo waterBottle = new ItemInfo(
                "Vannflaske",
                "Hverdagsutstyr",
                "Brukes til aa ha med vann paa skole, jobb eller trening.",
                "Kan vaskes og brukes mange ganger.",
                "Sorteres som plast eller metall, avhengig av materiale.",
                "Vask flasken ofte for aa unngaa daarlig lukt.");

        ItemInfo laptop = new ItemInfo(
                "Baerbar PC",
                "Datamaskin",
                "Brukes til skolearbeid, programmering, nett og dokumenter.",
                "Inneholder skjerm, tastatur, prosessor, minne og lagring.",
                "Leveres som EE-avfall. Batteriet skal ikke kastes i vanlig restavfall.",
                "Hold operativsystem og programmer oppdatert.");

        addSearchKeys(database, headphones, "1001", "hodetelefoner", "headset", "traadloese hodetelefoner");
        addSearchKeys(database, waterBottle, "1002", "flaske", "vannflaske");
        addSearchKeys(database, laptop, "1003", "pc", "laptop", "baerbar pc");

        return database;
    }

    private static void addSearchKeys(Map<String, ItemInfo> database, ItemInfo item, String... keys) {
        for (String key : keys) {
            database.put(normalize(key), item);
        }
    }

    private static void scanItem(Map<String, ItemInfo> itemDatabase, List<String> scanHistory) {
        String input = readText("Scan eller skriv inn kode/navn: ");
        String searchKey = normalize(input);
        ItemInfo item = itemDatabase.get(searchKey);

        if (item == null) {
            System.out.println("Fant ingen informasjon om: " + input);
            System.out.println("Proev for eksempel 1001, vannflaske eller laptop.");
            return;
        }

        System.out.println();
        System.out.println("Scan fullfoert.");
        item.printInfo();
        scanHistory.add(item.getName());
    }

    private static void showKnownItems(Map<String, ItemInfo> itemDatabase) {
        List<String> shownNames = new ArrayList<>();

        System.out.println();
        System.out.println("Appen kjenner disse tingene:");

        for (ItemInfo item : itemDatabase.values()) {
            if (!shownNames.contains(item.getName())) {
                shownNames.add(item.getName());
                System.out.println("- " + item.getName());
            }
        }
    }

    private static void showScanHistory(List<String> scanHistory) {
        if (scanHistory.isEmpty()) {
            System.out.println("Ingen ting er scannet ennå.");
            return;
        }

        System.out.println();
        System.out.println("Scan-historikk:");

        for (int i = 0; i < scanHistory.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, scanHistory.get(i));
        }
    }

    private static String normalize(String text) {
        return text.trim().toLowerCase();
    }

    private static String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);

            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                scanner.nextLine();
                return number;
            }

            scanner.nextLine();
            System.out.println("Skriv inn et heltall.");
        }
    }
}

class ItemInfo {
    private final String name;
    private final String category;
    private final String description;
    private final String technicalInfo;
    private final String recyclingInfo;
    private final String advice;

    public ItemInfo(
            String name,
            String category,
            String description,
            String technicalInfo,
            String recyclingInfo,
            String advice) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.technicalInfo = technicalInfo;
        this.recyclingInfo = recyclingInfo;
        this.advice = advice;
    }

    public String getName() {
        return name;
    }

    public void printInfo() {
        System.out.println("Navn: " + name);
        System.out.println("Kategori: " + category);
        System.out.println("Beskrivelse: " + description);
        System.out.println("Teknisk info: " + technicalInfo);
        System.out.println("Resirkulering: " + recyclingInfo);
        System.out.println("Raad: " + advice);
    }
}
