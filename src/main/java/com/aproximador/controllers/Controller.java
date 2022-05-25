package com.aproximador.controllers;

import com.aproximador.app.App;
import com.aproximador.data.*;
import com.aproximador.view.AproximationTab;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    private final Materials materials = new Materials();
    private final Services services = new Services();
    private final Connector connector = new Connector("juca", "g*$0Pe$h18cyiyJC");

    @FXML AddRecordController addRecordController;
    @FXML CreateAproximationController createAproximationController;

    @FXML private VBox vBoxMaterials;
    @FXML private VBox vBoxServices;
    @FXML private Button btnAddMaterial;
    @FXML private Button btnAddService;
    @FXML private TabPane tabAproximations;

    /*
    @param: String recordType
    @return: void
    Loads the addRecordPopUp and inits the addRecordController
     */
    public void popUpAddRecord(String recordType) throws IOException {

        FXMLLoader loader = new FXMLLoader(App.class.getResource("addRecordPopUp.fxml"));

        Parent root = loader.load();
        Scene addRecordScene = new Scene(root);
        Stage addStage = new Stage();
        addStage.setScene(addRecordScene);
        addStage.initModality(Modality.NONE);
        addStage.show();

        addRecordController = loader.getController();
        addRecordController.init(this, recordType);

    }

    /*
    @param: None
    @return: void
    Loads the createAproximationPopUp and inits the createAproximationController
     */
    public void createNewAproximation() throws IOException {

        FXMLLoader loader = new FXMLLoader(App.class.getResource("createAproximationPopUp.fxml"));

        Parent root = loader.load();
        Scene createAproximationScene = new Scene(root);
        Stage createAproximationStage = new Stage();
        createAproximationStage.setScene(createAproximationScene);
        createAproximationStage.initModality(Modality.NONE);
        createAproximationStage.show();

        createAproximationController = loader.getController();
        createAproximationController.init(this);
    }

    @Override
     public void initialize(URL location, ResourceBundle resources) {

        btnAddMaterial.setOnAction(event -> {
            try {
                popUpAddRecord("material");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnAddService.setOnAction(event -> {
            try {
                popUpAddRecord("service");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public VBox getvBoxMaterials() {
        return vBoxMaterials;
    }

    public VBox getvBoxServices() {
        return vBoxServices;
    }

    public TabPane getTabAproximations() {
        return tabAproximations;
    }

}
