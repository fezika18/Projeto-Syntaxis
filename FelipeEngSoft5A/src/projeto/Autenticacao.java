package projeto;

// Classe responsável por verificar se o usuário e senha estão corretos
public class Autenticacao {
	
	// Método que recebe usuário e senha e retorna true ou false
	public static boolean autenticar(String usuario, String senha) {

		// Verifica se o usuário é "pedro" e a senha é "123"
		// O equals é usado para comparar textos (String)
        if (usuario.equals("pedro") && senha.equals("123")) {
            return true;
        }

		// Verifica se o usuário é "maria" e a senha é "321"
        if (usuario.equals("maria") && senha.equals("321")) {
            return true;
        }

		// Se não for nenhum dos casos, retorna falso
        return false;
    }
}