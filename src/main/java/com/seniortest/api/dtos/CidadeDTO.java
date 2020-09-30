package com.seniortest.api.dtos;

import java.util.List;
import java.util.Set;

public class CidadeDTO {
  
  private Long codigoIBGE;
  private String nome;
  private Set<String> ceps;

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

  public Set<String> getCeps() {
    return ceps;
  }

  public void setCeps(Set<String> ceps) {
    this.ceps = ceps;
  }

  public void addCep(String cep) {
    this.ceps.add(cep);
  }
}
