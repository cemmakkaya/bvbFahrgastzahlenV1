/**
 * DataAnalyzer-Klasse zur Analyse von Fahrgastdaten
 *
 * Diese Klasse analysiert Fahrgastdaten für verschiedene Zeiträume und berechnet
 * statistische Kennzahlen wie Minimum, Maximum und Durchschnitt der Fahrgastzahlen.
 *
 * @author Cem Akkaya & Daniel Fluri
 * @version 1.0
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class DataAnalyzer {
    private List<PassengerData> data;

    /**
     * Konstruktor für den DataAnalyzer
     * @param data Liste der zu analysierenden Fahrgastdaten
     */
    public DataAnalyzer(List<PassengerData> data) {
        this.data = data;
    }

    /**
     * Analysiert die Fahrgastdaten für einen bestimmten Zeitraum
     * @param period Der zu analysierende Zeitraum (Format: YYYY, YYYY-QN, YYYY-MM oder YYYY-WNN)
     * @return Ein AnalysisResult-Objekt mit den Analyseergebnissen
     */
    public AnalysisResult analyzeByPeriod(String period) {
        List<PassengerData> filteredData = filterByPeriod(period);

        if (filteredData.isEmpty()) {
            return new AnalysisResult(0, 0, 0, period);
        }

        int minPassengers = getMinPassengers(filteredData);
        int maxPassengers = getMaxPassengers(filteredData);
        double avgPassengers = getAveragePassengers(filteredData);

        return new AnalysisResult(minPassengers, maxPassengers, avgPassengers, period);
    }

    /**
     * Filtert die Daten nach dem angegebenen Zeitraum
     * @param period Der Zeitraum für die Filterung
     * @return Eine gefilterte Liste von PassengerData-Objekten
     */
    private List<PassengerData> filterByPeriod(String period) {
        List<PassengerData> filteredData = new ArrayList<>();
        for (PassengerData d : data) {
            if (matchesPeriod(d, period)) {
                filteredData.add(d);
            }
        }
        return filteredData;
    }

    /**
     * Prüft, ob ein Datensatz zu einem bestimmten Zeitraum gehört
     * @param d Der zu prüfende Datensatz
     * @param period Der zu prüfende Zeitraum
     * @return true wenn der Datensatz zum Zeitraum gehört, false sonst
     */
    private boolean matchesPeriod(PassengerData d, String period) {
        if (d.getStartDate() == null) {
            return false;
        }

        LocalDate date;
        try {
            date = LocalDate.parse(d.getStartDate(), DateTimeFormatter.ISO_DATE);
        } catch (DateTimeParseException e) {
            System.err.println("Ungültiges Datum: " + d.getStartDate());
            return false;
        }

        if (period.matches("\\d{4}")) { // Jahr
            return date.getYear() == Integer.parseInt(period);
        } else if (period.matches("\\d{4}-Q[1-4]")) { // Quartal
            int year = Integer.parseInt(period.substring(0, 4));
            int quarter = Integer.parseInt(period.substring(6));
            return date.getYear() == year && ((date.getMonthValue() - 1) / 3 + 1) == quarter;
        } else if (period.matches("\\d{4}-\\d{2}")) { // Monat
            return d.getStartDate().startsWith(period) ||
                    (d.getMonthlyDate() != null && d.getMonthlyDate().startsWith(period));
        } else if (period.matches("\\d{4}-W\\d{2}")) { // Woche
            return d.getStartDate().startsWith(period.substring(0, 4)) &&
                    d.getCalendarWeek() != null &&
                    d.getCalendarWeek() == Integer.parseInt(period.substring(6));
        }
        return false;
    }

    /**
     * Ermittelt die minimale Fahrgastanzahl aus den gefilterten Daten
     * @param filteredData Die gefilterten Daten
     * @return Die minimale Fahrgastanzahl
     */
    private int getMinPassengers(List<PassengerData> filteredData) {
        int min = Integer.MAX_VALUE;
        for (PassengerData d : filteredData) {
            if (d.getPassengers() < min) {
                min = d.getPassengers();
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    /**
     * Ermittelt die maximale Fahrgastanzahl aus den gefilterten Daten
     * @param filteredData Die gefilterten Daten
     * @return Die maximale Fahrgastanzahl
     */
    private int getMaxPassengers(List<PassengerData> filteredData) {
        int max = Integer.MIN_VALUE;
        for (PassengerData d : filteredData) {
            if (d.getPassengers() > max) {
                max = d.getPassengers();
            }
        }
        return max == Integer.MIN_VALUE ? 0 : max;
    }

    /**
     * Berechnet die durchschnittliche Fahrgastanzahl aus den gefilterten Daten
     * @param filteredData Die gefilterten Daten
     * @return Die durchschnittliche Fahrgastanzahl
     */
    private double getAveragePassengers(List<PassengerData> filteredData) {
        if (filteredData.isEmpty()) {
            return 0;
        }
        long sum = 0;
        for (PassengerData d : filteredData) {
            sum += d.getPassengers();
        }
        return (double) sum / filteredData.size();
    }
}

/**
 * Klasse zur Speicherung der Analyseergebnisse
 */
class AnalysisResult {
    private final int minPassengers;
    private final int maxPassengers;
    private final double avgPassengers;
    private final String period;

    /**
     * Konstruktor für das AnalysisResult
     * @param minPassengers Minimale Fahrgastanzahl
     * @param maxPassengers Maximale Fahrgastanzahl
     * @param avgPassengers Durchschnittliche Fahrgastanzahl
     * @param period Analysierter Zeitraum
     */
    public AnalysisResult(int minPassengers, int maxPassengers, double avgPassengers, String period) {
        this.minPassengers = minPassengers;
        this.maxPassengers = maxPassengers;
        this.avgPassengers = avgPassengers;
        this.period = period;
    }

    /**
     * Gibt die minimale Fahrgastanzahl zurück
     * @return Minimale Fahrgastanzahl
     */
    public int getMinPassengers() {
        return minPassengers;
    }

    /**
     * Gibt die maximale Fahrgastanzahl zurück
     * @return Maximale Fahrgastanzahl
     */
    public int getMaxPassengers() {
        return maxPassengers;
    }

    @Override
    public String toString() {
        return String.format("Zeitraum: %s%nKleinste Fahrgastanzahl: %d%nGrösste Fahrgastanzahl: %d%nDurchschnittliche Fahrgastanzahl: %.2f",
                period, minPassengers, maxPassengers, avgPassengers);
    }
}