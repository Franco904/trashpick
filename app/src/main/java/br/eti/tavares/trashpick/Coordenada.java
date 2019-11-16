package br.eti.tavares.trashpick;

import com.google.android.gms.maps.model.LatLng;

public class Coordenada {

    protected double latitude;
    protected double longitude;


    protected Coordenada() {}

    protected Coordenada(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LatLng localizacao(){
        return new LatLng(latitude, longitude);
    }
}