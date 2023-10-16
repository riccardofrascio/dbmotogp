package it.view.impl;

import java.net.URL;
import java.util.ResourceBundle;

import it.controller.ModelController;
import it.utils.Pair;
import it.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class InsertGaraController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField condizioniMeteo;

    @FXML
    private DatePicker data;

    @FXML
    private TextField idGara;
    
    @FXML
    private Button aggiornaPunteggio;

    @FXML
    private TextField idPilota;

    @FXML
    private TextField idPista;

    @FXML
    private Button inserisciGara;

    @FXML
    private Button inserisciRisultati;

    @FXML
    private TextField posizione;

    @FXML
    private TextField punteggio;

    private ModelController modelController;

    @FXML
    void condizioniMeteo(ActionEvent event) {

    }

    @FXML
    void data(ActionEvent event) {

    }

    @FXML
    void idGara(ActionEvent event) {

    }

    @FXML
    void idPilota(ActionEvent event) {

    }

    @FXML
    void idPista(ActionEvent event) {

    }

    @FXML
    void aggiornaPunteggio(ActionEvent event) {
        if(idPilota.getText().length() == 0) {
            MainViewController.showAlertIncorrectData();
        } else if(modelController.findAllPrimaryKey(modelController.getTablePiloti())
                .contains(Integer.parseInt(idPilota.getText()))) {
            modelController.getTablePiloti().updatePunteggio(Integer.parseInt(idPilota.getText()));
            MainViewController.showAlertInsertData();
        }
    }

    @FXML
    void inserisciGara(ActionEvent event) {

        if(condizioniMeteo.getText().length() == 0 || idPista.getText().length() == 0 || 
                idGara.getText().length() == 0 || data.getValue() == null) {
            MainViewController.showAlertIncorrectData();
        } else if(modelController.findAllPrimaryKey(modelController.getTableGare())
                .contains(Integer.parseInt(idGara.getText()))) {
            MainViewController.showAlertAlreadyPresent();
        } else {
            var dataGara = Utils.buildDate(data.getValue().getDayOfMonth(), data.getValue().getMonthValue(), data.getValue().getYear());

            modelController.insertGara(Integer.parseInt(idGara.getText()),
                    dataGara.get(), 
                    condizioniMeteo.getText(), Integer.parseInt(idPista.getText()));
            MainViewController.showAlertInsertData();
        }
    }

    @FXML
    void inserisciRisultati(ActionEvent event) {
        if(idGara.getText().length() == 0 || idPilota.getText().length() == 0 || 
                posizione.getText().length() == 0 || punteggio.getText().length() == 0) {
            MainViewController.showAlertIncorrectData();
        } else if(modelController.findAllPrimaryKey(modelController.getTabelloneGare())
                .contains(new Pair<>(Integer.parseInt(idGara.getText()), Integer.parseInt(idPilota.getText())))) {
            MainViewController.showAlertAlreadyPresent();
        } else if(modelController.getTabelloneGare().getPosizioniFromIdGara(Integer.parseInt(idGara.getText()))
                .contains(Integer.parseInt(posizione.getText()))) {
            MainViewController.showAlertAlreadyPresent();
        } else {
            modelController.insertTabelloneGara(Integer.parseInt(idGara.getText()), Integer.parseInt(idPilota.getText()),
            Integer.parseInt(posizione.getText()), Integer.parseInt(punteggio.getText()));
            MainViewController.showAlertInsertData();
        }
    }

    @FXML
    void posizione(ActionEvent event) {

    }

    @FXML
    void punteggio(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert condizioniMeteo != null : "fx:id=\"condizioniMeteo\" was not injected: check your FXML file 'Untitled'.";
        assert data != null : "fx:id=\"data\" was not injected: check your FXML file 'Untitled'.";
        assert idGara != null : "fx:id=\"idGara\" was not injected: check your FXML file 'Untitled'.";
        assert idPilota != null : "fx:id=\"idPilota\" was not injected: check your FXML file 'Untitled'.";
        assert idPista != null : "fx:id=\"idPista\" was not injected: check your FXML file 'Untitled'.";
        assert inserisciGara != null : "fx:id=\"inserisciGara\" was not injected: check your FXML file 'Untitled'.";
        assert inserisciRisultati != null : "fx:id=\"inserisciRisultati\" was not injected: check your FXML file 'Untitled'.";
        assert posizione != null : "fx:id=\"posizione\" was not injected: check your FXML file 'Untitled'.";
        assert punteggio != null : "fx:id=\"punteggio\" was not injected: check your FXML file 'Untitled'.";

    }

    public void setModelController(ModelController modelController) {
        this.modelController = modelController;
        ViewImpl.addIntListener(idGara);
        ViewImpl.addIntListener(idPilota);
        ViewImpl.addIntListener(idPista);
        ViewImpl.addIntListener(punteggio);
        ViewImpl.addIntListener(posizione);
        data.setEditable(false);
    }
}

