package com.aproximador.view;

import com.aproximador.controllers.Controller;
import com.aproximador.data.Materials;
import com.aproximador.data.Services;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.math.BigDecimal;

public class RecordPane extends DialogPane {

    private Label lblCost;
    private Label lblDescription;
    private Label lblName;

    // Attributes for panes in aproximations
    private Button btnRemove;
    private Button btnAdd;
    private TextField txtQuantity;
    private String description;
    private BigDecimal originalCost;
    private BigDecimal currentCost;

    public RecordPane(String recordName, String cost, String description, Controller controller, boolean isMaterial){

        lblName = new Label(recordName);

        lblCost = new Label("$" + cost);
        lblCost.setAlignment(Pos.CENTER_RIGHT);

        lblDescription = new Label(description);
        lblDescription.setAlignment(Pos.CENTER_LEFT);
        lblDescription.setTextOverrun(OverrunStyle.ELLIPSIS);
        lblDescription.setEllipsisString("...");

        HBox hBox = new HBox();
        hBox.getChildren().add(lblDescription);
        hBox.getChildren().add(lblCost);

        this.setHeader(lblName);
        this.setContent(hBox);

        this.setOnMouseClicked(event -> {
            Tab currentAproximationTab = controller.getTabAproximations().getSelectionModel().getSelectedItem();
            int tabIndex = controller.getTabAproximations().getSelectionModel().getSelectedIndex();

            Node node = currentAproximationTab.getContent();

            VBox vBoxRecords = (VBox) node.lookup("VBox");

            vBoxRecords.getChildren().add(new RecordPane(lblName.getText(), new BigDecimal(lblCost.getText().replace("$", "")), lblDescription.getText(), controller, isMaterial, true));

            if (isMaterial)
                controller.getAproximations().get(tabIndex).getRecords().add(new Materials(lblName.getText(),
                        new BigDecimal(lblCost.getText().replace("$", "")), lblDescription.getText()));
            else
                controller.getAproximations().get(tabIndex).getRecords().add(new Services(lblName.getText(),
                        new BigDecimal(lblCost.getText().replace("$", "")), lblDescription.getText()));
        });

        this.setOnMouseEntered(event -> {
            this.setStyle("-fx-background-color: black");
        });

        this.setOnMouseExited(event -> {
            this.setStyle("-fx-background-color: white");
        });

    }

    public RecordPane(String recordName, BigDecimal cost, String description, Controller controller, boolean isMaterial, boolean isUsed){
        this.setHeader(new Label(recordName));
        originalCost = cost;
        currentCost = originalCost;
        this.description = description;

        btnRemove = new Button("R");
        btnRemove.setOnAction(event -> {
            int tabIndex = controller.getTabAproximations().getSelectionModel().getSelectedIndex();
            int currentAmount = Integer.parseInt(txtQuantity.getText());

            currentAmount = currentAmount > 1 ? currentAmount - 1 : 1;

            if(currentAmount != 1){

                if(isMaterial){
                    int selectedMaterial = controller.getAproximations().get(tabIndex).getRecords().indexOf(new Materials(recordName,
                            currentCost, this.description));

                    currentCost = currentCost.subtract(originalCost);

                    controller.getAproximations().get(tabIndex).getRecords().get(selectedMaterial).setUnitCost(currentCost);
                }
                else{
                    int selectedService = controller.getAproximations().get(tabIndex).getRecords().indexOf(new Services(recordName,
                            currentCost, this.description));

                    currentCost = currentCost.subtract(originalCost);

                    controller.getAproximations().get(tabIndex).getRecords().get(selectedService).setUnitCost(currentCost);
                }
            }
            else{

                btnRemove.setDisable(true);

                if(isMaterial){
                    int selectedMaterial = controller.getAproximations().get(tabIndex).getRecords().indexOf(new Materials(recordName,
                            currentCost, this.description));

                    currentCost = currentCost.subtract(originalCost);

                    controller.getAproximations().get(tabIndex).getRecords().get(selectedMaterial).setUnitCost(currentCost);
                }
                else{
                    int selectedService = controller.getAproximations().get(tabIndex).getRecords().indexOf(new Services(recordName,
                            currentCost, this.description));

                    currentCost = currentCost.subtract(originalCost);

                    controller.getAproximations().get(tabIndex).getRecords().get(selectedService).setUnitCost(currentCost);
                }
            }

            txtQuantity.setText(String.valueOf(currentAmount));
        });
        btnRemove.setDisable(true);

        txtQuantity = new TextField("1");
        txtQuantity.setMaxSize(50, 10);
        txtQuantity.setFocusTraversable(false);

        btnAdd = new Button("Add");
        btnAdd.setOnAction(event -> {
            int tabIndex = controller.getTabAproximations().getSelectionModel().getSelectedIndex();
            int currentAmount = Integer.parseInt(txtQuantity.getText());

            currentAmount += 1;
            btnRemove.setDisable(false);

            if(isMaterial){
                int selectedMaterial = controller.getAproximations().get(tabIndex).getRecords().indexOf(new Materials(recordName,
                        currentCost, this.description));

                currentCost = currentCost.add(originalCost);

                controller.getAproximations().get(tabIndex).getRecords().get(selectedMaterial).setUnitCost(currentCost);
            }
            else{
                int selectedService = controller.getAproximations().get(tabIndex).getRecords().indexOf(new Services(recordName,
                        currentCost, this.description));

                currentCost = currentCost.add(originalCost);

                controller.getAproximations().get(tabIndex).getRecords().get(selectedService).setUnitCost(currentCost);
            }
            txtQuantity.setText(String.valueOf(currentAmount));
        });

        FlowPane flowPane = new FlowPane();
        flowPane.setAlignment(Pos.CENTER_RIGHT);

        flowPane.getChildren().add(btnRemove);
        flowPane.getChildren().add(txtQuantity);
        flowPane.getChildren().add(btnAdd);

        this.setContent(flowPane);
    }

}
