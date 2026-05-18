package projeto;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class Remover extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private Opcoes opcoes;

	private int indice;

	public Remover(Opcoes opcoes, int indice) {
		setResizable(false);
		this.setOpcoes(opcoes);
		this.setIndice(indice);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Remover.class.getResource("/imagens/syntaxis_150x100.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 425, 166);

		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Deseja Remover o produto:");
		lblTitulo.setBounds(10, 17, 188, 17);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblTitulo);

		JLabel lblProduto = new JLabel("");
		lblProduto.setBounds(208, 20, 191, 14);
		contentPane.add(lblProduto);

		// Busca o produto selecionado e mostra seu nome na tela
		Produto p = opcoes.buscarProduto(indice);

		if (p != null) {
			lblProduto.setText(p.getNome());
		}
		
		JButton btnNao = new JButton("Não");
		btnNao.setBackground(new Color(255, 255, 255));
		btnNao.setForeground(new Color(255, 0, 0));
		btnNao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNao.setBounds(70, 64, 93, 37);
		contentPane.add(btnNao);
		
		JButton btnSim = new JButton("Sim");
		btnSim.setForeground(new Color(255, 255, 255));
		btnSim.setBackground(new Color(47, 111, 237));
		btnSim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opcoes.removerProduto(indice);
				dispose();
			}
		});
		btnSim.setBounds(253, 64, 93, 37);
		contentPane.add(btnSim);
		
		// Indica o botão que inicia a ação após apertar a tecla enter.
	    getRootPane().setDefaultButton(btnSim);

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