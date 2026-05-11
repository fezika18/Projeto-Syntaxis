package projeto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoService {
	
	private List<Produto> listaProdutos = new ArrayList<>();
	
	public boolean adicionarProduto(String nome, int quantidade) {

		// Verifica se existe um produto com o mesmo nome
		for (Produto p : listaProdutos) {
			//equalsIgnoreCase compara ignorando letras maiúsculas e minúsculas.
			if (p.getNome().equalsIgnoreCase(nome)) {
				return false;
			}
		}

		Produto novo = new Produto(nome, quantidade);
		listaProdutos.add(novo);
		return true;
	}

	// Remove um produto da lista usando o índice selecionado na tabela
	public void removerProduto(int indice) {
		if (indice >= 0 && indice < listaProdutos.size()) {
			listaProdutos.remove(indice);
		}
	}

	public List<Produto> listarProdutos() {
		return listaProdutos;
	}
	
	// Busca um produto pelo índice da tabela
	public Produto buscarProduto(int indice) {
		if (indice >= 0 && indice < listaProdutos.size()) {
			return listaProdutos.get(indice);
		}
		return null;
	}
	
	// Adiciona uma quantidade ao estoque de um produto
	public void adicionarQuantidade(int indice, int quantidade) {
		if (indice >= 0 && indice < listaProdutos.size()) {
			Produto p = listaProdutos.get(indice);
			p.setQuantidade(p.getQuantidade() + quantidade);

			Movimentacao mov = new Movimentacao(p.getNome(), "Entrada", quantidade);
			System.out.println(mov.mostrar());
		}
	}
	
	// Retira uma quantidade do estoque de um produto
	public boolean retirarQuantidade(int indice, int quantidade) {
		if (indice >= 0 && indice < listaProdutos.size()) {
			Produto p = listaProdutos.get(indice);

			// Verifica se existe quantidade suficiente no estoque
			if (p.getQuantidade() >= quantidade) {
				p.setQuantidade(p.getQuantidade() - quantidade);

				Movimentacao mov = new Movimentacao(p.getNome(), "Saida", quantidade);
				System.out.println(mov.mostrar());

				return true;
			}
		}

		return false;
	}
	
	public boolean alterarNomeProduto(int indice, String novoNome) {
		
		// Verifica se já existe um produto com o mesmo nome
		for (Produto p : listaProdutos) {
			if (p.getNome().equalsIgnoreCase(novoNome)) {
				return false;
			}
		}
		
		// Verifica se o índice é válido
		if (indice >= 0 && indice < listaProdutos.size()) {

			Produto p = listaProdutos.get(indice);

			p.nome = novoNome;

			return true;
		}
		
		return false;
	}
}