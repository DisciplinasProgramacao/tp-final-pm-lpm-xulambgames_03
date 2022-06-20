package cliente;

public class ClienteFactory {

	public static ICliente creator(TipoCliente tipoCliente, String nome, String nomeDeUsuario, String senha,
			String email) throws Exception {
				
		ICliente cliente = null;

		if (tipoCliente == null) {
			throw new Exception("Tipo do cliente não expecificado");
		}
		if (tipoCliente == TipoCliente.CADASTRADOS) {
			cliente = new ClienteCadastrado(nome, nomeDeUsuario, senha, email);
		}
		if (tipoCliente == TipoCliente.EMPOLGADOS) {
			cliente = new ClienteEmpolgado(nome, nomeDeUsuario, senha, email);
		}
		if (tipoCliente == TipoCliente.FANATICOS) {
			cliente = new ClienteFanatico(nome, nomeDeUsuario, senha, email);

		}
		return cliente;
	}
}
