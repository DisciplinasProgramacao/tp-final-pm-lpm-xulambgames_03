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

	public Compra(String meioPagamento) {
		jogos = new ArrayList<>();
		this.desconto = 0.0;
		this.dataCompra = LocalDate.now();
		this.meioPagamento = meioPagamento;
		this.valorTotal = 0.0;
	}

	public double calculaDesconto() {

		boolean umOuMaisLancamentos = jogos.stream().map(j -> j instanceof JogoLancamento).count()>=1;
		boolean doisPremiumsMaisUm = jogos.stream().map(j -> j instanceof JogoPremuim).count()>1 && jogos.size()>2; // Size, se já tiverem 2 premiums e a quantidade de jogos forem 3, satisfaz
		boolean tresPremiums = jogos.stream().map(j -> j instanceof JogoPremuim).count()>2;
		boolean tresRegularesUmAcima = jogos.stream().map(j -> j instanceof JogoRegular).count()>2 
											&& jogos.stream().map(j -> j instanceof JogoPremuim || j instanceof JogoLancamento).count()>=1; 
		boolean cincoRegulares = jogos.stream().map(j -> j instanceof JogoRegular).count()>4;
		boolean doisPremiums = jogos.stream().map(j -> j instanceof JogoPremuim).count()>1;
		boolean quatroRegulares = jogos.stream().map(j -> j instanceof JogoRegular).count()>=3;

		if(umOuMaisLancamentos || doisPremiumsMaisUm || tresPremiums || tresRegularesUmAcima || cincoRegulares) {
			desconto = 0.20;
			
		}
		else if(doisPremiums || quatroRegulares){
			desconto = 0.10;

		}
		return desconto;
	}

	public void adicionarJogo(Jogo jogo) {
		jogos.add(jogo);
		this.valorTotal += jogo.calcularPreco();
		jogo.comprarJogo();
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

	@Override
	public String toString() {
		return  "jogos=" + jogos + "\ndesconto=" + desconto +  "\nmeioPagamento="
				+ meioPagamento  + "\nvalorTotal=" + valorTotal + "\nvalorPago=" + valorPago;
	}

}
