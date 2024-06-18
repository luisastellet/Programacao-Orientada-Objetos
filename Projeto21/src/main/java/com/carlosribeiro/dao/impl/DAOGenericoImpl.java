package com.carlosribeiro.dao.impl;

import com.carlosribeiro.dao.DAOGenerico;
import com.carlosribeiro.util.Id;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DAOGenericoImpl<V> implements DAOGenerico<V> {

    protected Map<Integer, V> map = new LinkedHashMap<>(16);
    private int contador;

    public Map<Integer, V> getMap() {
        return map;
    }

    public void setMap(Map<Integer, V> map) {
        this.map = map;
    }

    @Override
    public Integer getContador() {
        return contador;
    }

    @Override
    public void setContador(Integer contador) {
        this.contador = contador;
    }

    public V incluir(V obj) {
         try {
             Method metodo = recuperarMetodoAnotadoComId(obj);
             String metodoString = metodo.getName().replace("get", "set");
             obj.getClass().getMethod(metodoString, Integer.class).invoke(obj, ++contador);
             return map.put((Integer)metodo.invoke(obj), obj);
        } catch (IllegalAccessException |
                 InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private Method recuperarMetodoAnotadoComId(V obj) {
        for (Method metodo : obj.getClass().getDeclaredMethods()) {
            if (metodo.isAnnotationPresent(Id.class)) {
                return metodo;
            }
        }
        throw new RuntimeException("Deve haver um método anotado com @Id");
    }

    public V alterar(V obj) {
        try {
            Method metodo = recuperarMetodoAnotadoComId(obj);
            String metodoString = metodo.getName().replace("get", "set");
            obj.getClass().getMethod(metodoString, Integer.class).invoke(obj, ++contador);
            return map.put((Integer)metodo.invoke(obj), obj);
        } catch (IllegalAccessException |
                 InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
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
