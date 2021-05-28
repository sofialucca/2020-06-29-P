/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.PremierLeague;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.PremierLeague.model.Adiacenti;
import it.polito.tdp.PremierLeague.model.Match;
import it.polito.tdp.PremierLeague.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnConnessioneMassima"
    private Button btnConnessioneMassima; // Value injected by FXMLLoader

    @FXML // fx:id="btnCollegamento"
    private Button btnCollegamento; // Value injected by FXMLLoader

    @FXML // fx:id="txtMinuti"
    private TextField txtMinuti; // Value injected by FXMLLoader

    @FXML // fx:id="cmbMese"
    private ComboBox<String> cmbMese; // Value injected by FXMLLoader

    @FXML // fx:id="cmbM1"
    private ComboBox<Match> cmbM1; // Value injected by FXMLLoader

    @FXML // fx:id="cmbM2"
    private ComboBox<Match> cmbM2; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doConnessioneMassima(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Coppie con connessione massima:\n");
    	for(Adiacenti a : model.getConnessioni()) {
    		txtResult.appendText("\n" + a.toString());
    	}
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	if(!isValid()) {
    		return;
    	}
    	
    	int min = Integer.parseInt(this.txtMinuti.getText());
    	int mese = 0;
    	switch(this.cmbMese.getValue()) {
    		case "Gennaio":
    			mese =1;
    			break;
    		case "Febbraio":
    			mese =2;
    			break;
    		case "Marzo":
    			mese = 3;
    			break;
    		case "Aprile":
    			mese = 4;
    			break;
    		case "Maggio":
    			mese = 5;
    			break;
    		case "Giugno":
    			mese = 6;
    			break;
    		case "Luglio":
    			mese = 7;
    			break;
    		case "Agosto":
    			mese = 8;
    			break;
    		case "Settembre":
    			mese = 9;
    			break;
    		case "Ottobre":
    			mese = 10;
    			break;
    		case "Novembre":
    			mese = 11;
    			break;
    		case "Dicembre":
    			mese = 12;
    			break;
    	}
    	
    	
    	model.creaGrafo(mese, min);
    	List<Match> vertici = model.getVertex();
    	txtResult.appendText("GRAFO CREATO:\n");
    	txtResult.appendText("#VERTICI: " + vertici.size());
    	txtResult.appendText("\n#ARCHI: " + model.getEdgeSize());
    	this.btnConnessioneMassima.setDisable(false);
    	this.btnCollegamento.setDisable(false);
    	this.cmbM1.setDisable(false);
    	this.cmbM2.setDisable(false);
    	this.cmbM1.getItems().setAll(vertici);
    	this.cmbM1.getItems().setAll(vertici);
    	
    }

    private boolean isValid() {
		String minuti = this.txtMinuti.getText();
		boolean check = true;
		if(this.cmbMese.getValue() == null) {
			txtResult.appendText("ERRORE: scegliere un mese\n");
			check = false;
		}
		//System.out.println(minuti);
		if(minuti.equals("")) {
			txtResult.appendText("ERRORE: inserire un valore per i minuti\n");
		}else {
			try {
				int min = Integer.parseInt(minuti);
				if(min<1 || min>90) {
					txtResult.appendText("ERRORE: minuti non possono essere maggiori di 90 o minori di 1\n");
					check = false;
				}
			}catch(NullPointerException npe) {
				txtResult.appendText("ERRORE: inserire un numero intero compreso tra 1 e 90\n");
				check = false;
			}			
		}

		return check;
	}

	@FXML
    void doCollegamento(ActionEvent event) {
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnConnessioneMassima != null : "fx:id=\"btnConnessioneMassima\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCollegamento != null : "fx:id=\"btnCollegamento\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMinuti != null : "fx:id=\"txtMinuti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbMese != null : "fx:id=\"cmbMese\" was not injected: check your FXML file 'Scene.fxml'.";        assert cmbM1 != null : "fx:id=\"cmbM1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbM2 != null : "fx:id=\"cmbM2\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
        ObservableList<String> mesi= FXCollections.observableArrayList("Gennaio","Febbraio", "Marzo", "Aprile", "Maggio","Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre");
        this.cmbMese.setItems(mesi);
  
    }
    
    
}
