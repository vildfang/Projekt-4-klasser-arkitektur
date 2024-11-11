package Gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Bestilling;
import model.Forestilling;
import model.Kunde;
import model.Plads;

public class BestillingPane extends GridPane {
    private ListView<Forestilling> forestillingListView;
    private ListView<Kunde> kundeListView;
    private ListView<Plads> pladsListView;
    private TextField dato;


    public BestillingPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        this.add(new Label("Forestillinger"), 0, 0);

        forestillingListView = new ListView<>();
        this.add(forestillingListView, 0, 1, 1, 3);
        forestillingListView.setPrefWidth(300);
        forestillingListView.setPrefHeight(100);
        forestillingListView.getItems().setAll(Controller.getForestillinger());

        this.add(new Label("Kunder"), 0, 4);

        kundeListView = new ListView<>();
        this.add(kundeListView, 0, 5, 1, 3);
        kundeListView.setPrefWidth(200);
        kundeListView.setPrefHeight(100);
        kundeListView.getItems().setAll(Controller.getKunder());

        this.add(new Label("Pladser"), 1, 0);

        pladsListView = new ListView<>();
        this.add(pladsListView, 1, 1, 1, 7);
        pladsListView.setPrefWidth(250);
        pladsListView.setPrefHeight(400);
        pladsListView.getItems().setAll(Controller.getPlads());

        dato = new TextField();
        this.add(dato, 1, 8);


        Button opretButton = new Button("Opret bestilling");
        this.add(opretButton, 1, 9);
        opretButton.setOnAction(event -> this.opretAction());
    }

    private void opretAction() {
        // Get selected items from each ListView
        Forestilling selectedForestilling = forestillingListView.getSelectionModel().getSelectedItem();
        Kunde selectedKunde = kundeListView.getSelectionModel().getSelectedItem();
        Plads selectedPlads = pladsListView.getSelectionModel().getSelectedItem();

        // Check if selections are made
        if (selectedForestilling != null && selectedKunde != null && selectedPlads != null) {
            // Create a new Bestilling with the provided data
            Bestilling bestilling = new Bestilling(dato.getText(), selectedForestilling, selectedKunde, selectedPlads);

            // Assuming you have a Controller method to handle the created reservation
            Controller.opretBestillingMedPladser(selectedForestilling);

            // Optionally: Clear selections or reset UI state
            forestillingListView.getSelectionModel().clearSelection();
            kundeListView.getSelectionModel().clearSelection();
            pladsListView.getSelectionModel().clearSelection();
            dato.clear();
        } else {
            // Handle error case where selections are not made
            System.out.println("Please make selections for all fields.");
        }
    }


}
