package br.eti.tavares.trashpick.model;

import com.google.android.gms.maps.model.LatLng;

public class Coordenada {

    private double latitude;
    private double longitude;


    public Coordenada() {}

    public Coordenada(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public LatLng localizacao(){
        return new LatLng(latitude, longitude);
    }
}