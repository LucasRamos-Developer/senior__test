package com.seniortest.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ViaCepDTO {
  public final String cep;
  public final String logradouro;
  public final String complemento;
  public final String bairro;
  public final String localidade;
  public final String uf;
  public final String ibge;
  public final String gia;
  public final String ddd;
  public final String siafi;

  public ViaCepDTO(@JsonProperty("cep") String cep,
                @JsonProperty("logradouro") String logradouro,
                @JsonProperty("complemento") String complemento,
                @JsonProperty("bairro") String bairro,
                @JsonProperty("localidade") String localidade,
                @JsonProperty("uf") String uf,
                @JsonProperty("ibge") String ibge,
                @JsonProperty("gia") String gia,
                @JsonProperty("ddd") String ddd,
                @JsonProperty("siafi") String siafi) {

    this.cep = cep;
    this.logradouro = logradouro;
    this.complemento = complemento;
    this.bairro = bairro;
    this.localidade = localidade;
    this.uf = uf;
    this.ibge = ibge;
    this.gia = gia;
    this.ddd = ddd;
    this.siafi = siafi;
  }
}