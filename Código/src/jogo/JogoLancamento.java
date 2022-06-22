package jogo;

public class JogoLancamento extends Jogo {

	public JogoLancamento() {
		super();
	}

	private double ACRESCIMO = 1.1;

	public double calcularPreco() {
		return super.getPrecoBase()*ACRESCIMO;
	}

	@Override
	public void setDesconto (double desconto) throws Exception{
			super.setDesconto(ACRESCIMO);
		
	}
}
