package com.seniortest.api.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.seniortest.api.dtos.CidadeDTO;
import com.seniortest.api.dtos.CidadeSimpleDTO;
import com.seniortest.api.models.Cidade;
import com.seniortest.api.response.DefaultResponse;
import com.seniortest.api.response.PaginationResponse;
import com.seniortest.api.response.TrackingResponse;
import com.seniortest.api.services.CidadeService;

@RestController
@RequestMapping(path="/cidades")
public class CidadeController {

  @Autowired
  private CidadeService cidadeService;
  
  @GetMapping
  @ResponseBody
  public ResponseEntity<PaginationResponse<CidadeSimpleDTO>> getPaged(@RequestParam(name = "page", defaultValue = "1") int page) {
    PaginationResponse<CidadeSimpleDTO> response = cidadeService.getAllPaged(page);
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<Cidade> create(@RequestBody Cidade cidade){
    try {
      Cidade _cidade = cidadeService.save(cidade);
      return new ResponseEntity<>(_cidade, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping(value = "/search")
  @ResponseBody
  public ResponseEntity<DefaultResponse<CidadeDTO>> searchByCEP(@RequestParam(name = "cep") String cep){
    DefaultResponse<CidadeDTO> response = new DefaultResponse<>();
    try {
      response.setData(cidadeService.searchByCEP(cep)); 
      response.setMessage("Acho que achou alguma coisa");
      return ResponseEntity.ok(response);
    } catch (IOException e) {
      response.setMessage("Houve um erro boladão");
      return ResponseEntity.badRequest().body(response);
    }
  }


  @GetMapping(value = "/tracking-route")
  @ResponseBody
  public ResponseEntity<TrackingResponse<CidadeSimpleDTO>> searchByCEP(@RequestParam(name = "ceps") List<String> ceps){
    TrackingResponse<CidadeSimpleDTO> response = new TrackingResponse<>();
    List<CidadeSimpleDTO> cidades = cidadeService.getRouteByCeps(ceps); 

    response.setData(cidades);
    response.setTracking(ceps);

    return ResponseEntity.ok(response);
  }

}
