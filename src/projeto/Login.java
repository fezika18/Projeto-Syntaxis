package projeto;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
import javax.swing.border.LineBorder;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JTextField txtUsuario;

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

	public Login() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/imagens/syntaxis_150x100.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 435, 527);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuário");
		lblUsuario.setBounds(40, 177, 61, 14);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(40, 202, 343, 37);
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(40, 268, 61, 14);
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(40, 293, 343, 37);
		contentPane.add(txtSenha);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(40, 387, 343, 37);
		btnEntrar.setForeground(Color.WHITE);
		btnEntrar.setBackground(new Color(47, 111, 237));
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Captura os dados digitados pelo usuário, sem espaços (no usuario).
				String usuario = txtUsuario.getText().trim();
				String senha = new String(txtSenha.getPassword());
				
				// Chama a classe de autenticação para validar login e senha
				boolean acessoLiberado = Autenticacao.autenticar(usuario, senha);

			    if (acessoLiberado) {
			        Opcoes opcoes = new Opcoes();
			        opcoes.setVisible(true);
			        dispose(); 
			        
			    } else {
			    	JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!");			    }
			}
		});
		contentPane.add(btnEntrar);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(140, 0, 150, 106);
		lblLogo.setIcon(new ImageIcon(Login.class.getResource("/imagens/syntaxis_150x100.png")));
		contentPane.add(lblLogo);
		
		JLabel lblTitulo = new JLabel("Bem-Vindo!");
		lblTitulo.setBounds(159, 118, 124, 31);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblTitulo);
		
		JLabel lblManchete = new JLabel("Faça seu login para acessar o sistema");
		lblManchete.setBounds(112, 151, 219, 14);
		lblManchete.setForeground(Color.GRAY);
		contentPane.add(lblManchete);

	}
}