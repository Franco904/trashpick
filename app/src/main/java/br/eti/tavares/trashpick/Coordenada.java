package br.eti.tavares.trashpick;

import com.google.android.gms.maps.model.LatLng;

public class Coordenada {

    private double latitude;
    private double longitude;
    private double duracao;

    public Coordenada(double latitude, double longitude, double duracao){
        this.latitude = latitude;
        this.longitude = longitude;
        this.duracao = duracao;
    }
    public LatLng localizacao(){
        return new LatLng(latitude, longitude);
    }

    public double getDuracao() {
        return duracao;
    }

    public void setDuracao(double duracao) {
        this.duracao = duracao;
    }
}