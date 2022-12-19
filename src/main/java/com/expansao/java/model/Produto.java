package com.expansao.java.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "loja_id", referencedColumnName = "id")
    private Loja loja;
    private String marca;
    private String modelo;
    private String descricao;
    private BigDecimal preco;

    public Produto(Loja loja, String marca, String modelo, String descricao, BigDecimal preco) {
        this.loja = loja;
        this.marca = marca;
        this.modelo = modelo;
        this.descricao = descricao;
        this.preco = preco;
    }
}
