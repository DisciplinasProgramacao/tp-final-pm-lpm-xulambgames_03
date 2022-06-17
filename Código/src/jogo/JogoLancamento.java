package jogo;

public class JogoLancamento extends Jogo {

	public JogoLancamento(String titulo, double preco, String genero, int classificacaoIndicativa, String produtora) {
		super(titulo, preco, genero, classificacaoIndicativa, produtora);
	}

	private double ACRESCIMO = 1.1;

	public double calcularPreco() {
		return super.getPreco()*ACRESCIMO;
	}
}
