package Gui;

import controller.Controller;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Forestilling;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ForestillingWindow extends Stage {
    private Forestilling forestilling;
    private TextField nameTextField;
    private TextField startDatoTextField;
    private TextField slutDatoTextField;
    private Label errorLabel;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public ForestillingWindow(String title, Forestilling forestilling) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.forestilling = forestilling;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane,250,300);
        this.setScene(scene);
    }

    public ForestillingWindow(String title) {
        this(title, null);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        pane.add(new Label("Navn"), 0, 0);

        nameTextField = new TextField();
        pane.add(nameTextField, 0, 1);
        nameTextField.setPrefWidth(200);

        pane.add(new Label("Start Dato"), 0, 2);

        startDatoTextField = new TextField();
        pane.add(startDatoTextField, 0, 3);
        startDatoTextField.setPrefWidth(200);

        pane.add(new Label("Slut dato"), 0, 4);

        slutDatoTextField = new TextField();
        pane.add(slutDatoTextField, 0, 5);
        slutDatoTextField.setPrefWidth(200);

        Button cancelButton = new Button("Cancel");
        pane.add(cancelButton, 0, 7);
        GridPane.setHalignment(cancelButton, HPos.LEFT);
        cancelButton.setOnAction(event -> this.cancelAction());

        Button okButton = new Button("OK");
        pane.add(okButton, 0, 7);
        GridPane.setHalignment(okButton, HPos.RIGHT);
        okButton.setOnAction(event -> this.okAction());

        errorLabel = new Label();
        pane.add(errorLabel, 0, 6, 2, 1); // Span across columns for visibility
        errorLabel.setStyle("-fx-text-fill: red");

        nameTextField.setPromptText("navn");
        startDatoTextField.setPromptText("Start dato (dd-mm-yyyy)");
        slutDatoTextField.setPromptText("Slut dato (dd-mm-yyyy)");

        this.initControls();
    }

    private void initControls() {
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

    private void cancelAction() {
        this.hide();
    }

    private void okAction() {
        String name = nameTextField.getText().trim();

        // Check if the name is empty
        if (name.isEmpty()) {
            errorLabel.setText("Navn må ikke være tomt.");
            return;
        }

        LocalDate startDato;
        LocalDate slutDato;

        // Parse start date and handle exceptions
        try {
            startDato = LocalDate.parse(startDatoTextField.getText().trim(), DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            errorLabel.setText("Start dato ugyldig (dd-mm-yyyy).");
            return;
        }

        // Parse end date and handle exceptions
        try {
            slutDato = LocalDate.parse(slutDatoTextField.getText().trim(), DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            errorLabel.setText("Slut dato ugyldig(dd-mm-yyyy).");
            return;
        }

        // Additional validation (if needed)
        if (slutDato.isBefore(startDato)) {
            errorLabel.setText("Slut dato skal være efter start dato.");
            return;
        }

        // Clear any error message if all validations pass
        errorLabel.setText("");

        // Call the controller methods to update or create a Forestilling
        if (forestilling != null) {
            Controller.updateForestilling(forestilling, name, startDato, slutDato);
        } else {
            Controller.createForestilling(name, startDato, slutDato);
        }

        this.hide();
    }
}


