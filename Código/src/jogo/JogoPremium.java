package jogo;

public class JogoPremium extends Jogo {

	private static final double DESCONTO = 0.0;

	public JogoPremium() {
		super();
	}
	
	public double calcularPreco() {
		return super.getPrecoBase();
	}

	@Override
	public void setDesconto (double desconto) throws Exception{
			super.setDesconto(DESCONTO);
	}
	

}
