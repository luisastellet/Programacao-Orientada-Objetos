package com.carlosribeiro.model;

import java.text.NumberFormat;
import java.util.Locale;

public class Produto
{
	private static int contador = 0;
	private final int numero;
	private String nome;
	private double lanceMinimo;
	// Formatador para imprimir e efetuar o parse de números
	private final static NumberFormat NF;

	// Formatador para imprimir e efetuar o parse de objetos date-time
	static
	{	NF = NumberFormat.getNumberInstance(new Locale("pt","BR"));

		NF.setMaximumFractionDigits (2);	   // O default é 3.
		NF.setMinimumFractionDigits (2);
	}

	public Produto(String nome, double lanceMinimo)
	{
		this.numero = ++contador;	// Caso a data de admissão seja
									// inválida o contador não será
									// incrementado.
		this.nome = nome;
		this.lanceMinimo = lanceMinimo;

		/* 	O método parseInt() gera a exceção NumberFormatException
			e o método substring() gera a exceção
			StringIndexOutOfBoundsException. E o método LocalDate.parse gera
			a exceção DateTimeParseException. Estas exceções são do tipo
			unchecked, logo o compilador não obriga o programador a
      		declarar que este  método gera estas exceções.

			(throws NumberFormatException, StringIndexOutOfBoundsException) 

		  	Toda exceção que deriva das  classes Error e  RuntimeException
		  	são consideradas  unchecked exceptions. O programador deve se
		   	preocupar com as exceções do tipo checked, manipulando-as
		   	ou anunciando que elas podem ser propagadas.
				
		   	Exceções do tipo unchecked são:
		   	- Um acesso fora dos limites de um array
		   	- Um acesso a um ponteiro null, etc.
				
		   	Hierarquia das classes que representam exceções:
		
	      		Throwable
					Error                 -  unchecked exceptions
					Exception
					    RuntimeException   -  unchecked exceptions
                           ...
						IOException        -  checked exceptions
						SQLException
						...
		*/
	}

	public String toString()
	{	return "Número = " + numero +
			   "  Nome = " + nome +
			   "  Lance mínimo = " + getLanceMinimoMasc();
	}

	public int getNumero()
	{	return numero;
	}

	public String getNome()
	{	return nome;
	}

	public double getLanceMinimo()
	{	return lanceMinimo;
	}

	public String getLanceMinimoMasc()
	{	
		return NF.format(lanceMinimo);
	}

	public void setNome(String nome)
	{	this.nome = nome;
	}
		
	public void setLanceMinimo(double lanceMinimo)
	{	this.lanceMinimo = lanceMinimo;
	}
}