package com.seniortest.api.dtos;

import java.util.List;

public class CidadeDTO {
  
  private String codigoIBGE;
  private String nome;
  private List<String> ceps;

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

  public List<String> getCeps() {
    return ceps;
  }

  public void setCeps(List<String> ceps) {
    this.ceps = ceps;
  }

  public void addCep(String cep) {
    this.ceps.add(cep);
  }
}
