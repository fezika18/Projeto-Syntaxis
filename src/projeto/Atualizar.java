package projeto;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class Atualizar extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JTextField txtQtd;

	private Opcoes opcoes;

	private int indice;

	public Atualizar(Opcoes opcoes, int indice) {
		setResizable(false);
		this.setOpcoes(opcoes);
		this.setIndice(indice);

		setIconImage(Toolkit.getDefaultToolkit().getImage(Atualizar.class.getResource("/imagens/syntaxis_150x100.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 423, 231);

		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome do Produto:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(10, 30, 121, 14);
		contentPane.add(lblNome);
		
		JLabel lblQtd = new JLabel("Quantidade:");
		lblQtd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQtd.setBounds(10, 99, 160, 14);
		contentPane.add(lblQtd);
		
		txtQtd = new JTextField();
		txtQtd.setBounds(180, 98, 51, 20);
		contentPane.add(txtQtd);
		txtQtd.setColumns(10);
		
		JLabel lblProduto = new JLabel("");
		lblProduto.setBounds(180, 32, 193, 14);
		contentPane.add(lblProduto);

		// Busca o produto selecionado e mostra o nome na tela
		Produto p = opcoes.buscarProduto(indice);
		if (p != null) {
			lblProduto.setText(p.getNome());
		}
		
		JButton btnCancelar = new JButton("⌫ Cancelar");
		btnCancelar.setBackground(new Color(255, 255, 255));
		btnCancelar.setForeground(new Color(255, 0, 0));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(20, 144, 105, 37);
		contentPane.add(btnCancelar);
		
		JButton btnAdicionar = new JButton("➕ Adicionar");
		btnAdicionar.setBackground(new Color(47, 111, 237));
		btnAdicionar.setForeground(new Color(255, 255, 255));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Pega o valor digitado no campo de quantidade, sem espaços.
				String qtdTexto = txtQtd.getText().trim();

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

					opcoes.adicionarEstoque(indice, quantidade);
					dispose();

				} catch (NumberFormatException erro) {
					JOptionPane.showMessageDialog(null, "Digite apenas números!");
				}
			}
		});
		btnAdicionar.setBounds(150, 144, 105, 37);
		contentPane.add(btnAdicionar);
		
		// Indica o botão que inicia a ação após apertar a tecla enter.
	    getRootPane().setDefaultButton(btnAdicionar);
		
		JButton btnRetirar = new JButton("Retirar");
		btnRetirar.setBackground(new Color(255, 255, 255));
		btnRetirar.setForeground(new Color(255, 0, 0));
		btnRetirar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Pega o valor digitado no campo de quantidade, sem espaços.
				String qtdTexto = txtQtd.getText().trim();

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
					JOptionPane.showMessageDialog(null, "Digite apenas números!");
				}
			}
		});
		btnRetirar.setBounds(280, 144, 105, 37);
		contentPane.add(btnRetirar);
		
		JButton btnAlterarNome = new JButton("Alterar Nome");
		btnAlterarNome.setForeground(new Color(255, 255, 255));
		btnAlterarNome.setBackground(new Color(47, 111, 237));
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

					JOptionPane.showMessageDialog(null, "Já existe um produto com esse nome!");
				}
			}
		});
		btnAlterarNome.setBounds(10, 55, 121, 23);
		contentPane.add(btnAlterarNome);
		
	}

	public Opcoes getOpcoes() {
		return opcoes;
	}

	public void setOpcoes(Opcoes opcoes) {
		this.opcoes = opcoes;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}
}