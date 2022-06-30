package jogo;

public class JogoPremuim extends Jogo {

	private static final double DESCONTO = 0.0;

	public JogoPremuim() {
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
