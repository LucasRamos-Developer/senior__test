package com.seniortest.api.dtos;

public class CidadeSimpleDTO {
  private Long codigoIBGE;
  private String nome;

  public CidadeSimpleDTO(Long codigo, String nome) {
    this.codigoIBGE = codigo;
    this.nome = nome;
  }

  public CidadeSimpleDTO() {
}

public Long getCodigoIBGE() {
    return codigoIBGE;
  }

  public void setCodigoIBGE(Long codigoIBGE) {
    this.codigoIBGE = codigoIBGE;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }
}
