import java.util.ArrayList;
import java.util.List;

public class Cliente implements IValoravel  {

    private String nome;
    private String nomeDeUsuario;
    private String senha;
    private String email;
    private IValoravel valoravel;
    private List<Compra> compras;
    private TipoCliente tipo;
    
    public Cliente(String nome, String nomeDeUsuario, String senha, String email, IValoravel valoravel, TipoCliente tipo) {
        this.nome = nome;
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
        this.email = email;
        this.valoravel = valoravel;
        compras = new ArrayList<Compra>();
        this.tipo = tipo;
    }

    public void adicionarCompra(Compra compra) {
        compras.add(compra);

    }
    
    public void calculaMensalidade(){
     
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

    public IValoravel getValoravel() {
        return valoravel;
    }

    public void setValoravel(IValoravel valoravel) {
        this.valoravel = valoravel;
    }

    public TipoCliente getTipo() {
        return tipo;
    }
    

    
}
