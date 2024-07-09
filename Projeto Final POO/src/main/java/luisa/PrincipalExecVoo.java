package luisa;

import corejava.Console;
import luisa.exception.DataHoraInvalidaException;
import luisa.exception.EntidadeNaoEncontradaException;
import luisa.exception.JaAconteceuException;
import luisa.model.ExecTrecho;
import luisa.model.ExecVoo;
import luisa.model.Voo;
import luisa.service.ExecVooService;
import luisa.service.VooService;

import javax.xml.crypto.Data;
import java.util.List;

public class PrincipalExecVoo {

    private final ExecVooService execVooService = new ExecVooService();
    private final VooService vooService = new VooService();


    public void principal() throws DataHoraInvalidaException {
        String dataHoraInicial;
        String dataHoraFinal;
        ExecVoo umaExecVoo;
        Voo umVoo;

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar Execução de Voo");
            System.out.println("2. Remover Execução de Voo");
            System.out.println("3. Listar todas as execuções de Voo");
            System.out.println("4. Listar o voo de uma execução de voo");
            System.out.println("5. Listar as execuções de trecho de uma execução de voo");
            System.out.println("6. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 6:");

            System.out.println();

            switch (opcao) {
                case 1 -> {
                    dataHoraInicial = Console.readLine("Informe a data e hora inicial da execução do voo (DD/MM/AAAA HH:MM:SS): ");
                    dataHoraFinal = Console.readLine("Informe a data e hora final da execução do voo (DD/MM/AAAA HH:MM:SS): ");
                    int idVoo = Console.readInt("Informe o número do voo: ");
                    try {
                        umVoo = vooService.recuperarVooPorId(idVoo);
                        umaExecVoo = new ExecVoo(dataHoraInicial, dataHoraFinal, umVoo);
                        execVooService.incluir(umaExecVoo);
                        System.out.println("\nExecução de voo de número " + umaExecVoo.getId() + " cadastrada com sucesso!");
                    }
                    catch(EntidadeNaoEncontradaException | DataHoraInvalidaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                }
                case 2 -> {
                    int id = Console.readInt(
                            "Informe o número da execução de voo que você deseja remover: ");

                    try {
                        execVooService.remover(id);
                        System.out.println('\n' + "Execução de voo removida com sucesso!");
                    } catch (EntidadeNaoEncontradaException | JaAconteceuException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 3 -> {    // Listar
                    List<ExecVoo> execucoesDeVoo = execVooService.recuperarExecucoesDeVoo();
                    for (ExecVoo execucoes : execucoesDeVoo) {
                        System.out.println(execucoes);
                    }
                }
                case 4 -> {
                    int id = Console.readInt("Qual o id da execução de voo? ");
                    try {
                        umaExecVoo = execVooService.recuperarExecucaoDeVooPorId(id);
                    }catch(EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println(umaExecVoo.getVoo());
                }
                case 5 -> {
                    int id = Console.readInt("Qual o id da execução de voo? ");
                    try {
                        umaExecVoo = execVooService.recuperarExecucaoDeVooPorId(id);
                    }catch(EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    List<ExecTrecho> execucoes = umaExecVoo.getExecucoesTrechos();
                    for (ExecTrecho exec : execucoes) {
                        System.out.println(exec);
                    }
                }
                case 6 -> continua = false;

                default -> System.out.println('\n' + "Opção inválida!");
            }
        }
    }
}
