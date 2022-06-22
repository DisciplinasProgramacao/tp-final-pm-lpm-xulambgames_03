package jogo;

public class JogoPremuim extends Jogo {

	public JogoPremuim() {
		super();
	}

	public double calcularPreco() {
		return super.getPrecoBase();
	}

	@Override
	public void setDesconto (double desconto){
		if (desconto == 0) {
			super.setDesconto(desconto);
		} else
		System.out.println("A categoria informada não possui desconto, favor digitar 0 !");
	}
	

}
