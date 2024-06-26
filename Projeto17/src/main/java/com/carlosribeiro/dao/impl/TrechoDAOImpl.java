package com.carlosribeiro.dao.impl;

import com.carlosribeiro.dao.TrechoDAO;
import com.carlosribeiro.model.Produto;

import java.util.List;

public class TrechoDAOImpl extends DAOGenericoImpl<
        Produto> implements TrechoDAO {

    public List<Produto> recuperarProdutosOrdenadosPorNome() {
        return map.values()
                .stream()
                .sorted((p1, p2) -> p1.getNome().compareTo(p2.getNome()))
                .toList();
    }
}
