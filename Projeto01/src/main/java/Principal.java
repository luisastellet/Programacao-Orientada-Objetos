import corejava.Console;

public class Principal {
    public static void main(String[] args) {
        System.out.println("Olá!");
        String nome = Console.readLine("Informe o nome: ");
        double salario = Console.readDouble("Informe o salário: ");
        Empregado e1 = new Empregado(nome, salario);

        Empregado e2 = new Empregado("Silvia", 6000);

        System.out.println(e1.getNome());
        e1.setNome("Julio");
        System.out.println(Empregado.getTelefone());
    }
}
