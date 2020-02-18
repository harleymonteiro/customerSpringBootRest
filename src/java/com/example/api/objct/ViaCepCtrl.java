/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.api.objct;


import java.io.Serializable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
/**
 *
 * @author threadtec
 */
public class ViaCepCtrl implements Serializable {
  private static final long serialVersionUID = 6046704732666502085L;

    /**
     *
     * @param cep
     * @return
     */
    public ResponseEntity<EnderecoTO> doObterCep(@PathVariable String cep) {
      
    return null;
    
  }
}
