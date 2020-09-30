package com.seniortest.api.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seniortest.api.dtos.ViaCepDTO;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ViaCepService {
  
  private static final ObjectMapper mapper = new ObjectMapper();

  public ViaCepDTO getInforByCEP(String cep) throws IOException {

    try (CloseableHttpClient client = HttpClients.createDefault()) {

      HttpGet request = new HttpGet(String.format("https://viacep.com.br/ws/%s/json/", cep));

      ViaCepDTO response = client.execute(request,
          httpResponse -> mapper.readValue(httpResponse.getEntity().getContent(), ViaCepDTO.class));

      return response;
    }
  }
}