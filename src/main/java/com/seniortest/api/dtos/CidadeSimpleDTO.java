package com.seniortest.api.dtos;

public class CidadeSimpleDTO {
  private String codigoIBGE;
  private String nome;

  public CidadeSimpleDTO(String codigo, String nome) {
    this.codigoIBGE = codigo;
    this.nome = nome;
  }

  public CidadeSimpleDTO() {
}

public String getCodigoIBGE() {
    return codigoIBGE;
  }

  public void setCodigoIBGE(String codigoIBGE) {
    this.codigoIBGE = codigoIBGE;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }
}
