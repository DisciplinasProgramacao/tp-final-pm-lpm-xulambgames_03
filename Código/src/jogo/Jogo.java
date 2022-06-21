package jogo;

import java.io.Serializable;

public abstract class Jogo implements Serializable {

	private String titulo;
	private double precoBase;
	private String genero;
	private int classificacaoIndicativa;
	private String produtora;
	private int numComprados;
	private double desconto;

	

	public double getDesconto() {
		return desconto;
	}

	public Jogo(){
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

	public double getPrecoBase() {
		return precoBase;
	}

	public void setPrecoBase(double preco) {
		this.precoBase = preco;
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
	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
}
