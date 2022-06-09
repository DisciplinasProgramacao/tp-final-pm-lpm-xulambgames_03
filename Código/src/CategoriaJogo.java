
public enum CategoriaJogo {

    LANCAMENTOS(1.1, 1.1),
    PREMIUM(0.0, 0.0),
    REGULARES(0.7,1),
    PROMOCOES(0.3,0.5);

    private double descontoMin;
    private double descontoMax;

    private CategoriaJogo(double descontoMin, double descontoMax) {
        this.descontoMin = descontoMin;
        this.descontoMax = descontoMax;
    }

    public double getDescontoMax() {
        return descontoMax;
    }

    public double getDescontoMin() {
        return descontoMin;
    }


}
