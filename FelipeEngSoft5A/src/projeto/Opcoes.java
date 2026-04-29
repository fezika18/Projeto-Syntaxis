package projeto;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

// Classe principal do sistema.
// Nela o usuário consegue visualizar os produtos cadastrados
// e acessar as funcionalidades de cadastro, remoção, busca e atualização de estoque.
public class Opcoes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	// Tabela que exibe os produtos cadastrados
	private JTable table;

	// Modelo da tabela, responsável por manipular as linhas
	private DefaultTableModel modeloTabela;

	// Classe responsável pelas operações com os produtos
	private ProdutoService service = new ProdutoService();

	// Campo de texto utilizado para buscar produtos pelo nome
	private JTextField textField;

	// Método responsável por atualizar a tabela com os produtos cadastrados
	private void atualizarTabela() {
		modeloTabela.setRowCount(0);

		for (Produto p : service.listarProdutos()) {
			modeloTabela.addRow(new Object[] {
				p.getNome(),
				p.getQuantidade()
			});
		}
	}

	// Método para cadastrar um novo produto
	public void cadastrarProduto(String nome, int quantidade) {
		boolean adicionou = service.adicionarProduto(nome, quantidade);

		if (adicionou) {
			atualizarTabela();
		} else {
			JOptionPane.showMessageDialog(null, "Este produto já está cadastrado!");
		}
	}

	// Remove um produto selecionado na tabela
	public void removerProduto(int indice) {
		service.removerProduto(indice);
		atualizarTabela();
	}

	// Retorna um produto específico da lista
	public Produto buscarProduto(int indice) {
		return service.buscarProduto(indice);
	}

	// Adiciona quantidade ao estoque de um produto
	public void adicionarEstoque(int indice, int quantidade) {
		service.adicionarQuantidade(indice, quantidade);
		atualizarTabela();
	}

	// Retira quantidade do estoque de um produto
	public boolean retirarEstoque(int indice, int quantidade) {
		boolean retirou = service.retirarQuantidade(indice, quantidade);
		atualizarTabela();
		return retirou;
	}

	// Método principal que inicia a aplicação
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Opcoes frame = new Opcoes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Construtor da tela principal
	public Opcoes() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Opcoes.class.getResource("/projeto/syntaxis_150x100.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 517, 494);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Botão que retorna para a tela de login
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				dispose();
				login.setVisible(true);
			}
		});
		btnSair.setBounds(200, 407, 93, 37);
		contentPane.add(btnSair);

		// Botão que abre a tela de cadastro de produto
		JButton btnCadastrar = new JButton("Cadastrar Produto");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroProduto cadastro = new CadastroProduto(Opcoes.this);
				cadastro.setVisible(true);
			}
		});
		btnCadastrar.setBounds(10, 373, 147, 23);
		contentPane.add(btnCadastrar);

		// Botão que remove o produto selecionado
		JButton btnRemover = new JButton("Remover Produto");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = table.getSelectedRow();

				if (linha == -1) {
					JOptionPane.showMessageDialog(null, "Selecione um produto para remover!");
					return;
				}

				Remover remover = new Remover(Opcoes.this, linha);
				remover.setVisible(true);
			}
		});
		btnRemover.setBounds(355, 373, 136, 23);
		contentPane.add(btnRemover);

		// Botão que abre a tela para atualizar o estoque
		JButton btnAtualizar = new JButton("Atualizar Status do Produto");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = table.getSelectedRow();

				if (linha == -1) {
					JOptionPane.showMessageDialog(null, "Selecione um produto para atualizar!");
					return;
				}

				Atualizar atualizar = new Atualizar(Opcoes.this, linha);
				atualizar.setVisible(true);
			}
		});
		btnAtualizar.setBounds(159, 373, 194, 23);
		contentPane.add(btnAtualizar);

		// Área com scroll onde fica a tabela
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 481, 320);
		contentPane.add(scrollPane);

		// Criação da tabela
		table = new JTable();
		scrollPane.setViewportView(table);

		// Inicialização do modelo da tabela
		modeloTabela = new DefaultTableModel();
		table.setModel(modeloTabela);

		// Campo de busca de produtos
		textField = new JTextField();
		textField.setBounds(10, 11, 382, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		// Botão que realiza a busca de produtos pelo nome
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nome = textField.getText().trim();

				if (nome.isEmpty()) {
					atualizarTabela();
					return;
				}

				modeloTabela.setRowCount(0);

				for (Produto p : service.listarProdutos()) {
					if (p.getNome().toLowerCase().contains(nome.toLowerCase())) {
						modeloTabela.addRow(new Object[] {
							p.getNome(),
							p.getQuantidade()
						});
					}
				}
			}
		});
		btnBuscar.setBounds(402, 10, 89, 23);
		contentPane.add(btnBuscar);

		// Definição das colunas da tabela
		modeloTabela.addColumn("Produto");
		modeloTabela.addColumn("Quantidade");

		// Ajuste do tamanho das colunas
		table.getColumnModel().getColumn(0).setPreferredWidth(350);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
	}
}