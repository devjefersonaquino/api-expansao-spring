package com.expansao.java.service;

import com.expansao.java.dto.LojaDto;
import com.expansao.java.dto.ProdutoDto;
import com.expansao.java.model.Loja;
import com.expansao.java.model.Produto;
import com.expansao.java.repository.LojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LojaService {

    @Autowired
    private LojaRepository lojaRepository;

    public List<LojaDto> buscaLojas() throws Exception{

        List<Loja> lojas = lojaRepository.findAll();

        if(lojas.isEmpty()){
            throw new Exception("Não há loja cadastrada!");
        }

        return mapLojaDto(lojas);
    }

    public void adicionaLoja(LojaDto loja){
        Loja lojaEntity = new Loja();

        lojaEntity.setNome(loja.getNome());
        lojaEntity.setEndereco(loja.getEndereco());
        lojaEntity.setTelefone(loja.getTelefone());

        lojaRepository.save(lojaEntity);
    }

    public void editarLoja(LojaDto loja, Long id){

        lojaRepository.findById(id).ifPresentOrElse(item->{
            item.setNome(loja.getNome());
            item.setEndereco(loja.getEndereco());
            item.setTelefone(loja.getTelefone());
        }, () -> {
            throw  new NoSuchElementException();
        });
    }

    public void deletarLoja (Long id){
        Loja loja = lojaRepository.findById(id).get();
        lojaRepository.delete(loja);
    }

    private List<LojaDto> mapLojaDto(List<Loja> lojas){

        List<LojaDto> lojaDtos = new ArrayList<>();

        lojas.forEach(item->{
            LojaDto dto = new LojaDto(
                    item.getNome(),
                    item.getEndereco(),
                    item.getTelefone());
            lojaDtos.add(dto);
        });

        return lojaDtos;
    }

}
