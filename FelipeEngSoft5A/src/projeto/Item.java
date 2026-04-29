package projeto;

// Classe base do sistema.
// Representa um item genérico que possui apenas um nome.
// É usada para demonstrar herança no projeto.
public class Item {

    // Nome do item (pode ser produto ou movimentação)
    public String nome;

    // Método que retorna uma descrição simples do item
    public String mostrar() {
        return "Nome: " + nome;
    }
}