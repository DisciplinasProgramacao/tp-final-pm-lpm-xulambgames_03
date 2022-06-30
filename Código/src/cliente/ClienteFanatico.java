package cliente;

public class ClienteFanatico extends Cliente {

	private static final double DESCONTO = 0.3;
	private static final double MENSALIDADE = 25.0;

	public ClienteFanatico(){
		super ();
	}

	public double calculaMensalidade() {
		return MENSALIDADE;
	}

	public double calculaDesconto() {
		return DESCONTO;
	}

}
