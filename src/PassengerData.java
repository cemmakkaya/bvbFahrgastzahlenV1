/**
 * PassengerData-Klasse zur Speicherung von Fahrgastdaten
 *
 * Diese Klasse repräsentiert einen einzelnen Datensatz von Fahrgastdaten,
 * bestehend aus Datum, Fahrgastanzahl, Kalenderwoche und weiteren Metadaten.
 *
 * @author Cem Akkaya & Daniel Fluri
 * @version 1.0
 */
public class PassengerData {
    private String startDate;         // Startdatum des Messzeitraums
    private int passengers;           // Anzahl der Fahrgäste
    private Integer calendarWeek;     // Kalenderwoche (optional)
    private String granularity;       // Granularität der Daten (z.B. "Woche", "Monat")
    private String monthlyDate;       // Monatsdatum (optional)

    /**
     * Konstruktor für PassengerData
     * @param startDate Startdatum des Messzeitraums im Format YYYY-MM-DD
     * @param passengers Anzahl der Fahrgäste
     * @param calendarWeek Kalenderwoche (kann null sein)
     * @param granularity Granularität der Daten
     * @param monthlyDate Monatsdatum (kann null sein)
     */
    public PassengerData(String startDate, int passengers, Integer calendarWeek,
                         String granularity, String monthlyDate) {
        this.startDate = startDate;
        this.passengers = passengers;
        this.calendarWeek = calendarWeek;
        this.granularity = granularity;
        this.monthlyDate = monthlyDate;
    }

    /**
     * Gibt das Startdatum zurück
     * @return Startdatum als String
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Gibt die Anzahl der Fahrgäste zurück
     * @return Anzahl der Fahrgäste
     */
    public int getPassengers() {
        return passengers;
    }

    /**
     * Gibt die Kalenderwoche zurück
     * @return Kalenderwoche oder null
     */
    public Integer getCalendarWeek() {
        return calendarWeek;
    }

    /**
     * Gibt die Granularität zurück
     * @return Granularität als String
     */
    public String getGranularity() {
        return granularity;
    }

    /**
     * Gibt das Monatsdatum zurück
     * @return Monatsdatum oder null
     */
    public String getMonthlyDate() {
        return monthlyDate;
    }

    @Override
    public String toString() {
        return "PassengerData{" +
                "startDate='" + startDate + '\'' +
                ", passengers=" + passengers +
                ", calendarWeek=" + calendarWeek +
                ", granularity='" + granularity + '\'' +
                ", monthlyDate='" + monthlyDate + '\'' +
                '}';
    }
}