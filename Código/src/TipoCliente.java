public enum TipoCliente {

    CADASTRADOS(0),
    EMPOLGADOS(10),
    FANATICOS(25);
    private int mensalidade;

    private TipoCliente(int mensalidade) {
        this.mensalidade = mensalidade;
    }

    public int getMensalidade() {
        return mensalidade; 
    }


    
}
