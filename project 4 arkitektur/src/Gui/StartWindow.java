package Gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StartWindow extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Teater");
        BorderPane pane = new BorderPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private void initContent(BorderPane pane) {
        TabPane tabPane = new TabPane();
        this.initTabPane(tabPane);
        pane.setCenter(tabPane);
    }

    private void initTabPane(TabPane tabPane) {
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab tabForestillinger = new Tab("Forestillinger");
        tabPane.getTabs().add(tabForestillinger);

        ForestillingPane forestillingPane = new ForestillingPane();
        tabForestillinger.setContent(forestillingPane);

        Tab tabKunder = new Tab("Kunder");
        tabPane.getTabs().add(tabKunder);

        KundePane kundePane = new KundePane();
        tabKunder.setContent(kundePane);

        Tab tabBestilling = new Tab("Bestillinger");
        tabPane.getTabs().add(tabBestilling);

        BestillingPane bestillingPane = new BestillingPane();
        tabBestilling.setContent(bestillingPane);
    }
}
