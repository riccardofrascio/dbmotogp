package it.view.impl;

import it.controller.ModelController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class InsertMotoController {

    @FXML
    private TextField casaProduttrice;

    @FXML
    private TextField cilindrata;

    @FXML
    private TextField idMoto;

    @FXML
    private Button inserisci;

    @FXML
    private TextField peso;

    private ModelController modelController;

    @FXML
    void casaProduttrice(ActionEvent event) {

    }

    @FXML
    void cilindrata(ActionEvent event) {

    }

    @FXML
    void idMoto(ActionEvent event) {

    }

    @FXML
    void inserisci(ActionEvent event) {
        if(idMoto.getText().length() == 0 || casaProduttrice.getText().length() == 0 || cilindrata.getText().length() == 0 ||
                peso.getText().length() == 0) {
            MainViewController.showAlertIncorrectData();
        } else if(modelController.findAllPrimaryKey(modelController.getTableMoto())
                .contains(Integer.parseInt(idMoto.getText()))) {
            MainViewController.showAlertAlreadyPresent();
        } else {
            modelController.insertMoto(Integer.parseInt(idMoto.getText()), 
                    casaProduttrice.getText(), Integer.parseInt(cilindrata.getText()),
                    Integer.parseInt(peso.getText()));
            MainViewController.showAlertInsertData();
        }
    }

    @FXML
    void peso(ActionEvent event) {

    }

    public void setModelController(ModelController modelController) {
        this.modelController = modelController;
        ViewImpl.addIntListener(peso);
        ViewImpl.addIntListener(cilindrata);
        ViewImpl.addIntListener(idMoto);
    }
}