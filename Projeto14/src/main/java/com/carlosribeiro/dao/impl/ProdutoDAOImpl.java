package com.carlosribeiro.dao.impl;

import com.carlosribeiro.model.Produto;
import com.carlosribeiro.dao.ProdutoDAO;
import com.carlosribeiro.util.ComparatorDeProdutosPorNome;

import java.util.*;

public class ProdutoDAOImpl implements ProdutoDAO {

    private Map<Integer, Produto> mapDeProdutos = new LinkedHashMap<>();

    @Override
    public Produto incluir(Produto produto) {
        return mapDeProdutos.put(produto.getNumero(), produto);
    }

    @Override
    public Produto remover(int numero) {
        return mapDeProdutos.remove(numero);
    }

    @Override
    public Produto recuperarProdutoPorNumero(int numero) {
        return mapDeProdutos.get(numero);
    }

    @Override
    public List<Produto> recuperarProdutos() {
        return new ArrayList<>(mapDeProdutos.values());
    }

    @Override
    public List<Produto> recuperarProdutosOrdenadosAscendentementePorNome() {
        List<Produto> lista = new ArrayList<>(mapDeProdutos.values());
        // lista.sort(new ComparatorDeProdutosPorNome());
        lista.sort((p1, p2) -> p1.getNome().compareTo(p2.getNome()));
        return lista;
    }

    @Override
    public List<Produto> recuperarProdutosOrdenadosDescendentementePorNome() {
        List<Produto> lista = new ArrayList<>(mapDeProdutos.values());
        // lista.sort(new ComparatorDeProdutosPorNome());
        lista.sort((p1, p2) -> p1.getNome().compareTo(p2.getNome()));
        Collections.reverse(lista);
        return lista;
    }

    @Override
    public boolean listaVazia() {
        return mapDeProdutos.isEmpty();
    }
}
