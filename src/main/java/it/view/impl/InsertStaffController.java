package it.view.impl;


import it.controller.ModelController;
import it.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class InsertStaffController {

    @FXML
    private TextField codiceFiscale;

    @FXML
    private TextField cognome;

    @FXML
    private TextField idStaff;

    @FXML
    private DatePicker dataNascita;

    @FXML
    private TextField idTeam;

    @FXML
    private TextField nome;

    @FXML
    private Button inserisci;

    @FXML
    private TextField ruolo;

    private ModelController modelController;

    @FXML
    void idStaff(ActionEvent event) {

    }

    @FXML
    void codiceFiscale(ActionEvent event) {

    }

    @FXML
    void nome(ActionEvent event) {

    }

    @FXML
    void cognome(ActionEvent event) {

    }

    @FXML
    void dataNascita(ActionEvent event) {

    }

    @FXML
    void idTeam(ActionEvent event) {

    }

    @FXML
    void ruolo(ActionEvent event) {

    }

    
    @FXML
    void inserisci(ActionEvent event) {
        if(idStaff.getText().length() == 0 || codiceFiscale.getText().length() == 0 || 
                nome.getText().length() == 0 || cognome.getText().length() == 0 || ruolo.getText().length() == 0 || 
                idTeam.getText().length() == 0 || dataNascita.getValue() == null) {
            MainViewController.showAlertIncorrectData();
        } else if(modelController.findAllPrimaryKey(modelController.getTableStaff())
                .contains(Integer.parseInt(idStaff.getText()))) {
            MainViewController.showAlertAlreadyPresent();
        } else {
            var date = dataNascita.getValue();
            modelController.insertStaff(Integer.parseInt(idStaff.getText()), 
                codiceFiscale.getText(), nome.getText(), cognome.getText(),
                Utils.buildDate(date.getDayOfMonth(), date.getMonthValue(), date.getYear()).get(),
                ruolo.getText(), Integer.parseInt(idTeam.getText()));
            MainViewController.showAlertInsertData();
        }
    }

    public void setModelController(ModelController modelController) {
        this.modelController = modelController;
        ViewImpl.addIntListener(idTeam);
        ViewImpl.addIntListener(idStaff);
        dataNascita.setEditable(false);
    }

}

