package projeto;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// Classe responsável por exibir uma mensagem de erro
// quando o usuário ou a senha estão incorretos.
public class UsuarioIncorreto extends JFrame {

	private static final long serialVersionUID = 1L;

	// Painel principal da tela
	private JPanel contentPane;

	// Método principal usado para testar esta tela separadamente
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsuarioIncorreto frame = new UsuarioIncorreto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Construtor da tela de erro
	public UsuarioIncorreto() {

		// Configurações principais da janela
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 365, 153);

		// Criação do painel principal
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Botão para fechar a tela de erro
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOk.setBounds(121, 57, 89, 23);
		contentPane.add(btnOk);
		
		// Mensagem exibida ao usuário
		JLabel lblTitulo = new JLabel("Usuario ou Senha Incorretos!");
		lblTitulo.setBounds(71, 21, 204, 17);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblTitulo);

	}

}