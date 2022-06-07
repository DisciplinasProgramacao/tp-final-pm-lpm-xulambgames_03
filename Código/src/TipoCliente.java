public enum TipoCliente implements IValoravel{

    EMPOLGADOS(10.0, 0.1),
    FANATICOS(25.0, 0.3);

    private double mensalidade;
    private double desconto; 

    private TipoCliente(double mensalidade, double desconto) {
        this.mensalidade = mensalidade;
        this.desconto = desconto;
    }

    @Override
    public double calculaMensalidade() {
        // TODO Auto-generated method stub
        return mensalidade;
    }

    @Override
    public double getDesconto(double compra) {
        // TODO Auto-generated method stub
        return compra*desconto;
    }

    
}
