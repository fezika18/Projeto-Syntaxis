package projeto;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Color;

// Classe responsável pela tela de login do sistema.
// Nela o usuário informa seu usuário e senha para acessar o sistema.
public class Login extends JFrame {

	private static final long serialVersionUID = 1L;

	// Painel principal da tela
	private JPanel contentPane;

	// Campo para digitar o usuário
	private JTextField txtUsuario;

	// Campo para digitar a senha
	private JPasswordField txtSenha;

	// Método principal que inicia a aplicação
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Construtor da tela de login
	public Login() {

		// Configurações da janela
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/projeto/syntaxis_150x100.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 449, 305);

		// Criação do painel principal
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Texto indicando o campo de usuário
		JLabel lblUsuario = new JLabel("Usuário:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuario.setBounds(10, 127, 61, 14);
		contentPane.add(lblUsuario);
		
		// Campo onde o usuário digita o login
		txtUsuario = new JTextField();
		txtUsuario.setBounds(81, 126, 323, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		// Texto indicando o campo de senha
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha.setBounds(10, 170, 61, 14);
		contentPane.add(lblSenha);
		
		// Campo onde o usuário digita a senha
		txtSenha = new JPasswordField();
		txtSenha.setBounds(81, 169, 323, 20);
		contentPane.add(txtSenha);
		
		// Botão responsável por realizar o login
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Captura os dados digitados pelo usuário
				String usuario = txtUsuario.getText().trim();
				String senha = new String(txtSenha.getPassword());
				
				// Chama a classe de autenticação para validar login e senha
				boolean acessoLiberado = Autenticacao.autenticar(usuario, senha);

				// Se estiver correto, abre a tela principal
			    if (acessoLiberado) {
			        Opcoes opcoes = new Opcoes();
			        opcoes.setVisible(true);
			        dispose(); // fecha a tela de login
			        
			    } else {
			    	// Se estiver incorreto, abre a tela de erro
			    	JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!");			    }
			}
		});
		btnEntrar.setBounds(167, 218, 93, 37);
		contentPane.add(btnEntrar);
		
		// Label para exibir o logo do sistema
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/projeto/syntaxis_150x100.png")));
		lblNewLabel.setBounds(136, 5, 150, 106);
		contentPane.add(lblNewLabel);

	}
}