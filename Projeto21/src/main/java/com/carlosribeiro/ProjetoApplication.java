package com.carlosribeiro;

import com.carlosribeiro.dao.LanceDAO;
import com.carlosribeiro.dao.ProdutoDAO;
import com.carlosribeiro.model.Lance;
import com.carlosribeiro.model.Produto;
import com.carlosribeiro.util.FabricaDeDaos;
import corejava.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.Map;

@SpringBootApplication
public class ProjetoApplication implements CommandLineRunner {
	@Autowired
	PrincipalProduto principalProduto;
	@Autowired
	PrincipalLance principalLance;

	@Autowired
	ProdutoDAO produtoDAO;
	@Autowired
	LanceDAO lanceDAO;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		this.recuperarDados();

		boolean continua = true;
		while (continua) {
			System.out.println('\n' + "========================================================");
			System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Tratar Produtos");
			System.out.println("2. Tratar Lances");
			System.out.println("3. Sair");

			int opcao = Console.readInt('\n' + "Digite um número entre 1 e 3:");

			System.out.println();

			switch (opcao) {
				case 1 -> {
					principalProduto.principal();
				}
				case 2 -> {
					principalLance.principal();
				}
				case 3 -> {
					continua = false;
					salvarDados();
				}

				default -> System.out.println('\n' + "Opção inválida!");
			}
		}
	}
	private void recuperarDados() {
		try {
			FileInputStream fis = new FileInputStream(new File("meusObjetos.txt"));
			ObjectInputStream ois = new ObjectInputStream(fis);
			Map<Integer, Produto> mapDeProdutos = (Map<Integer, Produto>) ois.readObject();
			produtoDAO.setMap(mapDeProdutos);
			Integer contadorDeProdutos = (Integer) ois.readObject();
			produtoDAO.setContador(contadorDeProdutos);
			Map<Integer, Lance> mapDeLances = (Map<Integer, Lance>) ois.readObject();
			lanceDAO.setMap(mapDeLances);
			Integer contadorDeLances = (Integer) ois.readObject();
			lanceDAO.setContador(contadorDeLances);
		} catch (FileNotFoundException e) {
			System.out.println("O arquivo meusObjetos.txt foi criado.");
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

	}

	private void salvarDados() {
		Map<Integer, Produto> mapDeProdutos = produtoDAO.getMap();
		Map<Integer, Lance> mapDeLances = lanceDAO.getMap();
		Integer contadorDeProdutos = produtoDAO.getContador();
		Integer contadorDeLances = lanceDAO.getContador();
		try {
			FileOutputStream fos = new FileOutputStream(new File("meusObjetos.txt"));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(mapDeProdutos);
			oos.writeObject(contadorDeProdutos);
			oos.writeObject(mapDeLances);
			oos.writeObject(contadorDeLances);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
