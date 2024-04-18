package com.carlosribeiro;

import java.util.ArrayList;

public class ListaDeObjetos<E> extends ArrayList<E> {
    private int corrente;

    public ListaDeObjetos(int tam) {
        super(tam);
        corrente = -1;
    }

    public boolean remover(int numero) {
        int n = this.localizar(numero);

        if (n != -1) {
            this.remove(n);
            return true;
        }
        return false;
    }

    private int localizar(int numero) {
        // Because there's a cache of Integer from -128 to 127.
        // Using valueOf for that range will return those instance instead of
        // create a new instance again and again.
        Integer num = Integer.valueOf(numero);  // new Integer(numero); deprecated

        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).equals(num)) {
                return i;
            }
        }
        return -1;
    }

    public E recuperar(int numero) {
        int n = this.localizar(numero);

        if (n != -1) {
            return this.get(n);
        }
        return null;
    }

    public E recuperarPrimeiro() {
        if (this.isEmpty())
            return null;
        corrente = 0;
        return this.get(corrente);
    }

    public E recuperarProximo() {
        if (corrente == -1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        corrente++;
        if (corrente < this.size()) {
            return this.get(corrente);
        } else {
            corrente = -1;
            return null;
        }
    }
}

