/**
 * DataLoader-Klasse zum Laden und Parsen von Fahrgastdaten aus einem JSON-String
 *
 * Diese Klasse verarbeitet JSON-formatierte Fahrgastdaten und konvertiert sie
 * in eine Liste von PassengerData-Objekten.
 *
 * @author Cem Akkaya & Flower Dan Fluri
 * @version 1.0
 */
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    private String jsonData;

    /**
     * Konstruktor für den DataLoader
     * @param jsonData Der JSON-String, der die Fahrgastdaten enthält
     */
    public DataLoader(String jsonData) {
        this.jsonData = jsonData;
    }

    /**
     * Lädt die Fahrgastdaten aus dem JSON-String
     * @return Eine Liste von PassengerData-Objekten oder null im Fehlerfall
     */
    public List<PassengerData> loadData() {
        List<PassengerData> data = new ArrayList<>();
        try {
            String json = jsonData.trim();

            if (!json.startsWith("[") || !json.endsWith("]")) {
                throw new IllegalArgumentException("Invalid JSON format: Expected array");
            }

            json = json.substring(1, json.length() - 1);
            List<String> jsonObjects = splitJsonObjects(json);

            for (String jsonObject : jsonObjects) {
                PassengerData passengerData = parsePassengerData(jsonObject);
                if (passengerData != null) {
                    data.add(passengerData);
                }
            }

        } catch (Exception e) {
            System.err.println("Fehler beim Laden der Daten: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

        if (data.isEmpty()) {
            System.err.println("Warnung: Keine Daten geladen.");
        }
        return data;
    }

    /**
     * Teilt einen JSON-String in einzelne JSON-Objekte auf
     * @param json Der zu teilende JSON-String
     * @return Eine Liste von JSON-Objekt-Strings
     */
    private List<String> splitJsonObjects(String json) {
        List<String> objects = new ArrayList<>();
        int depth = 0;
        StringBuilder currentObject = new StringBuilder();

        for (char c : json.toCharArray()) {
            if (c == '{') {
                depth++;
            } else if (c == '}') {
                depth--;
            }

            currentObject.append(c);

            if (depth == 0 && currentObject.length() > 0) {
                if (currentObject.toString().trim().startsWith("{")) {
                    objects.add(currentObject.toString().trim());
                }
                currentObject = new StringBuilder();
            }
        }

        return objects;
    }

    /**
     * Parst ein einzelnes JSON-Objekt in ein PassengerData-Objekt
     * @param json Das zu parsende JSON-Objekt als String
     * @return Ein PassengerData-Objekt oder null im Fehlerfall
     */
    private PassengerData parsePassengerData(String json) {
        try {
            String startDate = extractValue(json, "startdatum_kalenderwoche_monat");
            String passengersStr = extractValue(json, "fahrgaeste_einsteiger");
            String calendarWeekStr = extractValue(json, "kalenderwoche");
            String granularity = extractValue(json, "granularitat");
            String monthlyDate = extractValue(json, "datum_der_monatswerte");

            if (startDate == null || passengersStr == null) {
                return null;
            }

            int passengers = Integer.parseInt(passengersStr);
            Integer calendarWeek = null;
            if (calendarWeekStr != null && !calendarWeekStr.equals("null")) {
                calendarWeek = Integer.parseInt(calendarWeekStr);
            }

            return new PassengerData(startDate, passengers, calendarWeek, granularity, monthlyDate);
        } catch (Exception e) {
            System.err.println("Fehler beim Parsen des JSON-Objekts: " + json);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Extrahiert einen Wert aus einem JSON-Objekt anhand des Schlüssels
     * @param json Das JSON-Objekt als String
     * @param key Der zu suchende Schlüssel
     * @return Der gefundene Wert oder null
     */
    private String extractValue(String json, String key) {
        int start = json.indexOf("\"" + key + "\"");
        if (start == -1) return null;

        start = json.indexOf(":", start) + 1;
        while (start < json.length() && Character.isWhitespace(json.charAt(start))) {
            start++;
        }

        int end;
        if (json.charAt(start) == '"') {
            // String-Wert
            start++;
            end = json.indexOf("\"", start);
            if (end == -1) return null;
        } else {
            // Numerischer Wert oder null
            end = json.indexOf(",", start);
            if (end == -1) {
                end = json.indexOf("}", start);
            }
            if (end == -1) return null;
        }

        String value = json.substring(start, end).trim();
        return value.equals("null") ? null : value;
    }
}