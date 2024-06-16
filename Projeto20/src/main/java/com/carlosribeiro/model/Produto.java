package com.carlosribeiro.model;

import com.carlosribeiro.util.Id;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Produto implements Serializable {
    private int id;
    private String nome;
    private double lanceMinimo;
    private double valorUltimoLance;
    private List<Lance> lances;

    private static final NumberFormat NF; // Formatador para imprimir e efetuar o parse de números

    // Formatador para imprimir e efetuar o parse de objetos date-time
    static {
        NF = NumberFormat.getNumberInstance(new Locale("pt", "BR"));

        NF.setMaximumFractionDigits(2);       // O default é 3.
        NF.setMinimumFractionDigits(2);
    }

    public Produto(String nome, double lanceMinimo) {
        this.nome = nome;
        this.lanceMinimo = lanceMinimo;
        this.lances = new ArrayList<>();
    }

    public String toString() {
        return "Número = " + id +
                "  Nome = " + nome +
                "  Lance mínimo = " + getLanceMinimoMasc() +
                "  Valor do último lance = " + getValorUltimoLanceMasc();
    }

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public double getLanceMinimo() {
        return lanceMinimo;
    }

    public String getLanceMinimoMasc() {
        return NF.format(lanceMinimo);
    }

    public double getValorUltimoLance() {
        return valorUltimoLance;
    }

    public String getValorUltimoLanceMasc() {
        return NF.format(valorUltimoLance);
    }

    public List<Lance> getLances() {
        return lances;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLanceMinimo(double lanceMinimo) {
        this.lanceMinimo = lanceMinimo;
    }

    public void setValorUltimoLance(double valorUltimoLance) {
        this.valorUltimoLance = valorUltimoLance;
    }

    public void setLances(List<Lance> lances) {
        this.lances = lances;
    }
}