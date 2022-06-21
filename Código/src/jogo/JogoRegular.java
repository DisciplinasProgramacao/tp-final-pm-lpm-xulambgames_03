package jogo;



public class JogoRegular extends Jogo {

	public JogoRegular() {
		super();
	}

	private double DESCONTO_MAX = 1.0;
	private double DESCONTO_MIN = 0.7;

	public double calcularPreco() {
		return super.getPrecoBase() * super.getDesconto();
	}
	@Override
	public void setDesconto (double desconto){
		if (desconto <DESCONTO_MAX && desconto > DESCONTO_MIN ){
			super.setDesconto(desconto);
		}
}
}
