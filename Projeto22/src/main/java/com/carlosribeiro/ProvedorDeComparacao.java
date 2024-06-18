package com.carlosribeiro;

class ProvedorDeComparacao {
    public int compararPorNome(Produto e1, Produto e2) {
        return e1.getNome().compareTo(e2.getNome());
    }
}