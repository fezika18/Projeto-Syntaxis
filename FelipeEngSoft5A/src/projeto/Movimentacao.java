package projeto;

public class Movimentacao extends Item {

    private String tipo;
    private int quantidade;

    public Movimentacao(String nome, String tipo, int quantidade) {
        this.nome = nome;
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

    public String mostrar() {
        return "Produto: " + nome + " - " + tipo + ": " + quantidade;
    }
}