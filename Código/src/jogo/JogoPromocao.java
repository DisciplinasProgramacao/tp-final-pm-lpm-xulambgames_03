package jogo;



public class JogoPromocao extends Jogo {

	public JogoPromocao() {
		super();
	}

	private double DESCONTO_MAX = 0.5;
	private double DESCONTO_MIN = 0.3;
	

	public double calcularPreco() {
		return super.getPrecoBase() * super.getDesconto();
	}

	@Override
	public void setDesconto (double desconto){
		if (desconto >= DESCONTO_MIN && desconto <= DESCONTO_MAX ){
			super.setDesconto(desconto);
		} else
		System.out.println("Favor inserir um desconto entre o intervalo:"+DESCONTO_MAX+"e"+DESCONTO_MIN);
	}
}
