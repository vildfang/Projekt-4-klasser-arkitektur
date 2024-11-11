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
import model.Kunde;

import java.time.LocalDate;


public class KundeWindow extends Stage {
    private Kunde kunde;
    private TextField nameTextField;
    private TextField mobilTextField;
    private Label errorLabel;


    public KundeWindow(String title, Kunde kunde) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.kunde = kunde;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);

    }
    public KundeWindow(String title) {
        this(title,null);
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

        pane.add(new Label("Mobil"),0,2);

        mobilTextField = new TextField();
        pane.add(mobilTextField,0,3);
        mobilTextField.setPrefWidth(200);

        Button cancelButton = new Button("Cancel");
        pane.add(cancelButton, 0, 6);
        GridPane.setHalignment(cancelButton, HPos.LEFT);
        cancelButton.setOnAction(event -> this.cancelAction());

        Button okButton = new Button("OK");
        pane.add(okButton, 0, 6);
        GridPane.setHalignment(okButton, HPos.RIGHT);
        okButton.setOnAction(event -> this.okAction());

        nameTextField.setPromptText("Navn");
        mobilTextField.setPromptText("mobil");

        errorLabel = new Label();
        pane.add(errorLabel, 0, 5);
        errorLabel.setStyle("-fx-text-fill: red");

        this.initControls();
    }
    private void initControls() {
        if (kunde != null) {
            nameTextField.setText(kunde.getNavn());
        } else {
            nameTextField.clear();
        }
    }
    private void cancelAction() {
        this.hide();
    } private void okAction() {
        String name = nameTextField.getText().trim();
        String mobil = mobilTextField.getText().trim();
        if (name.isEmpty()) {
            errorLabel.setText("Name is empty");
            return;
        }
        // Call application.controller methods
        if (kunde != null) {
            Controller.updateKunde(kunde,name,mobil);

        }else{
            Controller.createKunde(name,mobil);

        }
        this.hide();
    }

    }
