package jogo;

public class JogoPremuim extends Jogo {

	public JogoPremuim(String titulo, double preco, String genero, int classificacaoIndicativa, String produtora) {
		super(titulo, preco, genero, classificacaoIndicativa, produtora);
	}

	public double calcularPreco() {
		return super.getPreco();
	}

}
