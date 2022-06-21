package cliente;


public class ClienteCadastrado extends Cliente {

	public ClienteCadastrado(){
		super ();
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
