package com.seniortest.api.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="cidade")
public class Cidade {

  @Id
  @Column(name="codigo_ibge")
  private Long codigoIBGE;

  private String nome;

  private String uf;

  @JsonBackReference
  @OneToMany(mappedBy="cidade", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Cep> ceps;

  public Cidade() {}

  public Cidade(Long codigoIBGE, String nome, String uf) {
    this.codigoIBGE = codigoIBGE;
    this.nome = nome;
    this.uf = uf;
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

  public String getUf() {
    return uf;
  }

  public void setUf(String uf) {
    this.uf = uf;
  }
  
}