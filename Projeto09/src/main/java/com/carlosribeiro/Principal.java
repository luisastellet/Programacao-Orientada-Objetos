package com.carlosribeiro;

import corejava.Console;

public class Principal
{	public static void main (String[] args)
	{	
		final int TAM = Console.readInt('\n' + "Informe o tamanho da lista de empregados: ");
		ListaDeObjetos<Empregado> listaDeEmpregados = new ListaDeObjetos<>(TAM);
		String nome;
		double salario;
		Empregado umEmpregado, umaSecretaria;

		boolean continua = true;
		while (continua)
		{	System.out.println('\n' + "==============================================================");
			System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar um empregado");
			System.out.println("2. Alterar um empregado");
			System.out.println("3. Remover um empregado");
			System.out.println("4. Listar relação de empregados");
			System.out.println("5. Listar apenas Número e Nome dos empregados");
			System.out.println("6. Listar nome dos gerentes e das secretárias");
			System.out.println("7. Sair");
						
			int opcao = Console.readInt('\n' + "Digite um número entre 1 e 7:");
			
		  	switch (opcao)
			{	case 1:
				{	nome = Console.readLine('\n' + "Digite o nome do empregado: ");
					salario = Console.readDouble("Digite o salario do empregado: ");

					String resposta = Console.readLine('\n' + "O empregado que você deseja "
							+ "cadastrar é um gerente? (s/n)");

					if (resposta.equalsIgnoreCase("s"))
					{	umaSecretaria = null;
						do
						{	int numeroSecretaria = Console.readInt('\n' + "Informe o número "
								+ "da secretária: ");
						
							umaSecretaria = listaDeEmpregados.recuperar(numeroSecretaria);
							
							if (umaSecretaria == null)
								System.out.println('\n' + "secretária Inexistente!");
						}
						while (umaSecretaria == null); 	

						umEmpregado = new Gerente(nome, salario, umaSecretaria);
						listaDeEmpregados.add(umEmpregado);
						System.out.println('\n' + "Gerente cadastrado com sucesso!");
					}
					else
					{		
						umEmpregado = new Empregado(nome, salario);
						listaDeEmpregados.add(umEmpregado);
						System.out.println('\n' + "Empregado cadastrado com sucesso!");
					}
					break;
				}
				case 2:	// Alterar
				{	int resposta = Console.readInt('\n' + "Digite o número do empregado "
						+ "que você deseja alterar: ");
								
				    umEmpregado = listaDeEmpregados.recuperar(resposta);
													
					if (umEmpregado != null)		 
					{	
						System.out.println('\n' + "O que você deseja alterar?");
						System.out.println('\n' + "1. Nome");
						System.out.println("2. Salario");
						System.out.println("3. Secretária (Caso o empregado seja Gerente)");

						int opcaoAlteracao = Console.readInt('\n' + "Digite um número de 1 a 3:");
					
				  		switch (opcaoAlteracao)
						{	case 1:
								String novoNome = Console.readLine("Digite o novo nome: ");
								umEmpregado.setNome(novoNome);
								System.out.println('\n' + "Alteração de nome efetuada com sucesso!");
								break;

							case 2:
								double novoSalario = Console.readDouble("Digite o novo salário: ");
								umEmpregado.setSalario(novoSalario);
								System.out.println('\n' + "Alteração de salário efetuada com sucesso!");
								break;

							case 3:
								if (!(umEmpregado instanceof Gerente))
								{	System.out.println('\n' +
										"Este empregado não é um gerente, logo não possui secretária!");
									break;
								}
	
								umaSecretaria = null;
								do
								{	int numeroSecretaria = Console.readInt('\n' + "Informe "
										+ "o número da nova secretária: ");
								
									umaSecretaria = listaDeEmpregados.recuperar(numeroSecretaria);
									
									if (umaSecretaria == null)
										System.out.println('\n' + "Secretária Inexistente!");
								}
								while (umaSecretaria == null);

								((Gerente) umEmpregado).setSecretaria(umaSecretaria);
								System.out.println('\n' + "Alteração de secretária efetuada com sucesso!");
								break;
	
							default:
								System.out.println('\n' + "Opção inválida!");
								break;
						}
					}
					else
					{	System.out.println('\n' + "Empregado não encontrado!");
					}						
					break;
				}

				case 3:	// Remover
				{	
					int resposta = Console.readInt('\n' +
						"Digite o número do empregado que você deseja remover: ");
					
					if (listaDeEmpregados.remover(resposta)) 
						System.out.println('\n' + "Empregado removido com sucesso!");
					else
						System.out.println('\n' + "Empregado não encontrado!");
					
					break;
				}
	
				case 4:	// Listar tudo
					if (listaDeEmpregados.isEmpty())
						System.out.println('\n' + "Não há empregados na lista.");
					else
					{	
						for(Empregado empregado : listaDeEmpregados)
						{
							System.out.println(empregado);
						}
					}
					break;

				case 5:	// Listar Numero e Nome
					if (listaDeEmpregados.isEmpty())
						System.out.println('\n' + "Não há empregados na lista.");
					else
					{	
						for(Empregado empregado : listaDeEmpregados)
						{
							System.out.println("Número = " + empregado.getNumero() +
									           "   Nome = " + empregado.getNome());
						}
					}
					break;

				case 6:	// Listar nome dos gerentes e das secretárias
					if (listaDeEmpregados.isEmpty())
						System.out.println('\n' + "Não há empregados na lista.");
					else
					{	System.out.println();

						boolean achou = false;
						for(Empregado empregado : listaDeEmpregados)
						{
							if (empregado instanceof Gerente)
							{	
								achou = true;
								System.out.println ("Nome do gerente = " + empregado.getNome()        
                                        + " 	 Nome da secretária = " + ((Gerente) empregado).getSecretaria().getNome());
							}
						}
						
						if (!achou)
							System.out.println('\n' + "Não há gerentes cadastrados!");
					}
					break;
	
				case 7:	// Sair
					continua = false;
					break;
	
				default:
					System.out.println('\n' + "Opção inválida!");
			}
		}		
	}
}