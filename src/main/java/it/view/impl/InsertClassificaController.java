package it.view.impl;

import java.net.URL;
import java.util.ResourceBundle;

import it.controller.ModelController;
import it.utils.Pair;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class InsertClassificaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField anno;

    @FXML
    private TextField idPilota;

    @FXML
    private Button inserisci;

    @FXML
    private TextField punti;

    private ModelController modelController;

    @FXML
    void anno(ActionEvent event) {

    }

    @FXML
    void idPilota(ActionEvent event) {

    }

    @FXML
    void inserisci(ActionEvent event) {
        
        if(idPilota.getText().length() == 0 || anno.getText().length() == 0 || punti.getText().length() == 0) {
            MainViewController.showAlertIncorrectData();
        } else if(modelController.findAllPrimaryKey(modelController.getTableClassifica())
                .contains(new Pair<>(Integer.parseInt(idPilota.getText()), Integer.parseInt(anno.getText())))) {
            MainViewController.showAlertAlreadyPresent();
        } else {
            modelController.insertClassifica(Integer.parseInt(idPilota.getText()),
                Integer.parseInt(anno.getText()), Integer.parseInt(punti.getText()));
            MainViewController.showAlertInsertData();
        }
    }

    @FXML
    void punti(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert anno != null : "fx:id=\"anno\" was not injected: check your FXML file 'insertClassifica.fxml'.";
        assert idPilota != null : "fx:id=\"idPilota\" was not injected: check your FXML file 'insertClassifica.fxml'.";
        assert inserisci != null : "fx:id=\"inserisci\" was not injected: check your FXML file 'insertClassifica.fxml'.";
        assert punti != null : "fx:id=\"punti\" was not injected: check your FXML file 'insertClassifica.fxml'.";

    }

    public void setModelController(ModelController modelController) {
        this.modelController = modelController;
        ViewImpl.addIntListener(idPilota);
        ViewImpl.addIntListener(anno);
        ViewImpl.addIntListener(punti);
    }
}
