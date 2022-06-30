package cliente;

import excecao.TipoInvalidoExcecao;

public class ClienteFactory { //Factory method

	public static Cliente creator( String tipo) throws TipoInvalidoExcecao {
				
		Cliente cliente = null;

		if (tipo.equalsIgnoreCase("cadastrado")) {
			cliente = new ClienteCadastrado();
		}
		else if (tipo.equalsIgnoreCase("empolgado")) {
			cliente = new ClienteEmpolgado ();
		}
		else if (tipo.equalsIgnoreCase("fanatico")) {
			cliente = new ClienteFanatico();

		}
		else {
			throw new TipoInvalidoExcecao(" O " + tipo+ " não é um tipo válido para cliente");
		}
		return cliente;
	}
}
