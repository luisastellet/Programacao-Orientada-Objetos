package com.carlosribeiro;

import java.text.NumberFormat;
import java.util.Locale;

public class Produto
{
	private String nome;
	private double lanceMinimo;

	public Produto(String nome)	{
		this.nome = nome;
	}

	public Produto(String nome, double lanceMinimo) {
		this.nome = nome;
		this.lanceMinimo = lanceMinimo;
	}

	public String toString() {
		return "Nome = " + nome +
			   "  Lance mínimo = " + lanceMinimo;
	}

	public String getNome() {
		return nome;
	}

	public double getLanceMinimo() {
		return lanceMinimo;
	}

	public void setNome(String n) {
		nome = n;
	}
		
	public void setLanceMinimo(double s) {
		lanceMinimo = s;
	}
}