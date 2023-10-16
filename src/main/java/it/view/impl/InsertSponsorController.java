package it.view.impl;

import it.controller.ModelController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class InsertSponsorController {

    @FXML
    private TextField citta;

    @FXML
    private TextField idSponsor;

    @FXML
    private Button inserisci;

    @FXML
    private TextField nome;

    @FXML
    private TextField stato;

    private ModelController modelController;

    @FXML
    void citta(ActionEvent event) {

    }

    @FXML
    void idSponsor(ActionEvent event) {

    }

    @FXML
    void inserisci(ActionEvent event) {
        if(idSponsor.getText().length() == 0 || nome.getText().length() == 0 || stato.getText().length() == 0 || 
                citta.getText().length() == 0){ 
            MainViewController.showAlertIncorrectData();
        } else if(modelController.findAllPrimaryKey(modelController.getTableSponsor())
                .contains(Integer.parseInt(idSponsor.getText()))) {
            MainViewController.showAlertAlreadyPresent();
        } else {
            modelController.insertSponsor(Integer.parseInt(idSponsor.getText()),
                nome.getText(), stato.getText(), citta.getText());
            MainViewController.showAlertInsertData();
        }
        
    }

    @FXML
    void nome(ActionEvent event) {

    }

    @FXML
    void stato(ActionEvent event) {

    }

    public void setModelController(ModelController modelController) {
        this.modelController = modelController;
        ViewImpl.addIntListener(idSponsor);
    }

}
