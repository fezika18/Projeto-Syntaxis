package projeto;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

public class Atualizar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private Opcoes opcoes;
	private int indice;

	public Atualizar(Opcoes opcoes, int indice) {
		this.opcoes = opcoes;
		this.indice = indice;

		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\visit0051\\eclipse-workspace\\FelipeEngSoft5A\\src\\Mídia.jpg"));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 423, 203);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome do Produto:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(10, 30, 121, 14);
		contentPane.add(lblNome);
		
		JLabel lblQtd = new JLabel("Quantidade:");
		lblQtd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQtd.setBounds(10, 65, 160, 14);
		contentPane.add(lblQtd);
		
		textField = new JTextField();
		textField.setBounds(180, 64, 51, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setBounds(180, 27, 219, 22);
		textArea.setEditable(false);
		contentPane.add(textArea);
		
		Produto p = opcoes.buscarProduto(indice);
		if (p != null) {
			textArea.setText(p.getNome());
		}
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(20, 116, 93, 37);
		contentPane.add(btnCancelar);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String qtdTexto = textField.getText().trim();

				if (qtdTexto.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite a quantidade!");
					return;
				}

				try {
					int quantidade = Integer.parseInt(qtdTexto);

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
		btnAdicionar.setBounds(150, 116, 93, 37);
		contentPane.add(btnAdicionar);
		
		JButton btnRetirar = new JButton("Retirar");
		btnRetirar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String qtdTexto = textField.getText().trim();

				if (qtdTexto.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite a quantidade!");
					return;
				}

				try {
					int quantidade = Integer.parseInt(qtdTexto);

					if (quantidade <= 0) {
						JOptionPane.showMessageDialog(null, "Digite uma quantidade maior que zero!");
						return;
					}

					boolean retirou = opcoes.retirarEstoque(indice, quantidade);

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
		btnRetirar.setBounds(280, 116, 93, 37);
		contentPane.add(btnRetirar);
	}
}