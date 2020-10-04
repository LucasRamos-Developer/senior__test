package com.seniortest.api.dtos;

public class CidadeSimpleDTO {
  private Long codigoIbge;
  private String nome;
  private String uf;

  public CidadeSimpleDTO(Long codigo, String nome, String uf) {
    this.codigoIbge = codigo;
    this.nome = nome;
    this.uf = uf;
  }

  public CidadeSimpleDTO() {
}

public Long getCodigoIbge() {
    return codigoIbge;
  }

  public void setCodigoIbge(Long codigoIbge) {
    this.codigoIbge = codigoIbge;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getUf() {
    return uf;
  }

  public void setUf(String uf) {
    this.uf = uf;
  }
}
