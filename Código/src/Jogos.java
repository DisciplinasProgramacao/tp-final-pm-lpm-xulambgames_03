public class Jogos {

    private String titulo;
    private double preco;
    private String genero;
    private int classificacaoIndicativa;
    private String produtora;
    private CategoriaJogos categoria;
    
    public Jogos(String titulo, double preco, String genero, int classificacaoIndicativa, String produtora,
            CategoriaJogos categoria) {
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

    public CategoriaJogos getCategoria() {
        return categoria;
    }


    public void setPreco(double preco) {
        this.preco = preco;
    }

    
}
