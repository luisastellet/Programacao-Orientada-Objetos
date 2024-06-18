package com.carlosribeiro.util;

import com.carlosribeiro.anotacao.Autowired;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FabricaDeServicos {
    private static final Map<Class<?>, Object> map = new HashMap<>();
    public static <T> T getServico(Class<T> tipo) {
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
                    campo.set(obj, FabricaDeDaos.getDAO(campo.getType()));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return (T) obj;
    }
}
