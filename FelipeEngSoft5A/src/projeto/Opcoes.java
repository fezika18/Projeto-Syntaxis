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

public class Opcoes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel modeloTabela;
	private ProdutoService service = new ProdutoService();
	private JTextField txtBuscar;

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
	
	private void buscarNaTabela(String nome) {
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

	public Opcoes() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\visit0051\\eclipse-workspace\\FelipeEngSoft5A\\src\\Mídia.jpg"));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 517, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

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

		JButton btnCadastrar = new JButton("Cadastrar Produto");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroProduto cadastro = new CadastroProduto(Opcoes.this);
				cadastro.setVisible(true);
			}
		});
		btnCadastrar.setBounds(10, 373, 147, 23);
		contentPane.add(btnCadastrar);

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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 481, 320);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		modeloTabela = new DefaultTableModel();
		table.setModel(modeloTabela);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(10, 11, 382, 20);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nome = txtBuscar.getText().trim();

				if (nome.isEmpty()) {
					atualizarTabela();
					return;
				}

				buscarNaTabela(nome);
			}
		});
		btnBuscar.setBounds(402, 10, 89, 23);
		contentPane.add(btnBuscar);

		modeloTabela.addColumn("Produto");
		modeloTabela.addColumn("Quantidade");
		table.getColumnModel().getColumn(0).setPreferredWidth(350); // Produto (maior)
		table.getColumnModel().getColumn(1).setPreferredWidth(80);  // Quantidade (menor)
	}
}