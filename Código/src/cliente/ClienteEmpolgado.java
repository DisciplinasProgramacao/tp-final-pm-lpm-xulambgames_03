package cliente;

public class ClienteEmpolgado extends Cliente {

	public ClienteEmpolgado(){
		super ();
	}

	private static final double DESCONTO = 0.1;
	private static final double MENSALIDADE = 10;

	public double calculaMensalidade() {
		return MENSALIDADE;
	}

	public double calculaDesconto() {
		return DESCONTO;
	}

}
