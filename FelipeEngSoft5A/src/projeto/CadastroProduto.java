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

public class CadastroProduto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnCadastrar;
	private JButton btnCancelar;
	private Opcoes opcoes;

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

	public CadastroProduto(Opcoes opcoes) {
		this.opcoes = opcoes;

		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\visit0051\\eclipse-workspace\\FelipeEngSoft5A\\src\\Mídia.jpg"));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 420, 198);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNome = new JLabel("Nome do Produto:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(10, 31, 121, 14);
		contentPane.add(lblNome);

		textField = new JTextField();
		textField.setBounds(180, 30, 214, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblQtd = new JLabel("Quantidade do Produto:");
		lblQtd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQtd.setBounds(10, 67, 160, 14);
		contentPane.add(lblQtd);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(180, 66, 214, 20);
		contentPane.add(textField_1);

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = textField.getText().trim();
				String qtdTexto = textField_1.getText().trim();

				if (nome.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite o nome do produto!");
					return;
				}

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

					opcoes.cadastrarProduto(nome, quantidade);
					dispose();

				} catch (NumberFormatException erro) {
					JOptionPane.showMessageDialog(null, "Digite apenas números na quantidade!");
				}
			}
		});
		btnCadastrar.setBounds(250, 111, 93, 37);
		contentPane.add(btnCadastrar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(64, 111, 93, 37);
		contentPane.add(btnCancelar);
	}
}