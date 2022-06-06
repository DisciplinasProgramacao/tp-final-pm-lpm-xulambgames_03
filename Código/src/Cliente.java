import java.util.ArrayList;
import java.util.List;

public class Cliente implements IValoravel  {

    private String nome;
    private String nomeDeUsuario;
    private String senha;
    private String email;
    private List<Compra> compras;
    private TipoCliente tipo;
    
    public Cliente(String nome, String nomeDeUsuario, String senha, String email, TipoCliente tipo) {
        this.nome = nome;
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
        this.email = email;
        compras = new ArrayList<Compra>();
        this.tipo = tipo;
    }

    public void adicionarCompra(Compra compra) {
        compras.add(compra);

    }
    
    public double calculaMensalidade(){
     return this.tipo.getMensalidade();
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

    public TipoCliente getTipo() {
        return tipo;
    }
    public double getDesconto(){
        return this.tipo.getDesconto();
       }

    
}
