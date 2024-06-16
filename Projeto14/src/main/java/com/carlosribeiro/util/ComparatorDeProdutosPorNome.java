package com.carlosribeiro.util;

import com.carlosribeiro.model.Produto;

import java.util.Comparator;

public class ComparatorDeProdutosPorNome implements Comparator<Produto> {

    @Override
    public int compare(Produto p1, Produto p2) {
        return p1.getNome().compareTo(p2.getNome());
    }
}
