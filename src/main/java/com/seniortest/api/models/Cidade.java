package com.seniortest.api.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="cidade")
public class Cidade {

  @Id
  @Column(name="codigo_ibge")
  private String codigoIBGE;

  private String nome;

  @OneToMany(mappedBy="cidade", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Cep> ceps;

  public Cidade() {}

  public Cidade(String codigoIBGE, String nome) {
    this.codigoIBGE = codigoIBGE;
    this.nome = nome;
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

  public Set<Cep> getCeps() {
    return ceps;
  }

  public void setCeps(Set<Cep> ceps) {
    this.ceps = ceps;
  }
  
}