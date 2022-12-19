package com.expansao.java.controller;

import com.expansao.java.dto.ProdutoDto;
import com.expansao.java.service.LojaService;
import com.expansao.java.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private LojaService lojaService;

    @GetMapping("/loja/{idLoja}")
    public ResponseEntity<List<ProdutoDto>> buscarProdutosPorLoja(@PathVariable Long idLoja){
        return new ResponseEntity<List<ProdutoDto>>(produtoService.produtosPorLoja(idLoja), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity criarNovoProduto(@RequestBody ProdutoDto produto){
        try {
            produtoService.adicionarProduto(produto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity editarProduto(@RequestBody ProdutoDto produto, @PathVariable Long id){
        try {
            produtoService.editarProduto(produto, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removerProduto(@PathVariable Long id){
        try {
            produtoService.deletarProduto(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
