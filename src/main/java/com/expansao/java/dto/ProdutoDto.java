package com.expansao.java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProdutoDto {

    private Long id;
    private Long idLoja;
    private String marca;
    private String modelo;
    private String descricao;
    private BigDecimal preco;



}
