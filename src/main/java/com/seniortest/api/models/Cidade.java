package com.seniortest.api.models;

import java.util.List;
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
  private Long codigoIBGE;

  private String nome;

  @OneToMany(mappedBy="cidade", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Cep> ceps;

  public Cidade() {}

  public Cidade(Long codigoIBGE, String nome) {
    this.codigoIBGE = codigoIBGE;
    this.nome = nome;
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

  public Set<Cep> getCeps() {
    return ceps;
  }

  public void setCeps(Set<Cep> ceps) {
    this.ceps = ceps;
  }

  public void addCep(Cep e) {
    this.ceps.add(e);
  }
  
}