package com.example.telefonrehberi;

public class Rehber {
    int id;
    String ad, telefon, eposta;

    public Rehber(int id, String ad, String telefon, String eposta) {
        this.id = id;
        this.ad = ad;
        this.telefon = telefon;
        this.eposta = eposta;
    }

    public int getId() {
        return id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }
}
