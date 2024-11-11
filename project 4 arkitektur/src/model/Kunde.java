package model;

import java.util.ArrayList;

public class Kunde {
    private String navn;
    private String mobil;
    private ArrayList<Bestilling> bestillinger = new ArrayList<>();

    public Kunde(String navn, String mobil) {
        this.navn = navn;
        this.mobil = mobil;
    }

    public String getNavn() {
        return navn;
    }

    public String getMobil() {
        return mobil;
    }
    public void addBestilling(Bestilling bestilling){
        if (!bestillinger.contains(bestilling)){
            bestillinger.add(bestilling);
        }

    }
    public String toString(){
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }
}
