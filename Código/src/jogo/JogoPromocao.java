package jogo;

import java.util.Random;

public class JogoPromocao extends Jogo {

	public JogoPromocao(String titulo, double preco, String genero, int classificacaoIndicativa, String produtora) {
		super(titulo, preco, genero, classificacaoIndicativa, produtora);
	}

	private double DESCONTO_MAX = 0.5;
	private double DESCONTO_MIN = 0.3;

	public double calcularPreco() {
		Random rand = new Random(); //Random para pegar um desconto entro o max e min
		double desconto = rand.nextDouble(DESCONTO_MIN, DESCONTO_MAX);
		return super.getPreco() * desconto;
	}

}
