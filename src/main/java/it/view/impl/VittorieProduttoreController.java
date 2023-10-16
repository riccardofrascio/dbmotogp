package it.view.impl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.controller.ModelController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class VittorieProduttoreController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField numeroVittorie;

    @FXML
    private Button ricerca;

    @FXML
    private ChoiceBox<String> sceltaProduttori;

    private ModelController modelController;

    @FXML
    void numeroVittorie(ActionEvent event) {

    }

    @FXML
    void ricerca(ActionEvent event) {
        if(sceltaProduttori.getSelectionModel().getSelectedItem() != null) {
            numeroVittorie.setText(Integer.toString(modelController.getTableMoto()
                    .findVittorieCasaProduttrice(sceltaProduttori.getSelectionModel().getSelectedItem())));
        }
    }

    @FXML
    void sceltaProduttori(MouseEvent event) {
        
    }

    @FXML
    void initialize() {
        assert numeroVittorie != null : "fx:id=\"numeroVittorie\" was not injected: check your FXML file 'vittorieCasaProduttrice.fxml'.";
        assert ricerca != null : "fx:id=\"ricerca\" was not injected: check your FXML file 'vittorieCasaProduttrice.fxml'.";
        assert sceltaProduttori != null : "fx:id=\"sceltaProduttori\" was not injected: check your FXML file 'vittorieCasaProduttrice.fxml'.";

    }

    public void setModelController(ModelController modelController) {
        this.modelController = modelController;
        numeroVittorie.setEditable(false);
        List<String> listCasaProd = modelController.getTableMoto().findAllCasaProduttrice();
        for (int i = 0; i < listCasaProd.size(); i++) {
            sceltaProduttori.getItems().add(listCasaProd.get(i));
        }
    }

}
