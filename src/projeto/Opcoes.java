package projeto;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class Opcoes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JTable tblProdutos;

	private DefaultTableModel modeloTabela;

	private ProdutoService service = new ProdutoService();

	private JTextField txtBuscar;

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

	public void cadastrarProduto(String nome, int quantidade) {
		boolean adicionou = service.adicionarProduto(nome, quantidade);

		if (adicionou) {
			atualizarTabela();
		} else {
			JOptionPane.showMessageDialog(null, "Este produto já está cadastrado!");
		}
	}

	public void removerProduto(int indice) {
		service.removerProduto(indice);
		atualizarTabela();
	}

	public Produto buscarProduto(int indice) {
		return service.buscarProduto(indice);
	}

	public void adicionarEstoque(int indice, int quantidade) {
		service.adicionarQuantidade(indice, quantidade);
		atualizarTabela();
	}

	public boolean retirarEstoque(int indice, int quantidade) {
		boolean retirou = service.retirarQuantidade(indice, quantidade);
		atualizarTabela();
		return retirou;
	}
	
	public boolean alterarNomeProduto(int indice, String novoNome) {

		boolean alterou = service.alterarNomeProduto(indice, novoNome);

		atualizarTabela();

		return alterou;
	}
	
	// Retorna o índice real do produto pelo nome
	public int buscarIndicePorNome(String nome) {
		return service.buscarIndicePorNome(nome);
	}

	public Opcoes() {
		setResizable(false);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Opcoes.class.getResource("/imagens/syntaxis_150x100.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 689, 544);

		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnSair = new JButton("↩️ Sair");
		btnSair.setForeground(Color.WHITE);
		btnSair.setBackground(new Color(47, 111, 237));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				dispose();
				login.setVisible(true);
			}
		});
		btnSair.setBounds(301, 442, 93, 37);
		contentPane.add(btnSair);

		JButton btnCadastrar = new JButton("➕ Cadastrar Produto");
		btnCadastrar.setForeground(Color.WHITE);
		btnCadastrar.setBackground(new Color(47, 111, 237));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroProduto cadastro = new CadastroProduto(Opcoes.this);
				cadastro.setVisible(true);
			}
		});
		btnCadastrar.setBounds(10, 373, 162, 37);
		contentPane.add(btnCadastrar);

		JButton btnRemover = new JButton("⌫ Remover Produto");
		btnRemover.setForeground(Color.RED);
		btnRemover.setBackground(Color.WHITE);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = tblProdutos.getSelectedRow();

				if (linha == -1) {
					JOptionPane.showMessageDialog(null, "Selecione um produto para remover!");
					return;
				}

				// Pega o nome que aparece na linha selecionada da tabela
				String nomeProduto = tblProdutos.getValueAt(linha, 0).toString();

				// Busca o índice real do produto na lista original
				int indiceReal = buscarIndicePorNome(nomeProduto);

				Remover remover = new Remover(Opcoes.this, indiceReal);
				remover.setVisible(true);
			}
		});
		btnRemover.setBounds(508, 373, 155, 37);
		contentPane.add(btnRemover);

		JButton btnAtualizar = new JButton("⏳ Atualizar Status do Produto");
		btnAtualizar.setBackground(Color.WHITE);
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = tblProdutos.getSelectedRow();

				if (linha == -1) {
					JOptionPane.showMessageDialog(null, "Selecione um produto para atualizar!");
					return;
				}

				// Pega o nome que aparece na linha selecionada da tabela
				String nomeProduto = tblProdutos.getValueAt(linha, 0).toString();

				// Busca o índice real do produto na lista original
				int indiceReal = buscarIndicePorNome(nomeProduto);

				Atualizar atualizar = new Atualizar(Opcoes.this, indiceReal);
				atualizar.setVisible(true);
			}
		});
		btnAtualizar.setBounds(204, 373, 268, 37);
		contentPane.add(btnAtualizar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 653, 320);
		contentPane.add(scrollPane);

		tblProdutos = new JTable();
		scrollPane.setViewportView(tblProdutos);

		modeloTabela = new DefaultTableModel();
		tblProdutos.setModel(modeloTabela);

		txtBuscar = new JTextField();
		txtBuscar.setBackground(Color.WHITE);
		txtBuscar.setBounds(10, 11, 550, 20);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JButton btnBuscar = new JButton("🔍 Buscar");
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setBackground(new Color(47, 111, 237));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nome = txtBuscar.getText().trim();

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
		btnBuscar.setBounds(568, 10, 93, 23);
		contentPane.add(btnBuscar);

		modeloTabela.addColumn("Produto");
		modeloTabela.addColumn("Quantidade");
		
		cadastrarProduto("Mouse", 15);
		cadastrarProduto("Teclado", 8);
		cadastrarProduto("Monitor", 5);
		cadastrarProduto("Headset", 12);
		cadastrarProduto("Notebook", 3);
		cadastrarProduto("Gabinete", 4);
		cadastrarProduto("Fonte 500W", 6);
		cadastrarProduto("SSD 480GB", 10);
		cadastrarProduto("HD 1TB", 7);
		cadastrarProduto("Memoria RAM 8GB", 14);
		cadastrarProduto("Memoria RAM 16GB", 9);
		cadastrarProduto("Placa Mae", 5);
		cadastrarProduto("Processador Ryzen 5", 3);
		cadastrarProduto("Cooler", 11);
		cadastrarProduto("Pasta Termica", 18);
		cadastrarProduto("Cabo HDMI", 20);
		cadastrarProduto("Cabo VGA", 13);
		cadastrarProduto("Filtro de Linha", 6);
		cadastrarProduto("Adaptador USB", 9);
		cadastrarProduto("Switch de Rede", 4);
		cadastrarProduto("Roteador", 5);
		cadastrarProduto("Impressora", 2);
		cadastrarProduto("Toner", 7);
		cadastrarProduto("Papel A4", 30);
		cadastrarProduto("Etiqueta", 16);
		cadastrarProduto("Caneta Azul", 40);
		cadastrarProduto("Caneta Preta", 35);
		cadastrarProduto("Lapis", 28);
		cadastrarProduto("Borracha", 19);
		cadastrarProduto("Marca Texto", 12);
		cadastrarProduto("Caderno", 14);
		cadastrarProduto("Grampeador", 6);
		cadastrarProduto("Clips", 50);
		cadastrarProduto("Mousepad", 10);
		cadastrarProduto("Webcam", 7);
		cadastrarProduto("Microfone", 4);
		cadastrarProduto("Caixa de Som", 8);
		cadastrarProduto("Projetor", 2);
		cadastrarProduto("Extensao Eletrica", 11);
		cadastrarProduto("Bateria 9V", 17);
		cadastrarProduto("Pilhas AA", 25);
		cadastrarProduto("Pilhas AAA", 22);
		cadastrarProduto("Pendrive 32GB", 13);
		cadastrarProduto("Pendrive 64GB", 8);
		cadastrarProduto("Cartao SD", 6);
		cadastrarProduto("Leitor Biométrico", 3);
		cadastrarProduto("Camera de Segurança", 5);
		cadastrarProduto("Fechadura Eletronica", 2);
		cadastrarProduto("Telefone IP", 9);
		cadastrarProduto("Patch Cord", 21);
		cadastrarProduto("Conector RJ45", 60);
		cadastrarProduto("Organizador de Cabos", 18);
		cadastrarProduto("Estabilizador", 4);
		cadastrarProduto("Nobreak", 3);
		cadastrarProduto("Scanner", 2);
		cadastrarProduto("Suporte para Monitor", 7);
		cadastrarProduto("Mesa Digitalizadora", 1);
		cadastrarProduto("Tablet", 4);
		cadastrarProduto("Celular Corporativo", 5);
		cadastrarProduto("Carregador USB", 12);
		cadastrarProduto("Fone de Ouvido", 16);

		// Ajuste do tamanho das colunas
		tblProdutos.getColumnModel().getColumn(0).setPreferredWidth(350);
		tblProdutos.getColumnModel().getColumn(1).setPreferredWidth(80);
	}
	
}