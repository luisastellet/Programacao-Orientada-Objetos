package com.carlosribeiro.dao.impl;

import com.carlosribeiro.dao.DAOGenerico;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class DAOGenericoImpl<K, V> implements DAOGenerico<K, V> {

    protected final LinkedHashMap<K, V> map = new LinkedHashMap<>(16);

    public V incluir(K id, V obj) {
        return map.put(id, obj);
    }

    public V alterar(K id, V obj) {
        return map.put(id, obj);
    }

    public V remover(K id) {
        return map.remove(id);
    }

    public V recuperarPorId(K id) {
        return map.get(id);
    }

    public List<V> recuperarTodos() {
        return new ArrayList<>(map.values()); // .stream().toList();
    }
}
