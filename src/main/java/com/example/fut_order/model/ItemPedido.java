package com.example.fut_order.model;

import com.example.fut_order.model.enums.Tamanho;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "itens_pedidos")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tamanho", nullable = false)
    private Tamanho tamanho;

    @Column(name = "nome_personalizacao")
    private String nomePersonalizacao;

    @Column(name = "numero_personalizacao")
    private Integer numeroPersonalizacao;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "preco_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoUnitario;


    //muitos itens pertence a um pedido
    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "camisa_id", nullable = false)
    private Camisa camisa;

    public ItemPedido() {
    }

    public ItemPedido(Long id, Tamanho tamanho, String nomePersonalizacao, Integer numeroPersonalizacao, Integer quantidade, BigDecimal precoUnitario) {
        this.id = id;
        this.tamanho = tamanho;
        this.nomePersonalizacao = nomePersonalizacao;
        this.numeroPersonalizacao = numeroPersonalizacao;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public String getNomePersonalizacao() {
        return nomePersonalizacao;
    }

    public void setNomePersonalizacao(String nomePersonalizacao) {
        this.nomePersonalizacao = nomePersonalizacao;
    }

    public Integer getNumeroPersonalizacao() {
        return numeroPersonalizacao;
    }

    public void setNumeroPersonalizacao(Integer numeroPersonalizacao) {
        this.numeroPersonalizacao = numeroPersonalizacao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
