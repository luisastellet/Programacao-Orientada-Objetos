package com.carlosribeiro;

public class Gerente extends Empregado {

	public Gerente(String nome, double salario) {
		super(nome, salario);
    }
    
 	public void aumentaSalario(double percentual) {
		this.setSalario(this.getSalario() * (1 + 2 * percentual/100));
   	}
}