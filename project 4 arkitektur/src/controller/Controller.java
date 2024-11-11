package controller;

import model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    public static Bestilling opretBestillingMedPladser(Forestilling forestilling, Kunde kunde, LocalDate dato, ArrayList<Plads> pladser) {
        // Check if the requested date is within the performance's start and end dates
        if (dato.isAfter(forestilling.getStartDato()) && dato.isBefore(forestilling.getSlutDato()) || dato.isEqual(forestilling.getStartDato()) || dato.isEqual(forestilling.getSlutDato())) {

            // Check if all requested seats are available for the given date
            for (Plads plads : pladser) {
                if (!forestilling.erPladsLedig(plads.getRække(), plads.getNr(), dato)) {
                    // If any seat is not available, return null or handle it as needed
                    return null;
                }
            }

            // All seats are available, so proceed with creating the booking
            Bestilling bestilling = forestilling.creatBestilling(dato, kunde);

            // Add the selected seats to the booking (assuming Bestilling has a method for this)
            for (Plads plads : pladser) {
                bestilling.addPlads(plads); // You'll need to implement addPlads() method in the Bestilling class
            }

            return bestilling;  // Return the created booking
        } else {
            // If the date is outside the performance's range, return null
            return null;
        }
    }

    public static Forestilling createForestilling(String navn, LocalDate startDato, LocalDate slutDato){
        Forestilling forestilling = new Forestilling(navn,startDato,slutDato);
        Storage.addForestilling(forestilling);
        return forestilling;
    }
    public static void updateForestilling(Forestilling forestilling, String navn, LocalDate startDato, LocalDate slutDato){
        forestilling.setNavn(navn);
        forestilling.setStartDato(startDato);
        forestilling.setSlutDato(slutDato);
    }

    public static ArrayList<Forestilling> getForestillinger() {
        return Storage.getForestillinger();
    }
//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    public static Kunde createKunde(String navn, String mobil){
        Kunde kunde = new Kunde(navn,mobil);
        Storage.addKunde(kunde);
        return kunde;
    }
    public static void updateKunde(Kunde kunde, String navn, String mobil){
        kunde.setNavn(navn);
        kunde.setMobil(mobil);
    }
    public static ArrayList<Kunde> getKunder() {
        return Storage.getKunder();
    }
//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    public static Plads createPlads(int række, int nr, int pris, PladsType pladsType){
        Plads plads = new Plads(række, nr, pris,pladsType);
        Storage.addPlads(plads);
        return plads;
    }
    public static ArrayList<Plads> getPlads() {
        return Storage.getPladser();
    }
}
