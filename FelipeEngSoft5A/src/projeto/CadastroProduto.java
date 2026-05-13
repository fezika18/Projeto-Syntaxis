package projeto;

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
import java.awt.Color;
import javax.swing.border.LineBorder;

public class CadastroProduto extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JTextField txtNome;

	private JTextField txtQtd;

	private JButton btnCadastrar;
	private JButton btnCancelar;

	private Opcoes opcoes;

	public CadastroProduto(Opcoes opcoes) {
		this.setOpcoes(opcoes);

		setIconImage(Toolkit.getDefaultToolkit().getImage(CadastroProduto.class.getResource("/imagens/syntaxis_150x100.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 420, 198);

		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNome = new JLabel("Nome do Produto:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(10, 31, 121, 14);
		contentPane.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(180, 30, 214, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblQtd = new JLabel("Quantidade do Produto:");
		lblQtd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQtd.setBounds(10, 67, 160, 14);
		contentPane.add(lblQtd);

		txtQtd = new JTextField();
		txtQtd.setColumns(10);
		txtQtd.setBounds(180, 66, 214, 20);
		contentPane.add(txtQtd);

		btnCadastrar = new JButton("➕ Cadastrar");
		btnCadastrar.setForeground(Color.WHITE);
		btnCadastrar.setBackground(new Color(47, 111, 237));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Pega os dados digitados pelo usuário, sem espaços.
				String nome = txtNome.getText().trim();
				String qtdTexto = txtQtd.getText().trim();

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
					JOptionPane.showMessageDialog(null, "Digite apenas números na quantidade!");
				}
			}
		});
		btnCadastrar.setBounds(236, 111, 115, 37);
		contentPane.add(btnCadastrar);

		btnCancelar = new JButton("⌫ Cancelar");
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.setForeground(new Color(255, 0, 0));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(64, 111, 115, 37);
		contentPane.add(btnCancelar);
	}

	public Opcoes getOpcoes() {
		return opcoes;
	}

	public void setOpcoes(Opcoes opcoes) {
		this.opcoes = opcoes;
	}
}