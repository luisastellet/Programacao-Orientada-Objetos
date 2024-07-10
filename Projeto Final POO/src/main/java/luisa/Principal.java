package luisa;

import luisa.dao.*;
import luisa.model.*;
import luisa.util.FabricaDeDaos;
import corejava.Console;

import java.io.*;
import java.util.Map;

public class Principal {
    public static void main(String[] args) {

        PrincipalCliente principalCliente = new PrincipalCliente();
        PrincipalPassagem principalPassagem = new PrincipalPassagem();
        PrincipalTrecho principalTrecho = new PrincipalTrecho();
        PrincipalVoo principalVoo = new PrincipalVoo();
        PrincipalExecTrecho principalExecTrecho = new PrincipalExecTrecho();
        PrincipalExecVoo principalExecVoo = new PrincipalExecVoo();

        recuperarDados();

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Tratar de Clientes");
            System.out.println("2. Tratar de Passagem");
            System.out.println("3. Tratar de Trecho");
            System.out.println("4. Tratar de Voo");
            System.out.println("5. Tratar de Execuções de Voo");
            System.out.println("6. Tratar de Execuções de Trecho");
            System.out.println("7. Sair");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 7:");

            System.out.println();

            switch (opcao) {
                case 1 -> {
                    principalCliente.principal();
                }
                case 2 -> {
                    principalPassagem.principal();
                }
                case 3 -> {
                    principalTrecho.principal();
                }
                case 4 -> {
                    principalVoo.principal();
                }
                case 5 -> {
                    principalExecVoo.principal();
                }
                case 6 -> {
                    principalExecTrecho.principal();
                }
                case 7 -> {
                    continua = false;
                    salvarDados();
                }
                default -> System.out.println('\n' + "Opção inválida!");
            }
        }
    }

    private static void recuperarDados() {
        try {
            ClienteDAO clienteDAO = FabricaDeDaos.getDAO(ClienteDAO.class);
            PassagemDAO passagemDAO = FabricaDeDaos.getDAO(PassagemDAO.class);
            TrechoDAO trechoDAO = FabricaDeDaos.getDAO(TrechoDAO.class);
            VooDAO vooDAO = FabricaDeDaos.getDAO(VooDAO.class);
            ExecVooDAO execVooDAO = FabricaDeDaos.getDAO(ExecVooDAO.class);
            ExecTrechoDAO execTrechoDAO = FabricaDeDaos.getDAO(ExecTrechoDAO.class);

            // fis é um objeto que tem o nome do arquivo de escrita
            FileInputStream fis = new FileInputStream(new File("meusObjetos.txt"));
            // ois é o objeto que vai ser responsável por ler o arquivo
            ObjectInputStream ois = new ObjectInputStream(fis);

            //preciso criar um contador de cada classe pra acompanhar a quantidade de elementos no map
            //se não fizer isso,
            Map<Integer, Cliente> mapDeClientes = (Map<Integer, Cliente>) ois.readObject();
            clienteDAO.setMap(mapDeClientes);
            Integer contadorDeClientes = (Integer) ois.readObject();
            clienteDAO.setContador(contadorDeClientes);

            Map<Integer, Passagem> mapDePassagens = (Map<Integer, Passagem>) ois.readObject();
            passagemDAO.setMap(mapDePassagens);
            Integer contadorDePassagens = (Integer) ois.readObject();
            passagemDAO.setContador(contadorDePassagens);

            Map<Integer, Trecho> mapDeTrechos = (Map<Integer, Trecho>) ois.readObject();
            trechoDAO.setMap(mapDeTrechos);
            Integer contadorDeTrechos = (Integer) ois.readObject();
            trechoDAO.setContador(contadorDeTrechos);

            Map<Integer, Voo> mapDeVoos = (Map<Integer, Voo>) ois.readObject();
            vooDAO.setMap(mapDeVoos);
            Integer contadorDeVoos = (Integer) ois.readObject();
            vooDAO.setContador(contadorDeVoos);

            Map<Integer, ExecVoo> mapDeExecVoos = (Map<Integer, ExecVoo>) ois.readObject();
            execVooDAO.setMap(mapDeExecVoos);
            Integer contadorDeExecVoos= (Integer) ois.readObject();
            execVooDAO.setContador(contadorDeExecVoos);

            Map<Integer, ExecTrecho> mapDeExecTrechos = (Map<Integer, ExecTrecho>) ois.readObject();
            execTrechoDAO.setMap(mapDeExecTrechos);
            Integer contadorDeExecTrechos = (Integer) ois.readObject();
            execTrechoDAO.setContador(contadorDeExecTrechos);


        } catch (FileNotFoundException e) {
            System.out.println("O arquivo meusObjetos.txt foi criado.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static void salvarDados() {
        ClienteDAO clienteDAO = FabricaDeDaos.getDAO(ClienteDAO.class);
        PassagemDAO passagemDAO = FabricaDeDaos.getDAO(PassagemDAO.class);
        TrechoDAO trechoDAO = FabricaDeDaos.getDAO(TrechoDAO.class);
        VooDAO vooDAO = FabricaDeDaos.getDAO(VooDAO.class);
        ExecTrechoDAO execTrechoDAO = FabricaDeDaos.getDAO(ExecTrechoDAO.class);
        ExecVooDAO execVooDAO = FabricaDeDaos.getDAO(ExecVooDAO.class);

        Map<Integer, Cliente> mapDeClientes = clienteDAO.getMap();
        Map<Integer, Passagem> mapDePassagens = passagemDAO.getMap();
        Map<Integer, Trecho> mapDeTrechos = trechoDAO.getMap();
        Map<Integer, Voo> mapDeVoos = vooDAO.getMap();
        Map<Integer, ExecTrecho> mapDeExecTrechos = execTrechoDAO.getMap();
        Map<Integer, ExecVoo> mapDeExecVoos = execVooDAO.getMap();

        Integer contadorDeClientes = clienteDAO.getContador();
        Integer contadorDePassagens = passagemDAO.getContador();
        Integer contadorDeTrechos = trechoDAO.getContador();
        Integer contadorDeVoos = vooDAO.getContador();
        Integer contadorDeExecTrechos = execTrechoDAO.getContador();
        Integer contadorDeExecVoos = execVooDAO.getContador();
        try {
            //objeto criado com o nome do arquivo de escrita
            FileOutputStream fos = new FileOutputStream(new File("meusObjetos.txt"));
            //objeto usado para escrever no arquivo
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(mapDeClientes);
            oos.writeObject(contadorDeClientes);

            oos.writeObject(mapDePassagens);
            oos.writeObject(contadorDePassagens);

            oos.writeObject(mapDeTrechos);
            oos.writeObject(contadorDeTrechos);

            oos.writeObject(mapDeVoos);
            oos.writeObject(contadorDeVoos);

            oos.writeObject(mapDeExecTrechos);
            oos.writeObject(contadorDeExecTrechos);

            oos.writeObject(mapDeExecVoos);
            oos.writeObject(contadorDeExecVoos);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}










