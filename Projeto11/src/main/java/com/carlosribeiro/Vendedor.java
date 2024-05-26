package com.carlosribeiro;

public class Vendedor extends Empregado
{
	private double percComissao;
	private double vendasNoMes;
	
	public Vendedor (String nome, double salario, double percComissao, double vendasNoMes)
	{
		super(nome, salario);
		this.percComissao = percComissao;
		this.vendasNoMes = vendasNoMes;
	}

	public void aumentaSalario (double percentualDeAumento)
	{
		this.setSalario(this.getSalario() * (1 + percentualDeAumento/100));
	}

	public double getPercComissao() {
		return percComissao;
	}

	public void setPercComissao(double percComissao) {
		this.percComissao = percComissao;
	}

	public double getVendasNoMes() {
		return vendasNoMes;
	}

	public void setVendasNoMes(double vendasNoMes) {
		this.vendasNoMes = vendasNoMes;
	}

	public double getSalarioTotal()
	{
		return salario + vendasNoMes * percComissao /100;
	}
}