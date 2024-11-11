package Gui;

import controller.Controller;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Forestilling;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ForestillingPane extends GridPane {
    private ListView<Forestilling> forestillingListView;
    private TextField nameTextField;
    private TextField startDatoTextField;
    private TextField slutDatoTextField;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public ForestillingPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        this.add(new Label("Forestillinger"), 0, 0);

        forestillingListView = new ListView<>();
        this.add(forestillingListView, 0, 1, 1, 3);
        forestillingListView.setPrefWidth(300);
        forestillingListView.setPrefHeight(200);
        forestillingListView.getItems().setAll(Controller.getForestillinger());

        ChangeListener<Forestilling> listener = (ov, oldForestilling, newForestilling) -> this.selectedForestillingChanged(newForestilling);
        forestillingListView.getSelectionModel().selectedItemProperty().addListener(listener);


        Label lblName = new Label("Navn:");
        this.add(lblName, 1, 1);

        nameTextField = new TextField();
        this.add(nameTextField, 2, 1);
        nameTextField.setEditable(false);

        Label lblStartDato = new Label("Start dato:");
        this.add(lblStartDato, 1, 2);

        startDatoTextField = new TextField();
        this.add(startDatoTextField, 2, 2);
        startDatoTextField.setEditable(false);


        Label lblSlutDato = new Label("Slut dato:");
        this.add(lblSlutDato, 1, 3);

        slutDatoTextField = new TextField();
        this.add(slutDatoTextField, 2, 3);
        slutDatoTextField.setEditable(false);

        Button opretButton = new Button("Opret forestilling");
        this.add(opretButton, 2, 4);
        opretButton.setOnAction(event -> this.opretAction());



        if (!forestillingListView.getItems().isEmpty()) {
            forestillingListView.getSelectionModel().select(0);
        }

    }
    private void opretAction() {
        new ForestillingWindow("Opret forestilling").showAndWait();
        // Wait for the modal dialog to close
        forestillingListView.getItems().setAll(Controller.getForestillinger());
        int index = forestillingListView.getItems().size() - 1;
        forestillingListView.getSelectionModel().select(index);

        // Inside the constructor (e.g., after adding other components), add the errorLabel to the layout


    }

    private void selectedForestillingChanged(Forestilling newforestilling) {
        this.updateControls(newforestilling);
    }

    // Add this line to the class field declarations to store error messages




    // Then, update the updateControls method:
    public void updateControls(Forestilling forestilling) {
        if (forestilling != null) {
                nameTextField.setText(forestilling.getNavn());
                startDatoTextField.setText(DATE_FORMATTER.format(forestilling.getStartDato()));
                slutDatoTextField.setText(DATE_FORMATTER.format(forestilling.getSlutDato()));
        } else {
            nameTextField.clear();
            startDatoTextField.clear();
            slutDatoTextField.clear();
        }
    }




}
