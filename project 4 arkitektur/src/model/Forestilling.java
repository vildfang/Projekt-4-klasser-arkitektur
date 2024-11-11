package model;

import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Forestilling {
    private String navn;
    private LocalDate startDato;
    private LocalDate slutDato;
    private ArrayList<Bestilling> bestillinger = new ArrayList<>();

    public Forestilling(String navn, LocalDate startDato, LocalDate slutDato) {
        this.navn = navn;
        this.startDato = startDato;
        this.slutDato = slutDato;
    }

    public String getNavn() {
        return navn;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }
    public Bestilling creatBestilling(LocalDate dato, Kunde kunde){
        Bestilling bestilling = new Bestilling(dato, this, kunde );
        bestillinger.add(bestilling);
        return bestilling;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setStartDato(LocalDate startDato) {
        this.startDato = startDato;
    }

    public void setSlutDato(LocalDate slutDato) {
        this.slutDato = slutDato;
    }

    @Override
    public String toString() {
        return navn;
    }
    public boolean erPladsLedig(int række, int nr, LocalDate dato) {
        for (Plads plads : Storage.getPladser()) {
            if (plads.getRække() == række && plads.getNr() == nr) {
                for (Bestilling bestilling : bestillinger) {
                    if (bestilling.getDato().equals(dato)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}
