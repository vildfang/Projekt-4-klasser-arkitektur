package Gui;

import controller.Controller;
import javafx.application.Application;
import model.Plads;
import model.PladsType;
import storage.Storage;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        initStorage();
        getPlads();

        Application.launch(StartWindow.class);

    }

    public static void initStorage() {
        Controller.createForestilling("Evita", LocalDate.of(2023, 8, 10), LocalDate.of(2023, 8, 20));
        Controller.createForestilling("Lykke Per", LocalDate.of(2023, 9, 1), LocalDate.of(2023, 9, 10));
        Controller.createForestilling("Chess", LocalDate.of(2023, 8, 21), LocalDate.of(2023, 8, 30));

        Controller.createKunde("Anders Hansen", "11223344");
        Controller.createKunde("Peter Jensen", "12345678");
        Controller.createKunde("Niels Madsen", "12341234");


    }
    public static void getPlads() {
        for (int række = 1; række <= 15; række++) {
            for (int nr = 1; nr <= 20; nr++) {
                PladsType pladstype;
                int pris;

                if (række >= 1 && række <= 5 && nr >= 3 && nr <= 18) {
                    // Gulepladser: 500 kr.
                    pladstype = PladsType.STANDARD;
                    pris = 500;
                } else if (række == 10 && nr >= 8 && nr <= 12) {
                    // Kørestolspladser: 450 kr.
                    pladstype = PladsType.KØRESTOL;
                    pris = 450;
                } else if (række == 11 && nr >= 8 && nr <= 12) {
                    // Ekstra benplads: 450 kr.
                    pladstype = PladsType.EKSTRABEN;
                    pris = 450;
                } else if ((række >= 6 && række <= 10 && nr <= 2 || nr >= 19) || (række >= 11 && række <= 15)) {
                    // blå pladser: 400 kr.
                    pladstype = PladsType.STANDARD;
                    pris = 400;
                } else {
                    // grønne pladser: 450 kr.
                    pladstype = PladsType.STANDARD;
                    pris = 450;
                }

                Controller.createPlads(række, nr, pris, pladstype);
            }
        }
    }

}








