public class Jogo {

    private String titulo;
    private double preco;
    private String genero;
    private int classificacaoIndicativa;
    private String produtora;
    private CategoriaJogo categoria;
    
    public Jogo(String titulo, double preco, String genero, int classificacaoIndicativa, String produtora,
            CategoriaJogo categoria) {
        this.titulo = titulo;
        this.preco = preco;
        this.genero = genero;
        this.classificacaoIndicativa = classificacaoIndicativa;
        this.produtora = produtora;
        this.categoria = categoria;
    }
    

    public String getTitulo() {
        return titulo;
    }

    public double getPreco() {
        return preco;
    }

    public String getGenero() {
        return genero;
    }

    public int getClassificacaoIndicativa() {
        return classificacaoIndicativa;
    }

    public String getProdutora() {
        return produtora;
    }

    public CategoriaJogo getCategoria() {
        return categoria;
    }


    public void setPreco(double preco) {
        this.preco = preco;
    }

    
}
