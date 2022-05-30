package com.asare.controllers;

import com.asare.data.Aproximation;
import com.asare.view.AproximationTab;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateAproximationController implements Initializable
{
    @FXML private TextField txtName;
    private Controller mainController;


    public String getName(){
        return txtName.getText();
    }

    /*
    @param: None
    @return: void
    Create and add a tab on the aproximations tab pane from mainController
     */
    public void createAproximationTab(){

        Button btnSave = new Button();
        Button btnCalculate = new Button();

        btnSave.setOnAction(event -> {
            System.out.println("Test");
        });

        mainController.getTabAproximations().getTabs().add(new AproximationTab(getName(), mainController));
        mainController.getAproximations().add(new Aproximation(getName()));
    }

    public void init(Controller mainController){
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtName.setFocusTraversable(false);
        txtName.setPromptText("Name");

        txtName.setOnKeyPressed(event -> {

            if(event.getCode() == KeyCode.ENTER){
                Stage stage = (Stage) txtName.getScene().getWindow();
                stage.close();
                createAproximationTab();
            }

        });
    }
}