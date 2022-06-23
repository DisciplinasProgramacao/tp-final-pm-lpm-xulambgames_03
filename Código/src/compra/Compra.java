package compra;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jogo.Jogo;
import jogo.JogoLancamento;
import jogo.JogoPremuim;
import jogo.JogoRegular;

public class Compra implements Serializable {

	private List<Jogo> jogos;
	private double valorTotal;
	private double desconto;
	private LocalDate dataCompra;
	private String meioPagamento;
	private double valorPago;
	private String tipoCliente;


	public Compra(String meioPagamento) {
		jogos = new ArrayList<>();
		this.desconto = 0.0;
		this.dataCompra = LocalDate.now();
		this.meioPagamento = meioPagamento;
		this.valorTotal = 0.0;
	}

	public double calculaDesconto() {

		boolean doisOuMaisLancamentos = jogos.stream().filter(j -> j instanceof JogoLancamento).count() > 1;
		boolean doisPremiumsMaisUm = jogos.stream().filter(j -> j instanceof JogoPremuim).count() == 2
				&& jogos.size() == 3; // Size, se já tiverem verifica se existe 1 jogo a mais dos 2 premiums
		boolean tresPremiums = jogos.stream().filter(j -> j instanceof JogoPremuim).count() == 3;
		boolean tresRegularesUmAcima = jogos.stream().filter(j -> j instanceof JogoRegular).count() == 3
				&& jogos.stream().filter(j -> j instanceof JogoPremuim || j instanceof JogoLancamento).count() == 1;
		boolean cincoRegulares = jogos.stream().filter(j -> j instanceof JogoRegular).count() == 5;
		boolean doisPremiums = jogos.stream().filter(j -> j instanceof JogoPremuim).count() == 2;
		boolean quatroRegulares = jogos.stream().filter(j -> j instanceof JogoRegular).count() == 4;

		if (doisOuMaisLancamentos || doisPremiumsMaisUm || tresPremiums || tresRegularesUmAcima || cincoRegulares) {
			desconto = 0.20;

		} else if (doisPremiums || quatroRegulares) {
			desconto = 0.10;

		}
		return desconto;
	}

	public void adicionarJogo(Jogo jogo) {
		jogos.add(jogo);
		this.valorTotal += jogo.calcularPreco();
		jogo.numVendas();
	}

	public List<Jogo> getJogos() {
		return jogos;
	}

	public double getValorTotal() {
		return valorTotal - valorTotal * calculaDesconto();
	}

	public LocalDate getDataCompra() {
		return dataCompra;
	}

	public String getMeioPagamento() {
		return meioPagamento;
	}

	public void setMeioPagamento(String meioPagamento) {
		this.meioPagamento = meioPagamento;
	}

	public double getValorPago() {
		return valorPago;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	@Override
	public String toString() {
		return String.format("jogos= %s \ndesconto= %.2f \nmeioPagamento= %s \nvalorTotal= %.2f \nvalorPago= %.2f",
				jogos, desconto, meioPagamento, valorTotal, valorPago);

	}

}
