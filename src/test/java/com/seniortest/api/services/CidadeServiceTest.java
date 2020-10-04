package com.seniortest.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.seniortest.api.dtos.CidadeSimpleDTO;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CidadeServiceTest {

  public HashMap<String,CidadeSimpleDTO> getCidades() {
    HashMap<String,CidadeSimpleDTO> list = new HashMap<>();

    list.put("001", new CidadeSimpleDTO((long) 001, "Cidade 01", "A"));
    list.put("002", new CidadeSimpleDTO((long) 002, "Cidade 02", "B"));
    list.put("003", new CidadeSimpleDTO((long) 002, "Cidade 02", "B"));
    list.put("004", new CidadeSimpleDTO((long) 004, "Cidade 03", "A"));

    return list;
  }

  public List<String> getCEP() {
    List<String> ceps = new ArrayList<>();

    ceps.add("001");
    ceps.add("003");
    ceps.add("002");
    ceps.add("004");

    return ceps;
  }

  @Test
  public void testRouteByCeps() {
    CidadeService cidadeService = new CidadeService();
    List<CidadeSimpleDTO> list =  cidadeService.getCidadesUniqueList(this.getCidades(), this.getCEP());
    
    assertEquals(3, list.size());
  }
  
  @Test
  public void testOrderByCeps() {
    CidadeService cidadeService = new CidadeService();

    List<String> ceps = this.getCEP();
    HashMap<String,CidadeSimpleDTO> cidades = this.getCidades();    

    List<CidadeSimpleDTO> list =  cidadeService.getCidadesUniqueList(cidades, ceps);

    CidadeSimpleDTO cidade = list.get(2);

    assertEquals(cidade.getCodigoIbge(), cidades.get("004").getCodigoIbge());
  }
  
}
