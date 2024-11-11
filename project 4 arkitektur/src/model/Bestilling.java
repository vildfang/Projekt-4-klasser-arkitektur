package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Bestilling {
    private LocalDate dato;
    private Forestilling forestilling;
    private Kunde kunde;
    private ArrayList<Plads> pladser = new ArrayList<>();
    private ArrayList<PladsType> pladsTypeer = new ArrayList<>();

    public Bestilling(LocalDate dato, Forestilling forestilling, Kunde kunde) {
        this.dato = dato;
        this.forestilling = forestilling;
        this.kunde = kunde;
    }

    public LocalDate getDato() {
        return dato;
    }
    public void addPlads(Plads plads){
        if (!pladser.contains(plads)){
            pladser.add(plads);
        }
    }
}
