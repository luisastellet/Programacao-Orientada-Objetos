package com.carlosribeiro.util;

import com.carlosribeiro.anotacao.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class FabricaDePrincipais {
    private static final Map<Class<?>, Object> map = new HashMap<>();
    public static <T> T getPrincipal(Class<T> tipo) {
        Object obj = map.get(tipo);
        if (obj == null) {
            try {
                obj = tipo.getDeclaredConstructor().newInstance();
                map.put(tipo, obj);
            } catch (InstantiationException |
                     IllegalAccessException |
                     InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        for (Field campo : tipo.getDeclaredFields()) {
            if (campo.isAnnotationPresent(Autowired.class)) {
                try {
                    campo.setAccessible(true);
                    campo.set(obj, FabricaDeServicos.getServico(campo.getType()));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return (T) obj;
    }
}
