package com.seniortest.api.dtos;

import java.util.Set;

public class CidadeDTO {
  
  private Long codigoIBGE;
  private String nome;
  private String uf;
  private Set<String> ceps;
  
  public CidadeDTO() {}

  public CidadeDTO(Long codigoIBGE, String nome, String uf, Set<String> ceps) {
    this.codigoIBGE = codigoIBGE;
    this.nome = nome;
    this.uf = uf;
    this.ceps = ceps;
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

  public Set<String> getCeps() {
    return ceps;
  }

  public void setCeps(Set<String> ceps) {
    this.ceps = ceps;
  }

  public void addCep(String cep) {
    this.ceps.add(cep);
  }

  public String getUf() {
    return uf;
  }

  public void setUf(String uf) {
    this.uf = uf;
  }
}
