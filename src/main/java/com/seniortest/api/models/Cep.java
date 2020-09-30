package com.seniortest.api.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cep")
public class Cep {
  
  @Id
  private String numero;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="cidade_codigo", nullable = false)
  private Cidade cidade;

  public Cep() {}

  public Cep(String numero, Cidade cidade) {
    this.numero = numero;
    this.cidade = cidade;
  }


  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public Cidade getCidade() {
    return cidade;
  }

  public void setCidade(Cidade cidade) {
    this.cidade = cidade;
  }
  
}