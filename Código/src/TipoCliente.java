public enum TipoCliente {

    CADASTRADOS(0, 0),
    EMPOLGADOS(10, 0.1),
    FANATICOS(25, 0.3);
    private int mensalidade;
    private double desconto; 

    private TipoCliente(int mensalidade, double desconto) {
        this.mensalidade = mensalidade;
        this.desconto = desconto;
    }

    public int getMensalidade() {
        return mensalidade; 
    }
    public double getDesconto(){
        return desconto;
    
    }

    
}
