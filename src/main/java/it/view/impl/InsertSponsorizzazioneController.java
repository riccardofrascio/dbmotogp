package it.view.impl;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import it.controller.ModelController;
import it.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

public class InsertSponsorizzazioneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker dataFine;

    @FXML
    private DatePicker dataInizio;

    @FXML
    private TextField idSponsor;

    @FXML
    private TextField idTeam;

    @FXML
    private Button inserisci;

    @FXML
    private TextField pagamento;

    private ModelController modelController;

    @FXML
    void dataFine(ActionEvent event) {

    }

    @FXML
    void dataInizio(ActionEvent event) {

    }

    @FXML
    void idSponsor(ActionEvent event) {

    }

    @FXML
    void idTeam(ActionEvent event) {

    }

    @FXML
    void inserisci(ActionEvent event) {
        if(idSponsor.getText().length() == 0 || idTeam.getText().length() == 0 || pagamento.getText().length() == 0 ||
                dataInizio.getValue() == null || dataFine.getValue() == null) {
            MainViewController.showAlertIncorrectData();
        }   else if(isContained(Integer.parseInt(idSponsor.getText()), Integer.parseInt(idTeam.getText()), 
                Utils.buildDate(dataInizio.getValue().getDayOfMonth(),
                dataInizio.getValue().getMonthValue(), dataInizio.getValue().getYear()).get())) {

            MainViewController.showAlertAlreadyPresent();
        } else {
            var dataIn = dataInizio.getValue();
            var datafin = dataInizio.getValue();
            modelController.insertSponsorizzazione(Integer.parseInt(idSponsor.getText()),
                Integer.parseInt(idTeam.getText()),
                Utils.buildDate(dataIn.getDayOfMonth(), dataIn.getMonthValue(), dataIn.getYear()).get(), 
                Utils.buildDate(datafin.getDayOfMonth(), datafin.getMonthValue(), datafin.getYear()).get(), 
                Double.parseDouble(pagamento.getText()));
            MainViewController.showAlertInsertData();
        }
        
    }

    private boolean isContained(int idSponsor, int idTeam, Date data) {
        var allKeys = modelController.findAllPrimaryKey(modelController.getTableSponsorizzazioni());
        for (int i = 0; i < allKeys.size(); i++) {
            if(allKeys.get(i).getFirst().equals(idSponsor) && allKeys.get(i).getSecond().equals(idTeam) &&
            allKeys.get(i).getThird().equals(data)) {
                    return true;
                }
        }
        return false;
    }

    @FXML
    void pagamento(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert dataFine != null : "fx:id=\"dataFine\" was not injected: check your FXML file 'insertSponsorizzazione.fxml'.";
        assert dataInizio != null : "fx:id=\"dataInizio\" was not injected: check your FXML file 'insertSponsorizzazione.fxml'.";
        assert idSponsor != null : "fx:id=\"idSponsor\" was not injected: check your FXML file 'insertSponsorizzazione.fxml'.";
        assert idTeam != null : "fx:id=\"idTeam\" was not injected: check your FXML file 'insertSponsorizzazione.fxml'.";
        assert inserisci != null : "fx:id=\"inserisci\" was not injected: check your FXML file 'insertSponsorizzazione.fxml'.";
        assert pagamento != null : "fx:id=\"pagamento\" was not injected: check your FXML file 'insertSponsorizzazione.fxml'.";

    }

    public void setModelController(ModelController modelController) {
        this.modelController = modelController;
        ViewImpl.addIntListener(idTeam);
        ViewImpl.addIntListener(idSponsor);
        dataInizio.setEditable(false);
        dataFine.setEditable(false);
        Pattern validEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?");

        UnaryOperator<TextFormatter.Change> filter = c -> {
            String text = c.getControlNewText();
            if (validEditingState.matcher(text).matches()) {
                return c ;
            } else {
                return null ;
            }
        };
        StringConverter<Double> converter = new StringConverter<Double>() {

            @Override
            public Double fromString(String s) {
                if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
                    return 0.0 ;
                } else {
                    return Double.valueOf(s);
                }
            }

            @Override
            public String toString(Double d) {
                return d.toString();
            }
        };
        TextFormatter<Double> textFormatter = new TextFormatter<>(converter, 0.0, filter);
        pagamento.setTextFormatter(textFormatter);
    }
}
