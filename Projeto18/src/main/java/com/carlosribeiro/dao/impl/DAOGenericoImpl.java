package com.carlosribeiro.dao.impl;

import com.carlosribeiro.dao.DAOGenerico;
import com.carlosribeiro.util.Id;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class DAOGenericoImpl<V> implements DAOGenerico<V> {

    protected final LinkedHashMap<Integer, V> map;
    private int contador;

    public DAOGenericoImpl() {
        this.map = new LinkedHashMap<>(16);
        this.contador = 0;
    }

    public V incluir(V obj) {
        Field campo = recuperarCampoIdentificador(obj);
        atribuirContadorACampo(obj, campo);
        return map.put(contador, obj);
    }

    private void atribuirContadorACampo(V obj, Field campo) {
        try {
            campo.setAccessible(true);
            campo.set(obj, ++contador);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Field recuperarCampoIdentificador(V obj) {
        for (Field campo : obj.getClass().getDeclaredFields()) {
            if (campo.isAnnotationPresent(Id.class)) {
                return campo;
            }
        }
        throw new RuntimeException("Deve haver um campo anotado com @Id");
    }

    public V alterar(V obj) {
        Field campo = recuperarCampoIdentificador(obj);
        atribuirContadorACampo(obj, campo);
        return map.put(contador, obj);
    }

    public V remover(Integer id) {
        return map.remove(id);
    }

    public V recuperarPorId(Integer id) {
        return map.get(id);
    }

    public List<V> recuperarTodos() {
        return new ArrayList<>(map.values()); // .stream().toList();
    }
}
