import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private String nome;
    private String nomeDeUsuario;
    private String senha;
    private String email;
    private List<Compra> compras;
    private IValoravel tipo;
    
    public Cliente(String nome, String nomeDeUsuario, String senha, String email) {
        this.nome = nome;
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
        this.email = email;
        this.tipo = null;
        compras = new ArrayList<Compra>();
        
    }

    public void adicionarCompra(Compra compra) {
        compras.add(compra);

    }
    
    public double calculaMensalidade(){
        return this.tipo.calculaMensalidade();
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeDeUsuario() {
        return nomeDeUsuario;
    }

    public void setNomeDeUsuario(String nomeDeUsuario) {
        this.nomeDeUsuario = nomeDeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getDesconto(double compra){
        return this.tipo.getDesconto(compra);
       }   
}
