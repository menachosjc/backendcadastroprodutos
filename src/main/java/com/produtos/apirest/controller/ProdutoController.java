package com.produtos.apirest.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.produtos.apirest.jsonFormat.View;
import com.produtos.apirest.entity.Produto;
import com.produtos.apirest.model.ProdutoForm;
import com.produtos.apirest.repository.ProdutoRepository;
import com.produtos.apirest.service.ProdutoService;
import com.produtos.apirest.service.SegurancaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @JsonView(View.Produto.class)
    @PostMapping("/novoproduto")
    public ResponseEntity<Produto> salvarProduto(@RequestBody ProdutoForm novoProduto, UriComponentsBuilder uriComponentsBuilder){
       Produto produto = produtoService.criarProduto(novoProduto);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(
                uriComponentsBuilder.path(
                        "/produto/" + produto.getIdProduto()).build().toUri());
       return new ResponseEntity<Produto>(produto, responseHeaders, HttpStatus.CREATED);
    }

    @JsonView(View.Produto.class)
    @GetMapping("produtos/usuario/{id}")
    public List<Produto> getProdutosUsuario(@PathVariable("id") Long id){

        return  produtoService.getProdutos(id);
    }

    @JsonView(View.Produto.class)
    @DeleteMapping(value = "remover/{idProduto}")
    public ResponseEntity<Boolean> removerProduto(@PathVariable("idProduto") Long idProduto){


        return ResponseEntity.ok(produtoService.removerProduto(idProduto));

    }

    @JsonView(View.Produto.class)
    @PutMapping("atualizarproduto/{idProduto}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable("idProduto") Long idProduto, @RequestBody ProdutoForm atualizarProduto){
        Produto produto = produtoRepository.findByIdProduto(idProduto);

        produto.setNomeProduto(atualizarProduto.getNomeProduto());
        produto.setQuantidadeProduto(atualizarProduto.getQuantidadeProduto());
        produto.setValorProduto(atualizarProduto.getValorProduto());

        final Produto atualizadoProduto = produtoRepository.save(produto);


        return ResponseEntity.ok(atualizadoProduto);
    }




    /*
    @DeleteMapping("/produto")
    @ApiOperation(value = "Deleta um produto")
    public void deletarProduto(@RequestBody ProdutoModel produto){
        produtoRepository.delete(produto);
    }

    @PutMapping("/produto")
    @ApiOperation(value = "Altera os dados de um produto")
    public ProdutoModel atualizarProduto(@RequestBody ProdutoModel produto){
        return produtoRepository.save(produto);
    }
    */


}
