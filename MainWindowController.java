package application;

import java.time.LocalDate;

//Import-Anweisungen für JavaFX-Klassen
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

//Controller-Klasse für das Hauptfenster
public class MainWindowController {
	
	// FXML-Annotationen, um UI-Komponenten an den Controller zu binden
	@FXML public Label datum;
	@FXML public Label beschreibung;
	@FXML public Label Betrag;
	
	// Label zur Anzeige des aktuellen Saldo
	@FXML public Label saldo;
	// Variable zur Verfolgung des aktuellen Saldo, initialisiert mit 0,0
	private double currentsaldo = 0.0;

	// Button zum Hinzufügen eines neuen Datensatzes	
	@FXML public Button AddButton;
	
	// Textfelder für die Benutzereingabe
	@FXML public DatePicker DatumFeld;
	@FXML public TextField BeschreibungFeld;
	@FXML public TextField BetragFeld;
	
	// ComboBox zum Auswählen des Transaktionstyps
	@FXML public ComboBox<String> combobox;
	
	// TableView zur Anzeige von Datensaetzen
	@FXML public TableView<Record> table;

	// Tabellenspalten für die verschiedenen Attribute der Datensaetze
	@FXML public TableColumn<Record, String> DatumColumn;
	@FXML public TableColumn<Record, String> BeschreibungColumn;
	@FXML public TableColumn<Record, Double> BetragColumn;
	@FXML public TableColumn<Record, String> TypColumn;
	
	// Initialisierungsmethode, die beim Start des Programms aufgerufen wird
	@FXML public void initialize() {
		
		// ComboBox mit den Optionen "Ausgaben" und "Einkommen" fuellen
		combobox.getItems().addAll("Ausgaben" , "Einkommen");
		combobox.setValue("Ausgaben");
		
		// Listener fuer das Betrag-Textfeld, um nur numerische Eingaben zu akzeptieren
		 BetragFeld.textProperty().addListener((observable, oldValue, newValue) -> {
		        if (!newValue.matches("\\d*\\.?\\d*")) {
		            BetragFeld.setText(oldValue);
		        }
		    });
		
		// Spalten mit den entsprechenden Attributen verknuepfen
	    DatumColumn.setCellValueFactory(new PropertyValueFactory<>("DatumColumn"));
	    BeschreibungColumn.setCellValueFactory(new PropertyValueFactory<>("BeschreibungColumn"));
	    BetragColumn.setCellValueFactory(new PropertyValueFactory<>("BetragColumn"));
	    TypColumn.setCellValueFactory(new PropertyValueFactory<>("TypColumn"));
	    
	    // Zentrieren des Texts in den Tabellenspalten
	    DatumColumn.setStyle("-fx-alignment: CENTER");
	    BeschreibungColumn.setStyle("-fx-alignment: CENTER");
	    BetragColumn.setStyle("-fx-alignment: CENTER");
	    TypColumn.setStyle("-fx-alignment: CENTER");
	    
	    // PRUEFEN MUSS HIER
	    
	 // Tabelle mit einer leeren Liste von Datensaetzen initialisieren
	    table.setItems(FXCollections.observableArrayList());
	}
	
	// Methode zum Verarbeiten des Klicks auf den Hinzufuegen-Button
	@FXML public void changeevent(ActionEvent e) {
	    String selectedTyp = combobox.getValue(); 
	    double betragValue = Double.parseDouble(BetragFeld.getText());
	    LocalDate selectedDate = DatumFeld.getValue();
	    
	    // Saldo basierend auf dem Transaktionstyp aktualisieren
	    if(selectedTyp.equals("Ausgaben")) {
	    	currentsaldo -= betragValue;
	    } else {
	    	currentsaldo += betragValue;	
	    }
	    
	    // Saldo-Label aktualisieren
	    saldo.setText(String.valueOf("Saldo : " + currentsaldo + " €"));
	    
	   // Fehlermeldungen anzeigen, wenn Felder leer sind
	   
	   if(DatumFeld.getValue() == null) {
		   Alert alert = new Alert(AlertType.ERROR);
		   alert.setTitle("ERROR");
		   alert.setHeaderText(null);
		   alert.setContentText("Datum Feld ist Leer");
		   alert.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
		   alert.showAndWait();
	   }
	   
	   if(BeschreibungFeld.getText().isEmpty()) {
		   Alert alert = new Alert(AlertType.ERROR);
		   alert.setTitle("ERROR");
		   alert.setHeaderText(null);
		   alert.setContentText("Beschreibung Feld ist Leer");
		   alert.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
		   alert.showAndWait();
	   }
	   
	   if(BetragFeld.getText().isEmpty()) {
		   Alert alert = new Alert(AlertType.ERROR);
		   alert.setTitle("ERROR");
		   alert.setHeaderText(null);
		   alert.setContentText("Betrag Feld ist Leer");
		   alert.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
		   alert.showAndWait();
	   }
	   
	   
	    // Neuen Datensatz erstellen und zur Tabelle hinzufuegen
	    Record record = new Record(selectedDate ,BeschreibungFeld.getText(), betragValue, selectedTyp);
	    table.getItems().add(record);
	    
	    // Textfelder leere
	    DatumFeld.setValue(null);
	   
	    BeschreibungFeld.clear();
	    BetragFeld.clear();
		//checking the changes

		
		    
	}

	
	
	
	

}
