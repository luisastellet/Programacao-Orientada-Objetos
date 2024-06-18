package com.carlosribeiro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Principal
{
	public static int compararPorNome(Produto p1, Produto p2) {
		return p1.getNome().compareTo(p2.getNome());
	}

	public static void main (String[] args) {
		List<Produto> listaDeProdutos = new ArrayList<>();

		listaDeProdutos.add(new Produto("ccc", 2400));
		listaDeProdutos.add(new Produto("bbb", 2500));
		listaDeProdutos.add(new Produto("aaa", 1900));

		// *****************************************************************************
		// Se uma expressão Lambda tem a forma: (args) -> Class.staticMethod(args)     *
		// Então, utilizando method reference fica assim: Class::staticMethod          *                       *
		// *****************************************************************************

		// Collections.sort(listaDeProdutos, (e1, e2) -> Principal.compararPorNome(e1, e2));

		Collections.sort(listaDeProdutos, Principal::compararPorNome);

		// listaDeProdutos.stream()
		//		.map(p -> p.getNome())
		//		.forEach(produto -> System.out.println(produto));

		listaDeProdutos.stream()
				.map(p -> p.getNome())
				.forEach(System.out::println);

		System.out.println("\n");

		// *****************************************************************************
		// Se uma expressão Lambda tem a forma: (args) -> obj.instanceMethod(args)     *
		// Então, utilizando method reference fica assim: obj::instanceMethod          *                  *
		// *****************************************************************************

		ProvedorDeComparacao comparador = new ProvedorDeComparacao();

		// Collections.sort(listaDeProdutos, (e1, e2) -> comparador.compararPorNome(e1, e2));

		Collections.sort(listaDeProdutos, comparador::compararPorNome);

		listaDeProdutos.stream()
				.map(p -> p.getNome())
				.forEach(System.out::println);

		System.out.println("\n");

		// ********************************************************************************
		// Se uma expressão Lambda tem a forma: (obj1, obj2) -> obj1.instanceMethod(obj2) *
		// Então, utilizando method reference fica assim: ObjectType::instanceMethod      *                         *
		// ********************************************************************************

		List<String> nomesDeProdutos = new ArrayList<>();

		nomesDeProdutos.add("ccc");
		nomesDeProdutos.add("bbb");
		nomesDeProdutos.add("aaa");

		// Collections.sort(nomesDeProdutos, (n1, n2) -> n1.compareToIgnoreCase(n2));

		Collections.sort(nomesDeProdutos, String::compareToIgnoreCase);

		nomesDeProdutos.stream().forEach(System.out::println);

		System.out.println("\n");
	}
}