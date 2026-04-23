package projeto;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Remover extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Opcoes opcoes;
	private int indice;
	private JTextArea textArea;

	public Remover(Opcoes opcoes, int indice) {
		this.opcoes = opcoes;
		this.indice = indice;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\visit0051\\eclipse-workspace\\FelipeEngSoft5A\\src\\Mídia.jpg"));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 425, 166);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Deseja Remover o produto:");
		lblTitulo.setBounds(10, 17, 188, 17);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblTitulo);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(195, 15, 204, 22);
		contentPane.add(textArea);

		Produto p = opcoes.buscarProduto(indice);

		if (p != null) {
			textArea.setText(p.getNome());
		}
		
		JButton btnNao = new JButton("Não");
		btnNao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNao.setBounds(70, 64, 93, 37);
		contentPane.add(btnNao);
		
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

}
