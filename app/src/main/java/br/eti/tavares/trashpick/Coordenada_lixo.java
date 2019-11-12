package br.eti.tavares.trashpick;

import com.google.android.gms.maps.model.LatLng;

public class Coordenada_lixo {

  private String id;
  Lixo lixo;
  Coordinate coordenada;

  public Coordenada_lixo(){}

  public Coordenada_lixo(String key, Double latitude, Double longitude, String nome,String descricao, String imagem) {

    id = key;

    if (nome != null) {
      lixo = new Lixo();
      lixo.setNome(nome);
      lixo.setDescricao(descricao);
      lixo.setImagem(imagem);
    } else {
      lixo = null;
    }


    coordenada = new Coordinate();
    coordenada.setLatitude(latitude);
    coordenada.setLongitude(longitude);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public String getNomeLixo(){
    return lixo.nome;
  }

  public String getDescricaoLixo(){
    return lixo.descricao;
  }

  public String getImagemLixo(){
    return lixo.imagem;
  }

  /////////////////////////////////////////////////////////////////
  // Classe Lixo
  private class Lixo {
    private String id;
    private String nome;
    private String descricao;
    private String imagem;

    public Lixo() {
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getNome() {
      return nome;
    }

    public void setNome(String nome) {
      this.nome = nome;
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
    private String id;
    private double latitude;
    private double longitude;

    public Coordinate() {
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
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