package projeto;

// Classe que representa uma movimentação de estoque.
// Pode ser uma entrada ou saída de produtos.
// Herda da classe Item para demonstrar herança no sistema.
public class Movimentacao extends Item {

    // Tipo da movimentação (Entrada ou Saída)
    private String tipo;

    // Quantidade movimentada
    private int quantidade;

    // Construtor que recebe o nome do produto, tipo e quantidade
    public Movimentacao(String nome, String tipo, int quantidade) {
        this.nome = nome; // herdado da classe Item
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

    // Método que retorna uma descrição da movimentação
    // Sobrescreve o método da classe Item (polimorfismo)
    public String mostrar() {
        return "Produto: " + nome + " - " + tipo + ": " + quantidade;
    }
}