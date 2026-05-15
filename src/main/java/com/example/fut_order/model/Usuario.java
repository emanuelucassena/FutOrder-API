package com.example.fut_order.model;

import com.example.fut_order.model.enums.TipoPapel;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    // O banco não aceita e-mail nulo e nem e-mail duplicado
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    // Dizemos para o JPA: "Salve o NOME do Enum, não o número dele"
    @Enumerated(EnumType.STRING)
    @Column(name = "papel", nullable = false)
    private TipoPapel papel;

    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos;

    // Construtor vazio exigido pelo JPA
    public Usuario() {
    }

    public Usuario(Long id, String nome, String email, String senha, TipoPapel papel) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.papel = papel;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoPapel getPapel() {
        return papel;
    }

    public void setPapel(TipoPapel papel) {
        this.papel = papel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
