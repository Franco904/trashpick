package br.eti.tavares.trashpick;

import com.google.android.gms.maps.model.LatLng;

public class Coordenada {

    private double latitude;
    private double longitude;
    private String titulo;

    public Coordenada(double latitude, double longitude, String titulo){
        this.latitude = latitude;
        this.longitude = longitude;
        this.titulo = titulo;
    }
    public LatLng localizacao(){
        return new LatLng(latitude, longitude);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
