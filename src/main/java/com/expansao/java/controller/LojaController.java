package com.expansao.java.controller;

import com.expansao.java.dto.LojaDto;
import com.expansao.java.dto.ProdutoDto;
import com.expansao.java.model.Produto;
import com.expansao.java.service.LojaService;
import com.expansao.java.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loja")
public class LojaController {

    @Autowired
    private LojaService lojaService;
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<LojaDto>> buscaLojas() throws Exception {
        return new ResponseEntity<List<LojaDto>>(lojaService.buscaLojas(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity criaNovaLoja(@RequestBody LojaDto loja){
        try {
            lojaService.adicionaLoja(loja);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity editarLoja(@RequestBody LojaDto loja, @PathVariable Long id){
        try {
            lojaService.editarLoja(loja, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity removerLoja(@PathVariable Long id){
        try {
            lojaService.deletarLoja(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
