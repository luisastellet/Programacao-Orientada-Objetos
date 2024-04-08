package com.carlosribeiro;

import java.util.*;

//criando uma classe genérica <E>
//no fundo no fundo a gente sabe que ListaDeObjetos é praticamente um ArrayList, só tem esse int corrente q é "bobeira"
//com esse extends, todas as funções de "ArrayList" ficam úteis para ListaDeObjetos, assim não precisamos das funções que criamos (adicionar, vazia, tamanho, remover, ...)
public class ListaDeObjetos<E> extends ArrayList<E> {
//    private final ArrayList<E> lista; //pra aquele objeto, a lista é constante "Empregados"
    private int corrente;

//    public ListaDeObjetos() {
//        lista = new ArrayList<>();
//        corrente = -1;
//    }

//    public void setLista(ArrayList<E> lista){
//        //método set normalmente é void
//        this.lista = lista;
//    }

    public ListaDeObjetos(int tam) {
        //vc tá chamando o método construtor de ArrayList, ele que cria o ArrayList de verdade
        super(tam);
        corrente = -1;
    }

//    public void adicionar(E obj) {
//        lista.add(obj);
//    }

//    public int tamanho() {
//        return lista.size();
//    }

    public boolean remover(int numero) {
        int posicao = localizar(numero);
        if (posicao == -1) {
            return false;
        }
        else {
            this.remove(posicao);
            return true;
        }

    }

    public E recuperar(int numero) {
        int posicao = localizar(numero);
        if (posicao == -1) {
            return null;
        }
        else {
            return this.get(posicao);
        }
    }

    private int localizar(int numero) {
        for(int i = 0; i < this.size(); i++) {
            if (this.get(i).equals(numero)) {
                return i;
            }
        }
        return -1;
    }

//    public boolean vazia() {
//        return lista.isEmpty();
//    }


    public E recuperarPrimeiro() {
        corrente = 0;
        return this.get(corrente);
    }

    public E recuperarProximo() {
        if (corrente != -1) {
            corrente++;
            if (corrente < this.size()) {
                return this.get(corrente);
            }
            else {
                corrente = -1;
                return null;
            }
        }
        else {
            throw new ArrayIndexOutOfBoundsException(
                    "Tentando acessar uma posição inválida do array.");
        }
    }
}

