package com.carlosribeiro;
import java.util.*;

public class ListaDeObjetos {
    public class ListaDeObjetos {
        private ArrayList lista;
        private int corrente;

        public ListaDeObjetos(int tam) {
            lista = new ArrayList(tam);
            corrente = -1;
        }

        public void adicionar(Object obj) {
            lista.add(obj);
        }

        public int tamanho() {
            return lista.size();
        }

        public boolean remover(int numero) {
            //número é o "id" do empregado, não a posição por isso precisa localizar o index antes
            int posicao = this.localizar(numero);
            //this aponta pro objeto corrente, a listaDeEmpregados (opcional, só diz aonde eu vou localizar o número)
            if(numero == -1) return false;
            lista.remove(numero);
            return true;
        }

        private int localizar(int numero) {
            for(int i=0; i<lista.size(); i++) {
                if(lista.get(i).equals(numero)){
                    //a propria comparação sabe que só tem um número inteiro, por isso não dá problema
                    //lista.get retorna um object, entao preciso do equals para "número" virar um objeto
                    //o equals faz o boxing, considera um inteiro como um objeto para poder comparar com o retorno do lista.get(i) que é um objeto
                    //if(numero.equals()) não funciona pq numero é int não obj
                    return i;
            }


        }

        public Object recuperar(int numero) {
            int posicao = localizar(numero);
            if (posicao == -1) {
                return null;
            } else {
                return lista.get(posicao);
            }
        }



        public boolean vazia() {
            return lista.isEmpty();
        }


        public Object recuperarPrimeiro() {
            corrente = 0;
            return lista.get(corrente);
        }
    }
    
        public Object recuperarProximo() {
        if(corrente != -1) {
            corrente++;
            if(corrente < lista.size()) {
                return lista.get(corrente);
            }else {
                corrente = -1;
                return null;
            }
        }else {
            //quando ocorre erro de lógica, um erro inesperado "Runtime exception"
            throw_new new ArrayIndexOutOfBoundsException("Tentando acessar uma posição inválida do array");
        }



    }
}


}
