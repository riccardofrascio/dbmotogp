package it.view.impl;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.controller.ModelController;
import it.db.impl.AbstractTable;
import it.model.Pilota;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainViewController {

    private ModelController modelController;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem Piloti;

    @FXML
    private MenuItem broadcastGare;

    @FXML
    private MenuItem classifiche;

    @FXML
    private MenuItem contratti;

    @FXML
    private MenuItem gare;

    @FXML
    private MenuItem incidenti;

    @FXML
    private MenuItem moto;

    @FXML
    private MenuItem piste;

    @FXML
    private MenuItem piuIncassi;

    @FXML
    private MenuItem piuIncidenti;

    @FXML
    private MenuItem piuVittorie;

    @FXML
    private MenuItem sponsor;

    @FXML
    private MenuItem sponsorizzazioni;

    @FXML
    private MenuItem staff;

    @FXML
    private MenuItem tabelloniGare;

    @FXML
    private MenuItem team;

    @FXML
    private MenuItem inserisciClassifica;

    @FXML
    private MenuItem inserisciGara;

    @FXML
    private MenuItem inserisciMoto;

    @FXML
    private MenuItem inserisciSponsor;

    @FXML
    private MenuItem inserisciSponsorizzazione;

    @FXML
    private MenuItem inserisciStaff;

    @FXML
    private MenuItem vittorieProduttori;

    @FXML
    private AnchorPane mainPane;


    @FXML
    void Piloti(ActionEvent event) {
        viewTable(modelController.getTablePiloti());
    }

    @FXML
    void broadcastGare(ActionEvent event) {
        viewTable(modelController.getTableBroadcast());
    }

    @FXML
    void classifiche(ActionEvent event) {
        viewTable(modelController.getTableClassifica());
    }

    @FXML
    void contratti(ActionEvent event) {
        viewTable(modelController.getTableContratti());
    }

    @FXML
    void gare(ActionEvent event) {
        viewTable(modelController.getTableGare());
    }

    @FXML
    void incidenti(ActionEvent event) {
        viewTable(modelController.getTableIncidenti());
    }

    @FXML
    void moto(ActionEvent event) {
        viewTable(modelController.getTableMoto());
    }

    @FXML
    void piste(ActionEvent event) {
        viewTable(modelController.getTablePiste());
    }

    @FXML
    void sponsor(ActionEvent event) {
        viewTable(modelController.getTableSponsor());
    }

    @FXML
    void sponsorizzazioni(ActionEvent event) {
        viewTable(modelController.getTableSponsorizzazioni());
    }

    @FXML
    void staff(ActionEvent event) {
        viewTable(modelController.getTableStaff());
    }

    @FXML
    void tabelloniGare(ActionEvent event) {
        viewTable(modelController.getTabelloneGare());
    }

    @FXML
    void team(ActionEvent event) {
        viewTable(modelController.getTableTeam());
    }

    @FXML
    void inserisciClassifica(ActionEvent event) {
        try {
            final Stage stage = new Stage();
            final FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("insertClassifica.fxml"));
            final Scene insertClassifica = new Scene(fxmlLoader.load());
            final InsertClassificaController insertClassificaController = (InsertClassificaController) fxmlLoader.getController();
            insertClassificaController.setModelController(modelController);
            stage.setTitle("Inserisci classifica");
            stage.setScene(insertClassifica);
            stage.sizeToScene();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void inserisciGara(ActionEvent event) {
        try {
            final Stage stage = new Stage();
            final FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("insertGara.fxml"));
            final Scene insertGara = new Scene(fxmlLoader.load());
            final InsertGaraController insertGaraController = (InsertGaraController) fxmlLoader.getController();
            insertGaraController.setModelController(modelController);
            stage.setTitle("Inserisci gara");
            stage.setScene(insertGara);
            stage.sizeToScene();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void inserisciMoto(ActionEvent event) {
        try {
            final Stage stage = new Stage();
            final FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("insertMoto.fxml"));
            final Scene insertMoto = new Scene(fxmlLoader.load());
            final InsertMotoController insertMotoController = (InsertMotoController) fxmlLoader.getController();
            insertMotoController.setModelController(modelController);
            stage.setTitle("Inserisci moto");
            stage.setScene(insertMoto);
            stage.sizeToScene();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void inserisciSponsor(ActionEvent event) {
        try {
            final Stage stage = new Stage();
            final FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("insertSponsor.fxml"));
            final Scene insertSponsor = new Scene(fxmlLoader.load());
            final InsertSponsorController insertSponsorController = (InsertSponsorController) fxmlLoader.getController();
            insertSponsorController.setModelController(modelController);
            stage.setTitle("Inserisci sponsor");
            stage.setScene(insertSponsor);
            stage.sizeToScene();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void inserisciSponsorizzazione(ActionEvent event) {
        try {
            final Stage stage = new Stage();
            final FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("insertSponsorizzazione.fxml"));
            final Scene insertSponsorizzazione = new Scene(fxmlLoader.load());
            final InsertSponsorizzazioneController insertSponsorizzazioneController = (InsertSponsorizzazioneController) fxmlLoader.getController();
            insertSponsorizzazioneController.setModelController(modelController);
            stage.setTitle("Inserisci sponsorizzazione");
            stage.setScene(insertSponsorizzazione);
            stage.sizeToScene();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void inserisciStaff(ActionEvent event) {
        try {
            final Stage stage = new Stage();
            final FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("insertStaff.fxml"));
            final Scene insertStaff = new Scene(fxmlLoader.load());
            final InsertStaffController insertStaffController = (InsertStaffController) fxmlLoader.getController();
            insertStaffController.setModelController(modelController);
            stage.setTitle("Inserisci staff");
            stage.setScene(insertStaff);
            stage.sizeToScene();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        @FXML
    void piuIncassi(ActionEvent event) {
        var coppiaTeamIncassi = modelController.getTableSponsorizzazioni().countMaxIncassi();
        if(modelController.getTableTeam().findByPrimaryKey(coppiaTeamIncassi.getX()).isPresent()) {
            viewValues(List.of(modelController.getTableTeam().findByPrimaryKey(coppiaTeamIncassi.getX()).get()), 
                    modelController.getListAttributes(modelController.getTableTeam()));
        }
        Label popLabel = new Label("Incassi: " + coppiaTeamIncassi.getY());
        AnchorPane.setRightAnchor(popLabel, 15.0);
        AnchorPane.setTopAnchor(popLabel, 50.0);
        mainPane.getChildren().add(popLabel);
    }

    
    @FXML
    void piuIncidenti(ActionEvent event) {
        var listPilotiIncidenti = modelController.getTableIncidenti().countMaxIncidenti();
        List<Pilota> piloti = new ArrayList<>();
        for (int i = 0; i < listPilotiIncidenti.size(); i++) {
            if(modelController.getTablePiloti().findByPrimaryKey(listPilotiIncidenti.get(i).getX()).isPresent()) {
                piloti.add(modelController.getTablePiloti().findByPrimaryKey(listPilotiIncidenti.get(i).getX()).get());
            }
        }
        viewValues(piloti, modelController.getListAttributes(modelController.getTablePiloti()));

        Label popLabel = new Label("Incidenti: " + listPilotiIncidenti.get(0).getY());
        AnchorPane.setRightAnchor(popLabel, 15.0);
        AnchorPane.setTopAnchor(popLabel, listPilotiIncidenti.size()*25 + 50.0);
        mainPane.getChildren().add(popLabel);

    }

    @FXML
    void piuVittorie(ActionEvent event) {
        var listPilotiVincenti = modelController.getTabelloneGare().countMaxVittorie();
        List<Pilota> piloti = new ArrayList<>();
        for (int i = 0; i < listPilotiVincenti.size(); i++) {
            if(modelController.getTablePiloti().findByPrimaryKey(listPilotiVincenti.get(i).getX()).isPresent()) {
                piloti.add(modelController.getTablePiloti().findByPrimaryKey(listPilotiVincenti.get(i).getX()).get());
            }
        }
        viewValues(piloti, modelController.getListAttributes(modelController.getTablePiloti()));

        Label popLabel = new Label("Vittorie: " + listPilotiVincenti.get(0).getY());
        AnchorPane.setRightAnchor(popLabel, 15.0);
        AnchorPane.setTopAnchor(popLabel, listPilotiVincenti.size()*25 + 50.0);
        mainPane.getChildren().add(popLabel);
    }

    
    @FXML
    void vittorieProduttori(ActionEvent event) {
        try {
            final Stage stage = new Stage();
            final FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("vittorieCasaProduttrice.fxml"));
            final Scene vittorieProduttore = new Scene(fxmlLoader.load());
            final VittorieProduttoreController vittorieProduttoreController = (VittorieProduttoreController) fxmlLoader.getController();
            vittorieProduttoreController.setModelController(modelController);
            stage.setTitle("Vittorie casa produttrice");
            stage.setScene(vittorieProduttore);
            stage.sizeToScene();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void initialize() {
        assert Piloti != null : "fx:id=\"Piloti\" was not injected: check your FXML file 'mainPane.fxml'.";
        assert broadcastGare != null : "fx:id=\"broadcastGare\" was not injected: check your FXML file 'mainPane.fxml'.";
        assert classifiche != null : "fx:id=\"classifiche\" was not injected: check your FXML file 'mainPane.fxml'.";
        assert contratti != null : "fx:id=\"contratti\" was not injected: check your FXML file 'mainPane.fxml'.";
        assert gare != null : "fx:id=\"gare\" was not injected: check your FXML file 'mainPane.fxml'.";
        assert incidenti != null : "fx:id=\"incidenti\" was not injected: check your FXML file 'mainPane.fxml'.";
        assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'mainPane.fxml'.";
        assert moto != null : "fx:id=\"moto\" was not injected: check your FXML file 'mainPane.fxml'.";
        assert piste != null : "fx:id=\"piste\" was not injected: check your FXML file 'mainPane.fxml'.";
        assert sponsor != null : "fx:id=\"sponsor\" was not injected: check your FXML file 'mainPane.fxml'.";
        assert sponsorizzazioni != null : "fx:id=\"sponsorizzazioni\" was not injected: check your FXML file 'mainPane.fxml'.";
        assert staff != null : "fx:id=\"staff\" was not injected: check your FXML file 'mainPane.fxml'.";
        assert tabelloniGare != null : "fx:id=\"tabelloniGare\" was not injected: check your FXML file 'mainPane.fxml'.";
        assert team != null : "fx:id=\"team\" was not injected: check your FXML file 'mainPane.fxml'.";

    }

    public void setModelController(final ModelController modelController) {
        this.modelController = modelController;
    }

    private <V> void viewValues(List<V> values, List<String> attributes) {
        mainPane.getChildren().removeAll();
        TableView<V> tableView = new TableView<>();
        for (int i = 0; i < attributes.size(); i++) {
            tableView.getColumns().add(i, new TableColumn<>(attributes.get(i)));
            tableView.getColumns().get(i).setCellValueFactory(new PropertyValueFactory<>(attributes.get(i)));
        }
        ObservableList<V> valuesList = FXCollections.observableArrayList();
        valuesList.addAll(values);
        tableView.setItems(valuesList);

        AnchorPane.setTopAnchor(tableView, 0.0);
        AnchorPane.setRightAnchor(tableView, 0.0);
        AnchorPane.setBottomAnchor(tableView, 0.0);
        AnchorPane.setLeftAnchor(tableView, 0.0);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tableView.prefHeightProperty().bind(mainPane.heightProperty());
        tableView.prefWidthProperty().bind(mainPane.widthProperty());
        mainPane.getChildren().add(tableView);
    }

    private <T extends AbstractTable<V, K>, V, K> void viewTable(T table) {
        mainPane.getChildren().removeAll();
        List<String> attributes = modelController.getListAttributes(table);
        List<V> values = table.findAll();
        TableView<V> tableView = new TableView<>();
        for (int i = 0; i < attributes.size(); i++) {
            tableView.getColumns().add(i, new TableColumn<>(attributes.get(i)));
            tableView.getColumns().get(i).setCellValueFactory(new PropertyValueFactory<>(attributes.get(i)));
        }
        ObservableList<V> valuesList = FXCollections.observableArrayList();
        valuesList.addAll(values);
        tableView.setItems(valuesList);

        AnchorPane.setTopAnchor(tableView, 0.0);
        AnchorPane.setRightAnchor(tableView, 0.0);
        AnchorPane.setBottomAnchor(tableView, 0.0);
        AnchorPane.setLeftAnchor(tableView, 0.0);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tableView.prefHeightProperty().bind(mainPane.heightProperty());
        tableView.prefWidthProperty().bind(mainPane.widthProperty());
        mainPane.getChildren().add(tableView);
    }

    public static void showAlertIncorrectData() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errato inserimento delle informazioni");
        alert.setContentText("Inserire correttamente i dati");
        alert.show();
    }

    public static void showAlertAlreadyPresent() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errato inserimento delle informazioni");
        alert.setContentText("Il valore è gia presente nel database");
        alert.show();
    }

    public static void showAlertInsertData() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Corretto inserimento");
        alert.setContentText("Valori inseriti correttamente");
        alert.show();
    }

}
