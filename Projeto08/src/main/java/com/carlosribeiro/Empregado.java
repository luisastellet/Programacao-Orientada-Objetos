package com.carlosribeiro;

import java.text.NumberFormat;
import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Empregado 
{
	private static int contador = 0;
	private int numero;
	private String nome;
	private double salario;
	private ZonedDateTime dataHoraAdmissao;

	// Formatador para imprimir e efetuar o parse de números
	private static final NumberFormat NF;

	// Formatador para imprimir e efetuar o parse de objetos date-time
	private static final  DateTimeFormatter DTF;
	
	static
	{	NF = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
		DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

		NF.setMaximumFractionDigits (2);	   // O default é 3.
		NF.setMinimumFractionDigits (2);
	}

	public Empregado (String nome, double salario, String dataHoraAdmissao) throws DataHoraInvalidaException
	{
		setDataHoraAdmissao(dataHoraAdmissao);

		this.numero = ++contador;	// Caso a data de admissão seja
									// inválida o contador não será
									// incrementado.
		this.nome = nome;
		this.salario = salario;


		/* 	O método parseInt() gera a exceção NumberFormatException
			e o método substring() gera a exceção
			StringIndexOutOfBoundsException. E o método ZonedDateTime.of
			gera a exceção DateTimeException. Estas exceções são do tipo
			unchecked, logo o compilador não obriga o programador a
      		declarar que este  método gera estas exceções.

			(throws NumberFormatException, StringIndexOutOfBoundsException,
			        DateTimeException)

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
			   "  Salário = " + getSalarioMasc() +
			   "  Data de Admissão = " + getDataHoraAdmissaoMasc();
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

	public String getSalarioMasc()
	{	
		return NF.format(salario);
	}
		
	public String getDataHoraAdmissaoMasc()
	{
	 	System.out.println("Data e hora em UTC: " + dataHoraAdmissao);

		return DTF.format(dataHoraAdmissao.withZoneSameInstant(ZoneId.of("America/Sao_Paulo")));
	}

	public void setNome(String nome)
	{	this.nome = nome;
	}
		
	public void setSalario(double salario)
	{	this.salario = salario;
	}
		
	public void setDataHoraAdmissao (String novaDataHoraAdmissao) throws DataHoraInvalidaException
	{		
		try                         //  dd/mm/aaaa hh:mm:ss
		{
			int dia = Integer.parseInt(novaDataHoraAdmissao.substring(0,2));
			int mes = Integer.parseInt(novaDataHoraAdmissao.substring(3,5));
			int ano = Integer.parseInt(novaDataHoraAdmissao.substring(6,10));
			
			int hora =    Integer.parseInt(novaDataHoraAdmissao.substring(11,13));
			int minuto =  Integer.parseInt(novaDataHoraAdmissao.substring(14,16));
			int segundo = Integer.parseInt(novaDataHoraAdmissao.substring(17,19));

			this.dataHoraAdmissao = ZonedDateTime.of(
					ano, mes, dia, hora, minuto, segundo, 0,
					ZoneId.of("America/Sao_Paulo"))
					.withZoneSameInstant(ZoneId.of("UTC"));

			System.out.println("Data e hora em UTC: " + this.dataHoraAdmissao);
		}
		catch(StringIndexOutOfBoundsException | NumberFormatException | DateTimeException e)
		{
			throw new DataHoraInvalidaException("Data e hora inválida.");
		}
	}

	public boolean equals(Object num) {
		return numero == (Integer) num;
	}
}