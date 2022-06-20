package cliente;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import compra.Compra;
import iterator.ImplementedItaratorCompra;
import iterator.IteratorCompra;

public abstract class Cliente implements ICliente {

	private String nome;
	private String nomeDeUsuario;
	private String senha;
	private String email;
	private List<Compra> compras;

	public Cliente(String nome, String nomeDeUsuario, String senha, String email) {
		this.nome = nome;
		this.nomeDeUsuario = nomeDeUsuario;
		this.senha = senha;
		this.email = email;
		compras = new ArrayList<Compra>();
	}

	public abstract double calculaMensalidade();
	public abstract double calculaDesconto();

	public void addCompras(Compra compra) { // Caulando o desconto do cliente, pós calculo do desconto da compra.
		compra.setValorPago(calculcarValorCompras(compra));
		compras.add(compra);
	}

	public double calculcarValorCompras(Compra compra) {
		double valorCompra = compra.getValorTotal();
		return valorCompra - valorCompra * this.calculaDesconto();
	}

	public IteratorCompra getExtrato() {
		IteratorCompra iteratorCompra = new ImplementedItaratorCompra(new LinkedList<Compra>(compras)); //Converter lista pra fila
		return iteratorCompra;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeDeUsuario() {
		return nomeDeUsuario;
	}

	public void setNomeDeUsuario(String nomeDeUsuario) {
		this.nomeDeUsuario = nomeDeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}
	

}
