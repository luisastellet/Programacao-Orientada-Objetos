package com.carlosribeiro.dao.impl;

import com.carlosribeiro.dao.ProdutoDAO;
import com.carlosribeiro.model.Produto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ProdutoDaoImpl implements ProdutoDAO {

    private final LinkedHashMap<Integer, Produto> mapDeProdutos = new LinkedHashMap<>(16);

    public Produto incluir(Produto produto) {
        return mapDeProdutos.put(produto.getId(), produto);
    }

    public Produto alterar(Produto produto) {
        return mapDeProdutos.put(produto.getId(), produto);
    }

    public Produto remover(Integer id) {
        return mapDeProdutos.remove(id);
    }

    public Produto recuperarPorId(Integer id) {
        return mapDeProdutos.get(id);
    }

    public List<Produto> recuperarTodos() {
        // Porque List tem método get(index) que Collection não possui.
        return new ArrayList<>(mapDeProdutos.values()); // .stream().toList();  // java.util.LinkedHashMap$LinkedProdutoalues
    }

    public List<Produto> recuperarProdutosOrdenadosPorNome() {
        return mapDeProdutos.values().stream().sorted((p1, p2) -> p1.getNome().compareTo(p2.getNome())).toList();
    }
}
