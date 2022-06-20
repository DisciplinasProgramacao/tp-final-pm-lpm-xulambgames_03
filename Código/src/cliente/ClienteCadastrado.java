package cliente;


public class ClienteCadastrado extends Cliente {

	public ClienteCadastrado(String nome, String nomeDeUsuario, String senha, String email) {
		super(nome, nomeDeUsuario, senha, email);
	}

	private static final double DESCONTO = 0.0;
	private static final double MENSALIDADE = 0.0;

	public double calculaMensalidade() {
		return MENSALIDADE;
	}

	public double calculaDesconto() {
		return DESCONTO;
	}

}
