
public enum CategoriaJogos {

    LANCAMENTOS(0.1, 0.1),
    PREMIUM(0.1, 0.1),
    REGULARES(0.7, 1),
    PROMOCOES(0.3, 0.5);

    private double descontoMinimo;
    private double descontoMaximo;

    private CategoriaJogos(double descontoMinimo, double descontoMaximo) {
        this.descontoMinimo = descontoMinimo;
        this.descontoMaximo = descontoMaximo;
    }

    public double getDescontoMinimo(){
        return descontoMinimo;

    }

    public double getDescontoMaximo(){
        return descontoMaximo;

    }

}
