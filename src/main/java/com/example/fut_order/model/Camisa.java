package com.example.fut_order.model;

import com.example.fut_order.model.enums.TipoVersao;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "camisas")
public class Camisa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time", nullable = false)
    private String time;

    @Column(name = "temporada", nullable = false)
    private String temporada;

    @Enumerated(EnumType.STRING)
    @Column(name = "versao", nullable = false)
    private TipoVersao versao;

    //diz ao banco para guardar até 10 dígitos, sendo 2 após a vírgula
    @Column(name = "preco", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoBase;

    @Column(name = "qtd_estoque", nullable = false)
    private Integer qtdEstoque;

    public Camisa() {
    }

    public Camisa(Long id, String time, String temporada, TipoVersao versao, BigDecimal precoBase, Integer qtdEstoque) {
        this.id = id;
        this.time = time;
        this.temporada = temporada;
        this.versao = versao;
        this.precoBase = precoBase;
        this.qtdEstoque = qtdEstoque;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public TipoVersao getVersao() {
        return versao;
    }

    public void setVersao(TipoVersao versao) {
        this.versao = versao;
    }

    public BigDecimal getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(BigDecimal precoBase) {
        this.precoBase = precoBase;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Camisa camisa = (Camisa) o;
        return Objects.equals(id, camisa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
