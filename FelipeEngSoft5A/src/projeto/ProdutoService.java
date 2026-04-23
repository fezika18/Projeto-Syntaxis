package projeto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoService {
	
	private List<Produto> listaProdutos = new ArrayList<>();
	private int quantidade;

	public boolean adicionarProduto(String nome, int quantidade) {

		for (Produto p : listaProdutos) {
			if (p.getNome().equalsIgnoreCase(nome)) {
				return false;
			}
		}

		Produto novo = new Produto(nome, quantidade);
		listaProdutos.add(novo);
		return true;
	}

	public void removerProduto(int indice) {
		if (indice >= 0 && indice < listaProdutos.size()) {
			listaProdutos.remove(indice);
		}
	}

	public List<Produto> listarProdutos() {
		return listaProdutos;
	}
	
	public Produto buscarProduto(int indice) {
		if (indice >= 0 && indice < listaProdutos.size()) {
			return listaProdutos.get(indice);
		}
		return null;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public void adicionarQuantidade(int indice, int quantidade) {
		if (indice >= 0 && indice < listaProdutos.size()) {
			Produto p = listaProdutos.get(indice);
			p.setQuantidade(p.getQuantidade() + quantidade);

			Movimentacao mov = new Movimentacao(p.getNome(), "Entrada", quantidade);
			System.out.println(mov.mostrar());
		}
	}
	
	public boolean retirarQuantidade(int indice, int quantidade) {
		if (indice >= 0 && indice < listaProdutos.size()) {
			Produto p = listaProdutos.get(indice);

			if (p.getQuantidade() >= quantidade) {
				p.setQuantidade(p.getQuantidade() - quantidade);

				Movimentacao mov = new Movimentacao(p.getNome(), "Saida", quantidade);
				System.out.println(mov.mostrar());

				return true;
			}
		}

		return false;
	}
}