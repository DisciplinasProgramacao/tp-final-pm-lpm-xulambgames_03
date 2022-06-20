package jogo;

import java.util.Random;

public class JogoRegular extends Jogo {

	public JogoRegular(String titulo, double preco, String genero, int classificacaoIndicativa, String produtora) {
		super(titulo, preco, genero, classificacaoIndicativa, produtora);
	}

	private double DESCONTO_MAX = 1.0;
	private double DESCONTO_MIN = 0.7;

	public double calcularPreco() {
		Random rand = new Random(); //Random para pegar um desconto entro o max e min
		double desconto = rand.nextDouble(DESCONTO_MIN, DESCONTO_MAX);
		return super.getPreco() * desconto;

	}

}
