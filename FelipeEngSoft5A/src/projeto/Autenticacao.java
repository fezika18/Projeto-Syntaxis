package projeto;

public class Autenticacao {
	
	public static boolean autenticar(String usuario, String senha) {

        if (usuario.equals("pedro") && senha.equals("123")) {
            return true;
        }

        if (usuario.equals("maria") && senha.equals("321")) {
            return true;
        }

        return false;
    }
}
