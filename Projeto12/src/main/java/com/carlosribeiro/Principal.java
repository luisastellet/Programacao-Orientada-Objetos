package com.carlosribeiro;

import corejava.Console;

import java.util.*;

public class Principal
{
	public static void main (String[] args) {

		final int TAM = Console.readInt('\n' +
				"Informe o tamanho da lista de produtos: ");

        Map<Integer, Produto> mapDeProdutos = new LinkedHashMap<>();

		String nome;
		double lanceMinimo;
		Produto umProduto;

		boolean continua = true;
		while (continua) {
			System.out.println('\n' + "========================================================");
			System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar um produto");
			System.out.println("2. Alterar um produto");
			System.out.println("3. Remover um produto");
			System.out.println("4. Listar relação de produtos");
            System.out.println("5. Listar produtos ordenados por nome");
            System.out.println("6. Listar produtos ordenados por nome descendentemente");
			System.out.println("7. Sair");

			int opcao = Console.readInt('\n' + "Digite um número entre 1 e 6:");

            switch (opcao) {
                case 1 -> {
                    nome = Console.readLine('\n' + "Digite o nome do produto: ");
                    lanceMinimo = Console.readDouble("Digite o lance mínimo do produto: ");
                    Produto produto = new Produto(nome, lanceMinimo);
                    mapDeProdutos.put(produto.getNumero(), produto);

                    System.out.println("Produto cadastrado com sucesso!");
                }
                case 2 ->    // Alterar
                {
                    int numero = Console.readInt('\n' +
                            "Digite o número do produto que você deseja alterar: ");

                    umProduto = mapDeProdutos.get(numero);

                    if (umProduto != null) {
                        System.out.println('\n' + "O que você deseja alterar?");
                        System.out.println('\n' + "1. Nome");
                        System.out.println("2. Lance Mínimo");

                        int opcaoAlteracao = Console.readInt('\n' + "Digite 1 ou 2:");

                        switch (opcaoAlteracao) {
                            case 1 -> {
                                String novoNome = Console.readLine("Digite o novo nome: ");
                                umProduto.setNome(novoNome);
                                System.out.println('\n' + "Alteração de nome efetuada com sucesso!");
                            }
                            case 2 -> {
                                double novoLanceMinimo = Console.readDouble("Digite o novo lance mínimo: ");
                                umProduto.setLanceMinimo(novoLanceMinimo);
                                System.out.println('\n' + "Alteração de lance mínimo efetuada com sucesso!");
                            }
                            default -> System.out.println('\n' + "Opção inválida!");
                        }
                    } else {
                        System.out.println('\n' + "Produto não encontrado!");
                    }
                }
                case 3 ->    // Remover
                {
                    int numero = Console.readInt('\n' +
                            "Digite o número do produto que você deseja remover: ");

                    if (mapDeProdutos.remove(numero) != null)
                        System.out.println('\n' + "Produto removido com sucesso!");
                    else
                        System.out.println('\n' + "Produto não encontrado!");

                }
                case 4 -> {    // Listar tudo
                    if (mapDeProdutos.isEmpty())
                        System.out.println('\n' + "Não há produtos cadastrados.");
                    else {
                        for (Produto produto : mapDeProdutos.values()) {
                            System.out.println(produto);
                        }
                    }
                }
                case 5 -> {    // Ordenar por nome

                    if (mapDeProdutos.isEmpty())
                        System.out.println('\n' + "Não há produtos na lista.");
                    else {
                        // sort retorna void
                        ArrayList<Produto> listaDeProdutos = new ArrayList<>(mapDeProdutos.values());
//                      listaDeProdutos.sort(new ComparatorDeProdutosPorNome());
                        listaDeProdutos.sort((p1, p2) -> p1.getNome().compareTo(p2.getNome()));
//                      listaDeProdutos.sort(new Comparator<Produto>() {
//                          @Override
//                          public int compare(Produto p1, Produto p2) {
//                              return p1.getNome().compareTo(p2.getNome());
//                          }
//                      });

                        for (Produto produto : listaDeProdutos) {
                            System.out.println("Número = " + produto.getNumero() +
                                    "   Nome = " + produto.getNome() +
                                    "   Lance mínimo = " + produto.getLanceMinimoMasc());
                        }
                    }
                }
                case 6 -> {    // Ordenar por nome na ordem inversa

                    if (mapDeProdutos.isEmpty())
                        System.out.println('\n' + "Não há produtos na lista.");
                    else {
                        // Collections.sort(listaDeProdutos, new ProdutoComparator());
                        ArrayList<Produto> listaDeProdutos = new ArrayList<>(mapDeProdutos.values());
                        listaDeProdutos.sort(new ComparatorDeProdutosPorNome().reversed());

                        for (Produto produto : listaDeProdutos) {
                            System.out.println("Número = " + produto.getNumero() +
                                    "   Nome = " + produto.getNome() +
                                    "   Lance mínimo = " + produto.getLanceMinimoMasc());
                        }
                    }
                }
                case 7 ->    // Sair
                        continua = false;
                default -> System.out.println('\n' + "Opção inválida!");
            }
		}
	}
}