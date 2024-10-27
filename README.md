# Passenger Data Analysis Program

## Overview
This Java program analyzes passenger data from public transportation systems. It processes JSON-formatted passenger count data and provides statistical analysis for different time periods (yearly, quarterly, monthly, and weekly).

## Authors
- Cem Akkaya
- Flower Dan Fluri

## Features
- Load and parse passenger data from JSON files
- Analyze passenger numbers for different time periods:
  - Yearly analysis (YYYY)
  - Quarterly analysis (YYYY-QN)
  - Monthly analysis (YYYY-MM)
  - Weekly analysis (YYYY-WNN)
- Calculate key statistics:
  - Minimum passenger count
  - Maximum passenger count
  - Average passenger count
- Interactive command-line interface
- Data validation and error handling
- Support for different data granularities

## System Requirements
- Java Runtime Environment (JRE) 8 or higher
- Sufficient memory to process large JSON files
- Read access to the input JSON file

## Project Structure
```
src/
├── Main.java              # Main program entry point and user interface
├── DataAnalyzer.java      # Core analysis functionality
├── DataLoader.java        # JSON data parsing and loading
├── PassengerData.java     # Data model class
└── 100075.json           # Sample passenger data file
```

## Installation
1. Clone the repository or download the source files
2. Ensure you have Java installed on your system
3. Place your JSON data file in the `src` directory
4. Compile the Java files:
   ```bash
   javac *.java
   ```

## Usage
1. Run the program:
   ```bash
   java Main
   ```
2. Enter time periods in one of the following formats:
   - Year: `YYYY` (e.g., 2020)
   - Quarter: `YYYY-QN` (e.g., 2020-Q1)
   - Month: `YYYY-MM` (e.g., 2020-02)
   - Week: `YYYY-WNN` (e.g., 2020-W06)
3. Type 'exit' to quit the program

## Input Data Format
The program expects JSON data in the following format:
```json
[
  {
    "startdatum_kalenderwoche_monat": "YYYY-MM-DD",
    "fahrgaeste_einsteiger": number,
    "kalenderwoche": number,
    "granularitat": "string",
    "datum_der_monatswerte": "YYYY-MM"
  }
]
```

## Example Output
```
Analyseergebnis:
Zeitraum: 2020-Q1
Kleinste Fahrgastanzahl: 572000
Grösste Fahrgastanzahl: 2538000
Durchschnittliche Fahrgastanzahl: 1548750.00
```

## Error Handling
The program includes comprehensive error handling for:
- Invalid JSON format
- Missing or corrupted data files
- Invalid date formats
- Invalid user input
- Out-of-range values

## Limitations
- Data range is limited to 2020-02 through 2024-08
- Only supports German-formatted JSON input files
- Single file processing at a time
- Memory constraints based on available system RAM

## Contributing
To contribute to this project:
1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License
This project is available under the MIT License. See the LICENSE file for more details.

## Support
For support and bug reports, please open an issue in the project's issue tracker.
