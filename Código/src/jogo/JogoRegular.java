package jogo;

public class JogoRegular extends Jogo {

	private double DESCONTO_MAX = 1.0;
	private double DESCONTO_MIN = 0.7;

	public JogoRegular() {
		super();
	}

	public double calcularPreco() {
		return super.getPrecoBase() * super.getDesconto();
	}
	@Override
	public void setDesconto (double desconto) throws Exception{
		if (desconto >= DESCONTO_MIN && desconto <= DESCONTO_MAX ){
			super.setDesconto(desconto);
		} else throw new Exception("Favor inserir um desconto entre o intervalo:"+DESCONTO_MAX+"e"+DESCONTO_MIN);
	}
}
