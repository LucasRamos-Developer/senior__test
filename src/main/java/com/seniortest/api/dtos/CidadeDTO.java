package com.seniortest.api.dtos;

import java.util.Set;

import com.seniortest.api.models.Cidade;

public class CidadeDTO {
  
  private Long codigoIbge;
  private String nome;
  private String uf;
  private Set<String> ceps;
  
  public CidadeDTO() {}

  public CidadeDTO(Cidade cidade) {
    this.codigoIbge = cidade.getcodigoIbge();
    this.nome  = cidade.getNome();
    this.uf = cidade.getUf();
  }

  public CidadeDTO(Long codigoIbge, String nome, String uf, Set<String> ceps) {
    this.codigoIbge = codigoIbge;
    this.nome = nome;
    this.uf = uf;
    this.ceps = ceps;
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
