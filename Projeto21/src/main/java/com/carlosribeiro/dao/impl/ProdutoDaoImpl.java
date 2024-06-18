package com.carlosribeiro.dao.impl;

import com.carlosribeiro.dao.ProdutoDAO;
import com.carlosribeiro.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProdutoDaoImpl extends DAOGenericoImpl<
        Produto> implements ProdutoDAO {

    public List<Produto> recuperarProdutosOrdenadosPorNome() {
        return map.values()
                .stream()
                .sorted((p1, p2) -> p1.getNome().compareTo(p2.getNome()))
                .toList();
    }
}
