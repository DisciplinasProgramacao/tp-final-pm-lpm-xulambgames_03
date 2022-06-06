
public enum CategoriaJogo {

    LANCAMENTOS(1.1),
    PREMIUM(0.0),
    REGULARES(0.8),
    PROMOCOES(0.4);

    private double precificacao;
    
    private CategoriaJogo(double precificacao) {
        this.precificacao = precificacao;
    }

    public double getPrecificacao(){
        return precificacao;
    }

}
