package projeto;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

// Classe responsável pela tela de cadastro de produtos.
// Nela o usuário informa o nome e a quantidade inicial do produto.
public class CadastroProduto extends JFrame {

	private static final long serialVersionUID = 1L;

	// Painel principal da tela
	private JPanel contentPane;

	// Campo para digitar o nome do produto
	private JTextField textField;

	// Campo para digitar a quantidade do produto
	private JTextField textField_1;

	// Botões da tela
	private JButton btnCadastrar;
	private JButton btnCancelar;

	// Referência da tela principal, usada para enviar o produto cadastrado
	private Opcoes opcoes;

	// Método principal usado para testar esta tela separadamente
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroProduto frame = new CadastroProduto(new Opcoes());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Construtor da tela de cadastro
	public CadastroProduto(Opcoes opcoes) {
		this.setOpcoes(opcoes);

		// Configurações principais da janela
		setIconImage(Toolkit.getDefaultToolkit().getImage(CadastroProduto.class.getResource("/projeto/syntaxis_150x100.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 420, 198);

		// Criação do painel principal
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Texto indicando o campo do nome
		JLabel lblNome = new JLabel("Nome do Produto:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(10, 31, 121, 14);
		contentPane.add(lblNome);

		// Campo onde o usuário digita o nome do produto
		textField = new JTextField();
		textField.setBounds(180, 30, 214, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		// Texto indicando o campo da quantidade
		JLabel lblQtd = new JLabel("Quantidade do Produto:");
		lblQtd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQtd.setBounds(10, 67, 160, 14);
		contentPane.add(lblQtd);

		// Campo onde o usuário digita a quantidade inicial do produto
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(180, 66, 214, 20);
		contentPane.add(textField_1);

		// Botão responsável por cadastrar o produto
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Pega os dados digitados pelo usuário
				String nome = textField.getText().trim();
				String qtdTexto = textField_1.getText().trim();

				// Verifica se o nome está vazio
				if (nome.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite o nome do produto!");
					return;
				}

				// Verifica se a quantidade está vazia
				if (qtdTexto.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite a quantidade!");
					return;
				}

				try {
					// Converte a quantidade de texto para número inteiro
					int quantidade = Integer.parseInt(qtdTexto);

					// Impede o cadastro de quantidade menor ou igual a zero
					if (quantidade <= 0) {
						JOptionPane.showMessageDialog(null, "Digite uma quantidade maior que zero!");
						return;
					}

					// Envia os dados para a tela principal cadastrar o produto
					opcoes.cadastrarProduto(nome, quantidade);
					dispose();

				} catch (NumberFormatException erro) {
					// Mensagem exibida caso o usuário digite letras na quantidade
					JOptionPane.showMessageDialog(null, "Digite apenas números na quantidade!");
				}
			}
		});
		btnCadastrar.setBounds(250, 111, 93, 37);
		contentPane.add(btnCadastrar);

		// Botão para cancelar o cadastro e fechar a tela
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(64, 111, 93, 37);
		contentPane.add(btnCancelar);
	}

	// Retorna a tela principal
	public Opcoes getOpcoes() {
		return opcoes;
	}

	// Define a tela principal
	public void setOpcoes(Opcoes opcoes) {
		this.opcoes = opcoes;
	}
}