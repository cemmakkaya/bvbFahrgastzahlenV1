/**
 * Main-Klasse für die Analyse von Fahrgastdaten
 * Diese Klasse enthält das Hauptprogramm zur Analyse von Fahrgastdaten aus einer JSON-Datei
 *
 * Features:
 * - Lädt Fahrgastdaten aus einer JSON-Datei
 * - Ermöglicht die Analyse nach verschiedenen Zeiträumen (Jahr, Quartal, Monat, Woche)
 * - Benutzerinteraktion über Konsoleneingaben
 * - Formatierte Ausgabe der Analyseergebnisse
 *
 * @author Cem Akkaya & Daniel Fluri
 * @version 1.0
 */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {
    /**
     * Der Haupteinstiegspunkt des Programms
     * @param args Kommandozeilenargumente (werden nicht verwendet)
     */
    public static void main(String[] args) {
        String jsonData;
        try {
            // Lese die JSON-Datei
            jsonData = Files.readString(Paths.get("src/100075.json"));
            System.out.println("JSON-Datei erfolgreich geladen.");
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen der JSON-Datei: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // Initialisiere den DataLoader mit den JSON-Daten
        DataLoader loader = new DataLoader(jsonData);
        List<PassengerData> data = loader.loadData();

        if (data == null || data.isEmpty()) {
            System.err.println("Fehler: Keine Daten geladen. Programm wird beendet.");
            System.exit(1);
        }

        System.out.println("Erfolgreich " + data.size() + " Datensätze geladen.");
        DataAnalyzer analyzer = new DataAnalyzer(data);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Programm wird beendet.");
                break;
            }

            if (!isValidInput(input)) {
                System.out.println("Ungültige Eingabe. Bitte verwenden Sie eines der folgenden Formate:");
                System.out.println("- YYYY (z.B. 2020)");
                System.out.println("- YYYY-QN (z.B. 2020-Q1)");
                System.out.println("- YYYY-MM (z.B. 2020-02)");
                System.out.println("- YYYY-WNN (z.B. 2020-W06)");
                continue;
            }

            AnalysisResult result = analyzer.analyzeByPeriod(input);
            System.out.println("\nAnalyseergebnis:");
            System.out.println(result);
            System.out.println();
        }

        scanner.close();
    }

    /**
     * Zeigt das Hauptmenü des Programms an
     */
    private static void printMenu() {
        System.out.println("\n-----------------Version 1-------------------");
        System.out.println("Geben Sie einen Zeitraum ein (Jahr, Quartal, Monat oder Woche):");
        System.out.println("Oder geben Sie 'exit' ein, um das Programm zu beenden.");
        System.out.println("---------------------------------------------");
        System.out.println("Formate:");
        System.out.println("- Jahr: YYYY (z.B. 2020)");
        System.out.println("- Quartal: YYYY-QN (z.B. 2020-Q1)");
        System.out.println("- Monat: YYYY-MM (z.B. 2020-02)");
        System.out.println("- Woche: YYYY-WNN (z.B. 2020-W06)");
        System.out.println("Datensatz geht von 2020-02 bis 2024-08");
        System.out.println("---------------------------------------------");
    }

    /**
     * Überprüft, ob die Benutzereingabe einem gültigen Format entspricht
     * @param input Die zu überprüfende Benutzereingabe
     * @return true wenn das Format gültig ist, false sonst
     */
    private static boolean isValidInput(String input) {
        return input.matches("\\d{4}") ||                    // Jahr
                input.matches("\\d{4}-Q[1-4]") ||            // Quartal
                input.matches("\\d{4}-(0[1-9]|1[0-2])") ||   // Monat
                input.matches("\\d{4}-W(0[1-9]|[1-4]\\d|5[0-3])"); // Woche
    }
}