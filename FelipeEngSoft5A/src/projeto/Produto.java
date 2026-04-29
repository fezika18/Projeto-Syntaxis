package projeto;

// Classe que representa um produto do sistema.
// Cada produto possui um nome e uma quantidade em estoque.
// Herda da classe Item para demonstrar herança.
public class Produto extends Item {

    // Quantidade disponível em estoque
    private int quantidade;

    // Construtor que recebe o nome e a quantidade inicial do produto
    public Produto(String nome, int quantidade) {
        this.nome = nome; // atributo herdado da classe Item
        this.quantidade = quantidade;
    }

    // Retorna o nome do produto
    public String getNome() {
        return nome;
    }

    // Retorna a quantidade do produto em estoque
    public int getQuantidade() {
        return quantidade;
    }

    // Atualiza a quantidade do produto
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // Método que retorna uma descrição do produto
    // Sobrescreve o método da classe Item (polimorfismo)
    public String mostrar() {
        return "Produto: " + nome + " - Qtd: " + quantidade;
    }
}