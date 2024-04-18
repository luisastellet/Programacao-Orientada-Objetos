package com.carlosribeiro;

public class Gerente extends Empregado
{
	private Empregado secretaria;

	public Gerente(String nome, double salario, Empregado secretaria)
	{
		super(nome, salario);
		this.secretaria = secretaria;
    }
    
   	public void setSecretaria(Empregado e) {
		this.secretaria = e;
   	}

   	public Empregado getSecretaria() {
		return this.secretaria;
   	}

	@Override
	public String toString() {
		return "Gerente{" +
				" nome = " + this.getNome() +
				" nome da secretaria=" + secretaria.getNome() +
				'}';
	}
}