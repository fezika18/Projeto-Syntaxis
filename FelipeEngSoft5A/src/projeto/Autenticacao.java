package projeto;

public class Autenticacao {
	
	public static boolean autenticar(String usuario, String senha) {

		// Verifica se o usuário é "pedro" e a senha é "123"
		// O equals é usado para comparar textos (String)
        if (usuario.equals("pedro") && senha.equals("123")) {
            return true;
        }

        if (usuario.equals("maria") && senha.equals("321")) {
            return true;
        }
        
        if (usuario.equals("eduardo") && senha.equals("271023")) {
            return true;
        }

        return false;
    }
}