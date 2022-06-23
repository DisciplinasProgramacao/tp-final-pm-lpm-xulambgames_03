package jogo;

import java.io.Serializable;
import excecao.TipoInvalidoExcecao;

public abstract class Jogo implements Serializable {

	private String titulo;
	private double precoBase;
	private String genero;
	private int classificacaoIndicativa;
	private String produtora;
	private int numComprados;
	private double desconto;

	public Jogo(){
		this.numComprados = 0;
	}

	/**
	 * @see IJogo#calcularPreco()
	 */
	
	public abstract double calcularPreco();

	public Jogo mudarCategoria(String categoria) throws TipoInvalidoExcecao { 
		Jogo jogo = JogoFactory.creator(categoria);
		jogo.setClassificacaoIndicativa(this.classificacaoIndicativa);
		jogo.setPrecoBase(this.precoBase);
		jogo.setProdutora(this.produtora);
		jogo.setTitulo(this.titulo);
		jogo.setNumComprados(this.numComprados);
		return jogo;
	}

	public void numVendas(){
		numComprados++;
	}

	public int getNumComprados() {
		return numComprados;
	}

	public String getTitulo() {
		return titulo;
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

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) throws Exception {
		this.desconto = desconto;
	}

	private void setNumComprados(int numComprados) {
		this.numComprados = numComprados;
	}

	

	@Override
	public String toString() {
		return titulo+" Categoria:"+this.getClass().getSimpleName();
	}
}
