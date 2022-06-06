import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;


public class Compra implements Serializable {

    private static final long serialVersionUID = 20221L;
   private double valorTotal;
    private String meioDePagamento;
    private double desconto;
    private LocalDate dataDeCompra;
    private List <Jogo> listaDeJogos;
    
    public Compra(String meioDePagamento) {
        this.meioDePagamento = meioDePagamento;
        this.dataDeCompra = LocalDate.now();
        this.listaDeJogos = new ArrayList<>();
        valorTotal=0;
    }













    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getMeioDePagamento() {
        return meioDePagamento;
    }

    public void setMeioDePagamento(String meioDePagamento) {
        this.meioDePagamento = meioDePagamento;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public LocalDate getDataDeCompra() {
        return dataDeCompra;
    }

    
}
