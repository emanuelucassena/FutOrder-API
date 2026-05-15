package com.example.fut_order.model;

import com.example.fut_order.model.enums.StatusPedido;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_pedido", nullable = false)
    private LocalDateTime dataPedido;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pedido", nullable = false)
    private StatusPedido statusPedido;

    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itemPedidos;


    public Pedido() {
    }

    public Pedido(Long id, LocalDateTime dataPedido, StatusPedido statusPedido, BigDecimal valorTotal) {
        this.id = id;
        this.dataPedido = dataPedido;
        this.statusPedido = statusPedido;
        this.valorTotal = valorTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    @PrePersist
    protected void onCreate() {
        this.dataPedido = LocalDateTime.now();

        // Bônus: Garante que todo pedido novo nasça como PENDENTE por padrão,
        // caso ninguém tenha preenchido o status.
        if (this.statusPedido == null) {
            this.statusPedido = StatusPedido.PENDENTE;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
