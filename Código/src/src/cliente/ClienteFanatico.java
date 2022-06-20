package cliente;

public class ClienteFanatico extends Cliente {

	public ClienteFanatico(String nome, String nomeDeUsuario, String senha, String email) {
		super(nome, nomeDeUsuario, senha, email);
	}

	private static final double DESCONTO = 0.3;
	private static final double MENSALIDADE = 25.0;

	public double calculaMensalidade() {
		return MENSALIDADE;
	}

	public double calculaDesconto() {
		return DESCONTO;
	}

}
