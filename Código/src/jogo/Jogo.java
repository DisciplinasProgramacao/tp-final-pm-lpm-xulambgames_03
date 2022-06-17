package jogo;
import compra.Compra;

public abstract class Jogo implements IJogo {

	private String titulo;
	private double preco;
	private String genero;
	private int classificacaoIndicativa;
	private String produtora;
	private CategoriaJogos categoria;
	private CategoriaJogos categoriaJogos;
	private Compra compra;



	public double getPrecificacao() {
		return 0;
	}


	/**
	 * @see IJogo#calcularPreco()
	 */
	public double calcularPreco() {
		return 0;
	}

}
