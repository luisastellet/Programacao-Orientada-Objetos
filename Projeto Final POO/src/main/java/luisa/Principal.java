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
        PrincipalExecTrecho principalExecTrecho = new PrincipalExecTrecho();
        PrincipalExecVoo principalExecVoo = new PrincipalExecVoo();
        PrincipalPassagem principalPassagem = new PrincipalPassagem();
        PrincipalTrecho principalTrecho = new PrincipalTrecho();
        PrincipalVoo principalVoo = new PrincipalVoo();


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
                case 7 ->{
                    continua = false;
                    salvarDados();
                }
                default -> System.out.println('\n' + "Opção inválida!");
            }
        }
    }

    private static void recuperarDados() {
        try {
            //obtem os objetos das classes
            ClienteDAO clienteDAO = FabricaDeDaos.getDAO(ClienteDAO.class);
            ExecTrechoDAO execTrechoDAO = FabricaDeDaos.getDAO(ExecTrechoDAO.class);
            ExecVooDAO execVooDAO = FabricaDeDaos.getDAO(ExecVooDAO.class);
            PassagemDAO passagemDAO = FabricaDeDaos.getDAO(PassagemDAO.class);
            TrechoDAO trechoDAO = FabricaDeDaos.getDAO(TrechoDAO.class);
            VooDAO vooDAO = FabricaDeDaos.getDAO(VooDAO.class);

            //Pega o arquivo
            FileInputStream fis = new FileInputStream(new File("meusObjetos.txt"));
            ObjectInputStream ois = new ObjectInputStream(fis);

            //Atribui oque pegou dos arquivos para a variável obtida no começo:

            //Clientes
            Map<Integer, Cliente> mapDeClientes = (Map<Integer, Cliente>) ois.readObject();
            clienteDAO.setMap(mapDeClientes);
            Integer contadorDeClientes = (Integer) ois.readObject();
            clienteDAO.setContador(contadorDeClientes);

            //Execução Trecho
            Map<Integer, ExecTrecho> mapDeExecTrechos = (Map<Integer, ExecTrecho>) ois.readObject();
            execTrechoDAO.setMap(mapDeExecTrechos);
            Integer contadorDeExecTrecho = (Integer) ois.readObject();
            execTrechoDAO.setContador(contadorDeExecTrecho);

            //Execução Voo
            Map<Integer,ExecVoo> mapDeExecVoos = (Map<Integer, ExecVoo>) ois.readObject();
            execVooDAO.setMap(mapDeExecVoos);
            Integer contadorExecDeVoo = (Integer) ois.readObject();
            execVooDAO.setContador(contadorExecDeVoo);

            //Passagem
            Map<Integer, Passagem> mapDePassagens = (Map<Integer, Passagem>) ois.readObject();
            passagemDAO.setMap(mapDePassagens);
            Integer contadorDePassagem = (Integer) ois.readObject();
            passagemDAO.setContador(contadorDePassagem);

            //Trecho
            Map<Integer, Trecho> mapDeTrechos = (Map<Integer, Trecho>) ois.readObject();
            trechoDAO.setMap(mapDeTrechos);
            Integer contadorDeTrecho = (Integer) ois.readObject();
            trechoDAO.setContador(contadorDeTrecho);

            //Voo
            Map<Integer, Voo> mapDeVoos = (Map<Integer, Voo>) ois.readObject();
            vooDAO.setMap(mapDeVoos);
            Integer contadorDeVoo = (Integer) ois.readObject();
            vooDAO.setContador(contadorDeVoo);

        } catch (FileNotFoundException e) {
            System.out.println("O arquivo meusObjetos.txt foi criado.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static void salvarDados() {
        //Pega as classes
        ClienteDAO clienteDAO = FabricaDeDaos.getDAO(ClienteDAO.class);
        ExecTrechoDAO execTrechoDAO = FabricaDeDaos.getDAO(ExecTrechoDAO.class);
        ExecVooDAO execVooDAO = FabricaDeDaos.getDAO(ExecVooDAO.class);
        PassagemDAO passagemDAO = FabricaDeDaos.getDAO(PassagemDAO.class);
        TrechoDAO trechoDAO = FabricaDeDaos.getDAO(TrechoDAO.class);
        VooDAO vooDAO = FabricaDeDaos.getDAO(VooDAO.class);

        //Pega o map de cada uma delas
        Map<Integer, Cliente> mapDeClientes = clienteDAO.getMap();
        Map<Integer, ExecTrecho> mapDeExecTrechos = execTrechoDAO.getMap();
        Map<Integer, ExecVoo> mapDeExecVoos = execVooDAO.getMap();
        Map<Integer,Passagem> mapDePassagens = passagemDAO.getMap();
        Map<Integer, Trecho> mapDeTrechos = trechoDAO.getMap();
        Map<Integer, Voo> mapDeVoos = vooDAO.getMap();

        Integer contadorDeCliente = clienteDAO.getContador();
        Integer contadorDeExecTrecho = execTrechoDAO.getContador();
        Integer contadorDeExecVoo = execVooDAO.getContador();
        Integer contadorDePassagem = passagemDAO.getContador();
        Integer contadorDeTrecho = trechoDAO.getContador();
        Integer contadorDeVoo = vooDAO.getContador();



        try {
            //Cria os objetos relacionados ao arquivo
            FileOutputStream fos = new FileOutputStream(new File("meusObjetos.txt"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            //Escreve os maps nos arquivos
            oos.writeObject(mapDeClientes);
            oos.writeObject(contadorDeCliente);
            oos.writeObject(mapDeExecTrechos);
            oos.writeObject(contadorDeExecTrecho);
            oos.writeObject(mapDeExecVoos);
            oos.writeObject(contadorDeExecVoo);
            oos.writeObject(mapDePassagens);
            oos.writeObject(contadorDePassagem);
            oos.writeObject(mapDeTrechos);
            oos.writeObject(contadorDeTrecho);
            oos.writeObject(mapDeVoos);
            oos.writeObject(contadorDeVoo);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}









