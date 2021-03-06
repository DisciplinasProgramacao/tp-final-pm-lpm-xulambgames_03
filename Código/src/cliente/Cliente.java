package cliente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import compra.Compra;
import excecao.TipoInvalidoExcecao;

public abstract class Cliente implements Serializable {

	private String nome;
	private String nomeDeUsuario;
	private String senha;
	private String email;
	private List<Compra> compras;

	public Cliente() {
		compras = new ArrayList<Compra>();
	}

	public abstract double calculaMensalidade();
	public abstract double calculaDesconto();

	public void addCompras(Compra compra) { // Caulando o desconto do cliente, pós calculo do desconto da compra.
		compra.setValorPago(calcularValorCompras(compra));
		compras.add(compra);
		compra.setTipoCliente(this.getClass().getSimpleName());
	}

	public double calcularValorCompras(Compra compra) {
		double valorCompra = compra.getValorTotal();
		return valorCompra - valorCompra * this.calculaDesconto();
	}

	public String  getExtrato() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nXULAMBS GAMES 03\n");
		builder.append("-------------------------------------");
		for (Compra compra : compras) {
			builder.append("\nExtrato em " + compra.getDataCompra() + "\n\n");
			builder.append("cliente: "+nome+"\nNome de usuário: "+nomeDeUsuario+"\n"+compra+"\n"+"Tipo:"+compra.getTipoCliente());
			builder.append("\n-------------------------------------");
		}
		return builder.toString();
	}

	public Cliente mudarTipo(String tipo) throws TipoInvalidoExcecao { 
		Cliente cliente = ClienteFactory.creator(tipo);
		cliente.setCompras(this.compras);
		cliente.setEmail(this.email);
		cliente.setNome(this.nome);
		cliente.setNomeDeUsuario(this.nomeDeUsuario);
		cliente.setSenha(this.senha);

		return cliente;
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
	public void setSenha(String senha) {
		this.senha = senha;
	}

}
