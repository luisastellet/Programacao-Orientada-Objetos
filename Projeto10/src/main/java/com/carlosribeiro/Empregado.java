package com.carlosribeiro;

public class Empregado
{
	private static int contador = 0;
	private final int numero;
	private String nome;
	private double salario;
// ==>
		
// ==>	public Empregado (String nome, double salario)
	{	
		this.setSalario(salario);
		this.numero = ++contador;
		this.nome = nome;
	}

	public String toString()
	{	return "Número = " + numero +
			   "  Nome = " + nome +
			   "  Salário = " + salario;
	}

	public int getNumero()
	{	return numero;
	}
	public String getNome()
	{	return nome;
	}
	public double getSalario()
	{	return salario;
	}

	public void setNome(String n)
	{	nome = n;
	}
// ==>	public void setSalario(double s)
	{		
// ==>



	}

	public boolean equals(Object num) {
		return numero == (Integer) num;
	}
}









