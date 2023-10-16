package it.view.impl;

import java.io.IOException;

import it.controller.ModelController;
import it.view.api.View;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ViewImpl implements View{


    private Parent root;
    private MainViewController mainViewController;
    private ModelController modelController;



    @Override
    public void start(Stage stage) {
         final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("mainPane.fxml"));
         try {
            root = loader.load();
            mainViewController = loader.getController();
            modelController = new ModelController();
            mainViewController.setModelController(modelController);
            final Scene mainViewScene = new Scene(root);
            stage.setTitle("DB-MotoGP");
            stage.setScene(mainViewScene);
            stage.setOnCloseRequest(e -> {
                Platform.exit();
                System.exit(0);
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addIntListener(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
              String newValue) {
                  if (!newValue.matches("\\d*")) {
                        textField.setText(newValue.replaceAll("[^\\d]", ""));
                    }
            }
        });
    }
    
}
