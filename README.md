# Fahrgastdaten-Analyseprogramm (V1)

## Version 1.0

### Überblick
Dieses Java-Programm analysiert Fahrgastdaten des öffentlichen Nahverkehrs. Es verarbeitet JSON-formatierte Fahrgastzählungen und bietet statistische Analysen für verschiedene Zeiträume.

### Autoren
- Cem Akkaya
- Flower Dan Fluri

### Funktionen
- Laden und Verarbeiten von Fahrgastdaten aus JSON-Dateien
- Analyse von Fahrgastzahlen für verschiedene Zeiträume:
  - Jahresanalyse (JJJJ)
  - Quartalsanalyse (JJJJ-QN)
  - Monatsanalyse (JJJJ-MM)
  - Wochenanalyse (JJJJ-WNN)
- Berechnung wichtiger Statistiken:
  - Minimale Fahrgastzahl
  - Maximale Fahrgastzahl
  - Durchschnittliche Fahrgastzahl
- Interaktive Benutzeroberfläche
- Fehlerbehandlung
- Unterstützung verschiedener Datenstrukturen

### Systemvoraussetzungen
- Java Runtime Environment (JRE) 8 oder höher
- Ausreichend Arbeitsspeicher für die Datenverarbeitung
- Lesezugriff auf die Eingabe-JSON-Datei

### Projektstruktur
```
src/
├── Main.java              # Hauptprogramm und Benutzeroberfläche
├── DataAnalyzer.java      # Kernfunktionalität zur Analyse
├── DataLoader.java        # JSON-Datenverarbeitung
├── PassengerData.java     # Datenmodell
└── 100075.json           # Beispiel-Fahrgastdaten
```

### Installation
1. Repository klonen oder Quelldateien herunterladen
2. Java auf dem System installieren
3. JSON-Datendatei im `src`-Verzeichnis platzieren
4. Java-Dateien kompilieren:
   ```bash
   javac *.java
   ```

### Verwendung
1. Programm starten:
   ```bash
   java Main
   ```
2. Zeiträume in einem der folgenden Formate eingeben:
   - Jahr: JJJJ (z.B. 2020)
   - Quartal: JJJJ-QN (z.B. 2020-Q1)
   - Monat: JJJJ-MM (z.B. 2020-02)
   - Woche: JJJJ-WNN (z.B. 2020-W06)
3. 'exit' eingeben zum Beenden

### Eingabedatenformat
```json
[
  {
    "startdatum_kalenderwoche_monat": "JJJJ-MM-TT",
    "fahrgaeste_einsteiger": nummer,
    "kalenderwoche": nummer,
    "granularitat": "string",
    "datum_der_monatswerte": "JJJJ-MM"
  }
]
```

### Beispielausgabe
```
Analyseergebnis:
Zeitraum: 2020-Q1
Kleinste Fahrgastanzahl: 572000
Grösste Fahrgastanzahl: 2538000
Durchschnittliche Fahrgastanzahl: 1548750.00
```

### Fehlerbehandlung
Das Programm behandelt folgende Fehler:
- Ungültiges JSON-Format
- Fehlende oder beschädigte Dateien
- Ungültige Datumsformate
- Ungültige Benutzereingaben

### Einschränkungen
- Datenzeitraum von 2020-02 bis 2024-08
- Nur deutschsprachige JSON-Eingabedateien
- Einzeldateiverarbeitung
- Arbeitsspeicherbegrenzung durch System
