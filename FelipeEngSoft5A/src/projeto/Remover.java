package projeto;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

// Classe responsável pela tela de confirmação de remoção.
// Ela mostra o produto selecionado e pergunta se o usuário deseja removê-lo.
public class Remover extends JFrame {

	private static final long serialVersionUID = 1L;

	// Painel principal da tela
	private JPanel contentPane;

	// Referência da tela principal
	private Opcoes opcoes;

	// Índice do produto selecionado na tabela
	private int indice;

	// Construtor da tela Remover
	public Remover(Opcoes opcoes, int indice) {
		this.setOpcoes(opcoes);
		this.setIndice(indice);
		
		// Configurações principais da janela
		setIconImage(Toolkit.getDefaultToolkit().getImage(Remover.class.getResource("/projeto/syntaxis_150x100.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 425, 166);

		// Criação do painel principal
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Texto de confirmação
		JLabel lblTitulo = new JLabel("Deseja Remover o produto:");
		lblTitulo.setBounds(10, 17, 188, 17);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblTitulo);

		// Label que mostra o nome do produto selecionado
		JLabel lblProduto = new JLabel("");
		lblProduto.setBounds(208, 20, 191, 14);
		contentPane.add(lblProduto);

		// Busca o produto selecionado e mostra seu nome na tela
		Produto p = opcoes.buscarProduto(indice);

		if (p != null) {
			lblProduto.setText(p.getNome());
		}
		
		// Botão para cancelar a remoção
		JButton btnNao = new JButton("Não");
		btnNao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNao.setBounds(70, 64, 93, 37);
		contentPane.add(btnNao);
		
		// Botão que confirma a remoção do produto
		JButton btnSim = new JButton("Sim");
		btnSim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opcoes.removerProduto(indice);
				dispose();
			}
		});
		btnSim.setBounds(253, 64, 93, 37);
		contentPane.add(btnSim);

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