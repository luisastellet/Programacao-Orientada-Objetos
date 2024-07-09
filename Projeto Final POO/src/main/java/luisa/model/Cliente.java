package luisa.model;
import luisa.util.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Serializable{

    private int id;
    private String nome;
    private String cpf;
    private List<Passagem> passagens;

    public Cliente (String nome, String cpf)
    {	this.nome = nome;
        this.cpf = cpf;
        this.passagens = new ArrayList<>();
    }

    public String toString() {
        return "Id = " + id + "  Nome = " + nome + " Cpf = " + cpf;
    }

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setNome(String novoNome) {
        this.nome = novoNome;
    }

    public List<Passagem> getPassagens() {
        return passagens;
    }
}

