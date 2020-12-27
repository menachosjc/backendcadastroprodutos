package com.produtos.apirest.repository;

import com.produtos.apirest.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoRepository extends JpaRepository <Produto, Long> {

    @Query("select a from Produto a inner join a.usuarios u where u.idUsuario = ?1")
    public List<Produto> getProdutosPorUsuario(Long idUsuario);



    public Produto findByIdProduto(Long idProduto);

}
