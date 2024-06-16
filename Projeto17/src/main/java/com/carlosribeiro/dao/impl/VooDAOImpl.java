package com.carlosribeiro.dao.impl;

import com.carlosribeiro.dao.VooDAO;
import com.carlosribeiro.model.Lance;
import com.carlosribeiro.model.Produto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class VooDAOImpl extends DAOGenericoImpl<Lance> implements VooDAO {

    public List<Lance> recuperarTodosOsLancesDeUmProduto(int id) {
        return map.values()
                          .stream()
                          .filter((lance) -> lance.getProduto().getId() == id)
                          .toList();
    }
}
