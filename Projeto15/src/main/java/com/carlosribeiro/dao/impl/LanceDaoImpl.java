package com.carlosribeiro.dao.impl;

import com.carlosribeiro.dao.LanceDAO;
import com.carlosribeiro.model.Lance;
import com.carlosribeiro.model.Produto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class LanceDaoImpl implements LanceDAO {

    private final LinkedHashMap<Integer, Lance> mapDeLances = new LinkedHashMap<>(16);

    public Lance incluir(Lance lance) {
        return mapDeLances.put(lance.getId(), lance);
    }

    public Lance alterar(Lance lance) {
        return mapDeLances.put(lance.getId(), lance);
    }

    public Lance remover(Integer id) {
        return mapDeLances.remove(id);
    }

    public Lance recuperarPorId(Integer id) {
        return mapDeLances.get(id);
    }

    public List<Lance> recuperarTodos() {
        return new ArrayList<>(mapDeLances.values()); // .stream().toList();
    }

    public List<Lance> recuperarTodosOsLancesDeUmProduto(int id) {
        return mapDeLances.values()
                          .stream()
                          .filter((lance) -> lance.getProduto().getId() == id)
                          .toList();
    }
}
