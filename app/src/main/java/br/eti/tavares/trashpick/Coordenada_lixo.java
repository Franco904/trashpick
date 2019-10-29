package br.eti.tavares.trashpick;

import com.google.android.gms.maps.model.LatLng;

public class Coordenada_lixo {

  Lixo lixo;
  Coordinate coordenada;

  public Coordenada_lixo(){}

  public Coordenada_lixo(Double latitude, Double longitude, String descricao, String imagem) {
    lixo = new Lixo();
    lixo.setDescricao(descricao);
    lixo.setImagem(imagem);

    coordenada = new Coordinate();
    coordenada.setLatitude(latitude);
    coordenada.setLongitude(longitude);
  }

  public Lixo getLixo() {
    return lixo;
  }

  public void setLixo(Lixo lixo) {
    this.lixo = lixo;
  }

  public Coordinate getCoordenada() {
    return coordenada;
  }

  public void setCoordenada(Coordinate coordenada) {
    this.coordenada = coordenada;
  }

  public LatLng getLatLng() {
    return new LatLng(coordenada.latitude, coordenada.longitude);
  }

  /////////////////////////////////////////////////////////////////

  public String getDescricaoLixo(){
    return lixo.descricao;
  }

  public String getImagemLixo(){
    return lixo.imagem;
  }

  /////////////////////////////////////////////////////////////////
  // Classe Lixo
  private class Lixo {
    private String descricao;
    private String imagem;

    public Lixo() {
    }

    public String getDescricao() {
      return descricao;
    }

    public void setDescricao(String descricao) {
      this.descricao = descricao;
    }

    public String getImagem() {
      return imagem;
    }

    public void setImagem(String imagem) {
      this.imagem = imagem;
    }
  }

  // Classe Coordinate
  private class Coordinate {
    private double latitude;
    private double longitude;

    public Coordinate() {
    }

    public double getLatitude() {
      return latitude;
    }

    public void setLatitude(double latitude) {
      this.latitude = latitude;
    }

    public double getLongitude() {
      return longitude;
    }

    public void setLongitude(double longitude) {
      this.longitude = longitude;
    }
  }
}