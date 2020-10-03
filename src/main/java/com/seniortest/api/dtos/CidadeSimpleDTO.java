package com.seniortest.api.dtos;

public class CidadeSimpleDTO {
  private Long codigoIbge;
  private String nome;

  public CidadeSimpleDTO(Long codigo, String nome) {
    this.codigoIbge = codigo;
    this.nome = nome;
  }

  public CidadeSimpleDTO() {
}

public Long getcodigoIbge() {
    return codigoIbge;
  }

  public void setcodigoIbge(Long codigoIbge) {
    this.codigoIbge = codigoIbge;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }
}
