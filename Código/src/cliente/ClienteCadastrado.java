package cliente;


public class ClienteCadastrado extends Cliente {

	private static final double DESCONTO = 0.0;
	private static final double MENSALIDADE = 0.0;

	public ClienteCadastrado(){
		super ();
	}

	public double calculaMensalidade() {
		return MENSALIDADE;
	}

	public double calculaDesconto() {
		return DESCONTO;
	}

}
