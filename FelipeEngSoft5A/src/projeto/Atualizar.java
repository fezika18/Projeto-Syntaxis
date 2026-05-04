package projeto;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

// Classe responsável pela tela de atualização do estoque.
// Nesta tela o usuário pode adicionar ou retirar quantidade de um produto.
public class Atualizar extends JFrame {

	private static final long serialVersionUID = 1L;

	// Painel principal da tela
	private JPanel contentPane;

	// Campo onde o usuário digita a quantidade que será adicionada ou retirada
	private JTextField textField;

	// Referência da tela principal, usada para atualizar a tabela
	private Opcoes opcoes;

	// Índice do produto selecionado na tabela
	private int indice;

	// Construtor da tela Atualizar
	public Atualizar(Opcoes opcoes, int indice) {
		this.setOpcoes(opcoes);
		this.setIndice(indice);

		// Configurações principais da janela
		setIconImage(Toolkit.getDefaultToolkit().getImage(Atualizar.class.getResource("/projeto/syntaxis_150x100.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 423, 231);

		// Criação do painel principal
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Texto indicando o nome do produto
		JLabel lblNome = new JLabel("Nome do Produto:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(10, 30, 121, 14);
		contentPane.add(lblNome);
		
		// Texto indicando onde digitar a quantidade
		JLabel lblQtd = new JLabel("Quantidade:");
		lblQtd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQtd.setBounds(10, 99, 160, 14);
		contentPane.add(lblQtd);
		
		// Campo para digitar a quantidade
		textField = new JTextField();
		textField.setBounds(180, 98, 51, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		// Label que mostra o nome do produto selecionado
		JLabel lblProduto = new JLabel("");
		lblProduto.setBounds(180, 32, 193, 14);
		contentPane.add(lblProduto);

		// Busca o produto selecionado e mostra o nome na tela
		Produto p = opcoes.buscarProduto(indice);
		if (p != null) {
			lblProduto.setText(p.getNome());
		}
		
		// Botão para cancelar a operação e fechar a tela
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(20, 144, 93, 37);
		contentPane.add(btnCancelar);
		
		// Botão para adicionar quantidade ao estoque
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Pega o valor digitado no campo de quantidade
				String qtdTexto = textField.getText().trim();

				// Verifica se o campo está vazio
				if (qtdTexto.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite a quantidade!");
					return;
				}

				try {
					// Converte o texto digitado para número inteiro
					int quantidade = Integer.parseInt(qtdTexto);

					// Impede quantidade menor ou igual a zero
					if (quantidade <= 0) {
						JOptionPane.showMessageDialog(null, "Digite uma quantidade maior que zero!");
						return;
					}

					// Chama a tela principal para adicionar ao estoque
					opcoes.adicionarEstoque(indice, quantidade);
					dispose();

				} catch (NumberFormatException erro) {
					// Mensagem exibida caso o usuário digite letras no campo
					JOptionPane.showMessageDialog(null, "Digite apenas números!");
				}
			}
		});
		btnAdicionar.setBounds(150, 144, 93, 37);
		contentPane.add(btnAdicionar);
		
		// Botão para retirar quantidade do estoque
		JButton btnRetirar = new JButton("Retirar");
		btnRetirar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Pega o valor digitado no campo de quantidade
				String qtdTexto = textField.getText().trim();

				// Verifica se o campo está vazio
				if (qtdTexto.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite a quantidade!");
					return;
				}

				try {
					// Converte o texto digitado para número inteiro
					int quantidade = Integer.parseInt(qtdTexto);

					// Impede quantidade menor ou igual a zero
					if (quantidade <= 0) {
						JOptionPane.showMessageDialog(null, "Digite uma quantidade maior que zero!");
						return;
					}

					// Tenta retirar a quantidade do estoque
					boolean retirou = opcoes.retirarEstoque(indice, quantidade);

					// Caso não tenha estoque suficiente, mostra mensagem de erro
					if (!retirou) {
						JOptionPane.showMessageDialog(null, "Estoque insuficiente!");
						return;
					}

					dispose();

				} catch (NumberFormatException erro) {
					// Mensagem exibida caso o usuário digite letras no campo
					JOptionPane.showMessageDialog(null, "Digite apenas números!");
				}
			}
		});
		btnRetirar.setBounds(280, 144, 93, 37);
		contentPane.add(btnRetirar);
		
		// Botão responsável por alterar o nome do produto
		JButton btnAlterarNome = new JButton("Alterar Nome");
		btnAlterarNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Abre uma caixa para o usuário digitar o novo nome
				String novoNome = JOptionPane.showInputDialog("Digite o novo nome:");

				// Verifica se o usuário cancelou a operação
				if (novoNome == null) {
					return;
				}

				// Remove espaços extras do texto digitado
				novoNome = novoNome.trim();

				// Verifica se o campo ficou vazio
				if (novoNome.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite um nome válido!");
					return;
				}

				// Chama a tela principal para alterar o nome do produto
				boolean alterou = opcoes.alterarNomeProduto(indice, novoNome);

				// Verifica se o nome foi alterado com sucesso
				if (alterou) {

					// Atualiza o nome exibido na tela
					lblProduto.setText(novoNome);

					JOptionPane.showMessageDialog(null, "Nome alterado com sucesso!");
				} else {

					// Mensagem exibida caso já exista um produto com o mesmo nome
					JOptionPane.showMessageDialog(null, "Já existe um produto com esse nome!");
				}
			}
		});
		btnAlterarNome.setBounds(10, 55, 110, 23);
		contentPane.add(btnAlterarNome);
		
	}

	// Retorna a tela principal
	public Opcoes getOpcoes() {
		return opcoes;
	}

	// Define a tela principal
	public void setOpcoes(Opcoes opcoes) {
		this.opcoes = opcoes;
	}

	// Retorna o índice do produto selecionado
	public int getIndice() {
		return indice;
	}

	// Define o índice do produto selecionado
	public void setIndice(int indice) {
		this.indice = indice;
	}
}