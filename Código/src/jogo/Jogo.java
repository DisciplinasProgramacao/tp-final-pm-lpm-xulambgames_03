package jogo;

public abstract class Jogo implements IJogo {

	private String titulo;
	private double preco;
	private String genero;
	private int classificacaoIndicativa;
	private String produtora;
	private int numComprados;

	

	public Jogo(String titulo, double preco, String genero, int classificacaoIndicativa, String produtora) {
		this.titulo = titulo;
		this.preco = preco;
		this.genero = genero;
		this.classificacaoIndicativa = classificacaoIndicativa;
		this.produtora = produtora;
		this.numComprados = 0;
	}

	/**
	 * @see IJogo#calcularPreco()
	 */
	
	public abstract double calcularPreco();

	public String getTitulo() {
		return titulo;
	}

	public void comprarJogo(){
		numComprados++;
	}

	public int getNumComprados() {
		return numComprados;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getClassificacaoIndicativa() {
		return classificacaoIndicativa;
	}

	public void setClassificacaoIndicativa(int classificacaoIndicativa) {
		this.classificacaoIndicativa = classificacaoIndicativa;
	}

	public String getProdutora() {
		return produtora;
	}

	public void setProdutora(String produtora) {
		this.produtora = produtora;
	}

}
