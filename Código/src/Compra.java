import java.time.LocalDate;
import java.io.Serializable;


public class Compra implements Serializable {

    private static final long serialVersionUID = 20221L;
   private double valorTotal;
    private String meioDePagamento;
    private double desconto;
    private LocalDate dataDeCompra;
    
    public Compra(double valorTotal, String meioDePagamento, double desconto, LocalDate dataDeCompra) {
        this.valorTotal = valorTotal;
        this.meioDePagamento = meioDePagamento;
        this.desconto = desconto;
        this.dataDeCompra = LocalDate.now();
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
