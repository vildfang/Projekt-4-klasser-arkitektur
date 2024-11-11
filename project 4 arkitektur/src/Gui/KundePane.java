package Gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Kunde;

public class KundePane extends GridPane {
    private ListView<Kunde> kundeListView;
    private TextField nameTextField;
    private TextField mobilTextField;


    public KundePane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        this.add(new Label("Kunder"), 0, 0);

        kundeListView = new ListView<>();
        this.add(kundeListView, 0, 1, 1, 3);
        kundeListView.setPrefWidth(200);
        kundeListView.setPrefHeight(200);
        kundeListView.getItems().setAll(Controller.getKunder());

        ChangeListener<Kunde> listener = (ov, oldKunde, newKunde) -> this.selectedKundeChanged(newKunde);
        kundeListView.getSelectionModel().selectedItemProperty().addListener(listener);


        Label lblName = new Label("Navn:");
        this.add(lblName, 1, 1);

        nameTextField = new TextField();
        this.add(nameTextField, 2, 1);
        nameTextField.setEditable(false);

        Label lblMobil = new Label("Mobil nummer:");
        this.add(lblMobil, 1, 2);

        mobilTextField = new TextField();
        this.add(mobilTextField, 2, 2);
        mobilTextField.setEditable(false);

        Button opretKunde = new Button("Opret kunde");
        this.add(opretKunde,2,3);
        opretKunde.setOnAction(event -> this.opretAction());




    }
    private void selectedKundeChanged(Kunde newKunde) {
        this.updateControls(newKunde);
    }
    public void updateControls(Kunde kunde) {
        if (kunde != null) {
            nameTextField.setText(kunde.getNavn());
            mobilTextField.setText( kunde.getMobil());


        } else {
            nameTextField.clear();
            mobilTextField.clear();
        }
    }
    private void opretAction() {
        new KundeWindow("Opret kunde").showAndWait();
        // Wait for the modal dialog to close
        kundeListView.getItems().setAll(Controller.getKunder());
        int index = kundeListView.getItems().size() - 1;
        kundeListView.getSelectionModel().select(index);


    }
}
