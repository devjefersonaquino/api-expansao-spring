package com.expansao.java.service;

import com.expansao.java.dto.ProdutoDto;
import com.expansao.java.model.Loja;
import com.expansao.java.model.Produto;
import com.expansao.java.repository.LojaRepository;
import com.expansao.java.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private LojaRepository lojaRepository;

    public List<ProdutoDto> produtosPorLoja(Long idLoja){

        List<Produto> produtosPorLoja = produtoRepository.findByLojaId(idLoja);

        return mapProdutoDto(produtosPorLoja);
    }

    public void adicionarProduto(ProdutoDto produto){

        Loja loja = lojaRepository.findById(produto.getIdLoja()).get();
        produtoRepository.save(new Produto( loja,
                                            produto.getMarca(),
                                            produto.getModelo(),
                                            produto.getDescricao(),
                                            produto.getPreco()));
    }

    public void editarProduto(ProdutoDto produto, Long id){

        produtoRepository.findById(id).ifPresentOrElse(item->{
            item.setDescricao(produto.getDescricao());
            item.setPreco(produto.getPreco());
        }, () -> {
            throw  new NoSuchElementException();
        });
    }

    public void deletarProduto(Long id){
        Produto produto = produtoRepository.findById(id).get();
        produtoRepository.delete(produto);
    }

    private List<ProdutoDto> mapProdutoDto(List<Produto> produtos){

        List<ProdutoDto> produtoDtos = new ArrayList<>();

        produtos.forEach(item->{
            ProdutoDto produtoDto = new ProdutoDto(
                    item.getId(),
                    item.getLoja().getId(),
                    item.getMarca(),
                    item.getModelo(),
                    item.getDescricao(),
                    item.getPreco());
            produtoDtos.add(produtoDto);
        });

        return produtoDtos;
    }
}
