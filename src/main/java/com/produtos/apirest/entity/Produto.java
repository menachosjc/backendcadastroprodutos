package com.produtos.apirest.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.produtos.apirest.jsonFormat.View;
import com.produtos.apirest.entity.Usuario;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "produto")
public class Produto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({View.Usuario.class,View.Produto.class})
    @Column(name = "produto_id")
    private long idProduto;

    @JsonView({View.Usuario.class,View.Produto.class})
    @Column(name = "produto_nome")
    private String nomeProduto;

    @JsonView({View.Usuario.class,View.Produto.class})
    @Column(name = "produto_qtd")
    private int quantidadeProduto;

    @JsonView({View.Usuario.class,View.Produto.class})
    @Column(name = "produto_valor")
    private int valorProduto;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_produto",
            joinColumns = {@JoinColumn(name = "produto_id")},
            inverseJoinColumns = {@JoinColumn(name = "usuario_id")}
    )
    private Set<Usuario> usuarios;

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public int getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(int valorProduto) {
        this.valorProduto = valorProduto;
    }
}
