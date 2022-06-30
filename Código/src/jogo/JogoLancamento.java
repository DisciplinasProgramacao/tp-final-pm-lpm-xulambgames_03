package jogo;

public class JogoLancamento extends Jogo {

	private double ACRESCIMO = 1.1;

	public JogoLancamento() {
		super();
	}
	
	public double calcularPreco() {
		return super.getPrecoBase()*ACRESCIMO;
	}

	@Override
	public void setDesconto (double desconto) throws Exception{
			super.setDesconto(ACRESCIMO);
		
	}
}
