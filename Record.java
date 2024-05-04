package application;

import java.time.LocalDate;

//Import-Anweisungen für die Eigenschaftsklassen von JavaFX
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

//Die Klasse Record repräsentiert einen Datensatz in der Anwendung
public class Record {
	
	 // Deklaration von Eigenschaften fuer die Spalten der Tabelle
    private final SimpleStringProperty DatumColumn;
    private final SimpleStringProperty BeschreibungColumn;
    private final SimpleDoubleProperty BetragColumn;
    private final SimpleStringProperty TypColumn;

 // Konstruktor der Klasse Record, der die Werte fuer die Eigenschaften initialisiert
    public Record(LocalDate datumColumn, String beschreibungColumn, double betragColumn, String typColumn) {
        this.DatumColumn = new SimpleStringProperty(datumColumn.toString());
        this.BeschreibungColumn= new SimpleStringProperty(beschreibungColumn);
        this.BetragColumn = new SimpleDoubleProperty (betragColumn);
        this.TypColumn = new SimpleStringProperty(typColumn);
    }

    // Getter-Methode fuer die Datum-Spalte
       public String getDatumColumn() {
       return DatumColumn.get();
    }

    // Getter-Methode fuer die Beschreibung-Spalte
    public String getBeschreibungColumn() {
        return BeschreibungColumn.get();
    }

    // Getter-Methode fuer die Betrag-Spalte
    public Double getBetragColumn() {
        return BetragColumn.get();
    }

    // Getter-Methode fuer die Typ-Spalte
    public String getTypColumn() {
        return TypColumn.get();
    }
}
