package com.carlosribeiro;

import corejava.Console;

import java.util.ArrayList;


public class Principal
{	public static void main (String[] args)
	{		
		final int TAM = Console.readInt("Informe o tamanho da lista: ");
		//"Console.readInt" é tipo o scanf
		//final não poe ser alterada o valor, static pode
		//nome de variável static é todo maiúsculo

		ArrayList listaDeEmpregados = new ArrayList(TAM);

		String nome;
		double salario;
		Empregado umEmpregado;

		boolean continua = true;
		while (continua)
		{	System.out.println("\nO que você deseja fazer?");
			//prinln já muda de linha então esse \n tá deixando uma linha em branco
			// 'caractere/número' e "string" (char; String)
			System.out.println('\n' + "1. Cadastrar um empregado");
			System.out.println("2. Alterar um empregado");
			System.out.println("3. Remover um empregado");
			System.out.println("4. Listar relação de empregados");
			System.out.println("5. Sair");
						
			int opcao = Console.readInt('\n' + "Digite um número entre 1 e 5:");			
					
			switch (opcao)
			{	case 1:
					nome = Console.readLine("Informe o nome: ");
					salario = Console.readDouble("Informe o salário: ");
					//readLine; readDouble; readInt
					
					umEmpregado = new Empregado(nome, salario);

					//adicionando na lista:
					// variáveis começam com letra minúscula e Classes com maiúscula
					listaDeEmpregados.add(umEmpregado);
				
				//Vector parece ArrayList, mas vai crescendo com algum tamanho Vector(10,2) -> tamanho 10 e cresce de 2 em 2

					System.out.println("Empregado " + umEmpregado.getNome() + " cadastrado com sucesso.");

					break;
				case 2:
										
					break;
				case 3:

					break;
				case 4:

					for(int i=0; i>listaDeEmpregados.size(); i++){
						umEmpregado = (Empregado)listaDeEmpregados.get(i);
						//(Empregado) é o casting que precisa ser feito para reafirmar que estou entregando um Empregado
						System.out.println(umEmpregado.getNome());
					}

					break;
				case 5:
					continua = false;
					break;
				default:
					System.out.println('\n' + "Opção inválida!");			
					break;
			}
		}		
	}
}

// ANOTAÇÕES DA AULA DO DIA 27/03
/*
*  Object(getClass) -> Pessoa (nome, endereço) -> Empregado (salário)
* - Quando eu fizer new Empregado, terei que ceder nome, endereço e slário, pq Empregado é "filho" de Pessoa
* - Todo Empregado é uma Pessoa, mas nem toda Pessoa é um Empregado
*
* Se fizer ArrayList() sem o tamanho específico ele cria com 10, se precisar aumentar 1, ele vai crescer sem vc saber o quanto, perde o conftrole sobre o tamanho do array
*
*
* */

