package aplicacao;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import cliente.ClienteFactory;
import cliente.ICliente;
import cliente.TipoCliente;
import compra.Compra;
import dao.DAO;
import dao.DaoGenerico;
import iterator.IteratorCompra;
import jogo.CategoriaJogos;
import jogo.IJogo;
import jogo.Jogo;
import jogo.JogoFactory;

public class App {
    
    static List<ICliente> listaCliente;
    static List<IJogo> listaJogo;

    private static int opcao;
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {

        DAO<ICliente> daoCliente = new DaoGenerico<>("Clientes.DAT");
        DAO<IJogo> daoJogo = new DaoGenerico<>("Jogos.DAT");

        listaCliente = daoCliente.getAll();
        listaJogo = daoJogo.getAll();   

    
        do {
            System.out.println("\n                  *****      Xulamb Games 03      *****                ");
            System.out.println("       ================================================================= ");
            System.out.println("      |                                                                 |");
            System.out.println("      |    1. Cadastrar cliente                                         |");
            System.out.println("      |    2. Cadastrar jogo                                            |");
            System.out.println("      |    3. Lançar compra                                             |");
            System.out.println("      |    4. Histórico de um cliente específico                        |");
            System.out.println("      |    5. Valor mensal vendido                                      |");
            System.out.println("      |    6. Valor médio das compras                                   |");
            System.out.println("      |    7. Jogo mais vendido                                         |");
            System.out.println("      |    7. Jogo menos vendido                                        |");
            System.out.println("      |                                                                 |");
            System.out.println("      |    0. Salvar e sair                                             |");
            System.out.println("      *=================================================================*\n");
            System.out.print("Informe uma opcao: ");

            opcao = sc.nextInt();
            System.out.println("");

            switch (opcao) {

                case 1:
                    cadastrarCliente();
                    break;

                case 2:
                    cadastrarJogo();

                    break;

                case 3:
                    cadastrarCompra();

                    break;

                case 4:
                    historicoDoCliente();

                    break;

                case 5:
                    valorMedioDasCompras();

                    break;

                case 6:
                   jogoMaisVendido();

                    break;

                case 7:
                    jogoMenosVendido();

                    break;


                case 0:
                    break;
            }

        } while (opcao != 0);

        daoJogo.salvarTodos(listaJogo);
        daoCliente.salvarTodos(listaCliente);
    }


	public static double valorMensalVendido() { // Valor do mes atual
        int mesReferencia = LocalDate.now().getMonthValue();
        return listaCliente.stream().mapToDouble(c -> (c).getCompras().stream().filter(m -> m.getDataCompra().getMonthValue()==mesReferencia)
                                .mapToDouble(Compra :: getValorPago).sum()).sum();  
	}

	public static double valorMedioDasCompras() { // Valor das vendas totais
        return listaCliente.stream().mapToDouble(c -> (c).getCompras().stream()
                                .mapToDouble(Compra :: getValorPago).sum()).average().getAsDouble();
	}

    public static Jogo jogoMaisVendido() {

        int maisComprado = listaJogo.stream()
                    .mapToInt(j -> j.getNumComprados()).max().getAsInt();

        for (IJogo jogo : listaJogo) {
            if (jogo.getNumComprados() == maisComprado)
                return (Jogo) jogo;
        }
        return null;
    }

    public static Jogo jogoMenosVendido() {
        int menosComprado = listaJogo.stream()
                    .mapToInt(j -> j.getNumComprados()).min().getAsInt();

        for (IJogo jogo : listaJogo) {
            if (jogo.getNumComprados() == menosComprado)
                return (Jogo) jogo;
        }
        return null;  
	}

    public static void cadastrarCliente() throws Exception {

        sc.nextLine();
        System.out.println("Informe o seu nome: ");
        String nome = sc.nextLine();

        boolean jaExistente = false;
        String nomeDeUsuario;
        do {
        System.out.println("Informe o seu nome de usuário: ");
        nomeDeUsuario = sc.nextLine();

        for (ICliente cliente : listaCliente ) {
            if (nomeDeUsuario.equals(cliente.getNomeDeUsuario())) {
                System.out.println("Usuário já cadastrado");
                jaExistente = true;
                break;
            }
        }

        } while(jaExistente);

        

        System.out.println("Informe sua senha: ");
        String senha = sc.nextLine();

        while (!isValidPassword(senha)) {
            senhaInvalida();
            System.out.println("Informe sua senha: ");
            senha = sc.nextLine();
        }        

        System.out.println("Informe seu email: ");
        String email = sc.nextLine();

        while (!isValidEmailAddressRegex(email)) {
            System.out.println("Informe seu email: ");
            email = sc.nextLine();
        }

        
        System.out.println("Informe qual o tipo do cliente: ");
        String tipo = sc.nextLine();

        TipoCliente tipoCliente = validarTipoCliente(tipo);

        while (tipoCliente == null) {
        System.out.println("Informe qual o tipo do cliente: ");
        tipo = sc.nextLine();
        tipoCliente = validarTipoCliente(tipo);
        }
        
        ICliente cliente = ClienteFactory.creator(tipoCliente, nome, nomeDeUsuario, senha, email);
        listaCliente.add(cliente);

    }

    public static void cadastrarJogo() throws Exception {

        sc.nextLine();
        System.out.println("Informe o titulo do jogo ");
        String titulo = sc.nextLine();

        System.out.println("Informe o preço do jogo: ");
        double preco = sc.nextDouble();

        sc.nextLine();
        System.out.println("Informe o genero do jogo: ");
        String genero = sc.nextLine();

        System.out.println("Informe a classificação indicativa: ");
        int classificacaoIndicativa = sc.nextInt();       

        System.out.println("Informe a produtora do jogo: ");
        String produtora = sc.nextLine();

        sc.nextLine();

        System.out.println("Informe a seguir a categoria do jogo: ");
        String categoria = sc.nextLine();

        IJogo jogo = JogoFactory.creator(validarCategoriaJogos(categoria), titulo, preco, genero, classificacaoIndicativa, produtora);
        listaJogo.add(jogo);
        
    }

    public static void cadastrarCompra() throws Exception {  

        System.out.println("Qual o nome do usuário: ");
        String nomeUsuario = sc.nextLine();

        ICliente cliente = null;
        for (ICliente iCliente : listaCliente) {
            if (iCliente.getNome().equals(nomeUsuario)){
                cliente = iCliente;
                break; // parar de percorrer
            }
        } 
        if (cliente == null) {
            System.out.println("Cliente não encontrado");
            return ;
        }

        System.out.println("Qual o meio de pagamento? : ");
        String meioPagamento = sc.nextLine();

        Compra compra = new Compra(meioPagamento);
        
        String s;
        do {
            addJogo(compra);
            System.out.println("Gostaria de adicionar outro jogo? s/n");
            s = sc.nextLine();
        } while (s.equals("s"));

        cliente.addCompras(compra); //Após adicionar todos os jogos, método addCompra calcula os descontos.

        System.out.println("Compra finalizada. Valor total:"+cliente.calcularValorCompras(compra));       
    }

    public static void historicoDoCliente() {

        System.out.println("Qual o nome do usuário: ");
        String nomeUsuario = sc.nextLine();

        ICliente cliente = null;
        for (ICliente iCliente : listaCliente) {
            if (iCliente.getNome().equals(nomeUsuario)){
                cliente = iCliente;
                break; // parar de percorrer
            }
        } 
        if (cliente == null) {
            System.out.println("Cliente não encontrado");
            return ;
        }

        IteratorCompra iteratorCompra = cliente.getExtrato();

        while (iteratorCompra.hasNext()) {
            System.out.println(iteratorCompra.getNext());
        } 

    }

    public static boolean isValidEmailAddressRegex(String email) {
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            } else
            System.out.println("\nFavor inserir um email válido!\n");
        }
        return isEmailIdValid;
    }

    public static boolean isValidPassword (String senha) {

        String regex = "^(?=.*[0-9])"
                       + "(?=.*[a-z])(?=.*[A-Z])"
                       + "(?=.*[!@#$%^&+=])"
                       + "(?=\\S+$).{8,20}$";
        Pattern p = Pattern.compile(regex);
        if (senha == null) {
            return false;
        }
        Matcher m = p.matcher(senha);
        return m.matches();
    }

    public static void senhaInvalida(){
        System.out.println("\nFavor criar uma senha que respeita as sequintes regras:");
        System.out.println(); 
        System.out.println("conter entre 8 e 20 caracteres.");
        System.out.println("Conter pelo menos um dígito.");
        System.out.println("Conter pelo menos um alfabeto maiúsculo.");
        System.out.println("Conter pelo menos um alfabeto minúsculo.");
        System.out.println("Conter pelo menos um caractere especial que inclui ! @ # $% & *() - + = ^ .");
        System.out.println("Não conter nenhum espaço em branco.\n");
    }

    public static TipoCliente validarTipoCliente(String tipo) {

        TipoCliente tipoCliente = null;
        if (tipo.equalsIgnoreCase(TipoCliente.CADASTRADOS.toString())) {
            tipoCliente = TipoCliente.CADASTRADOS;
        }
        else if (tipo.equalsIgnoreCase(TipoCliente.EMPOLGADOS.toString())){
            tipoCliente = TipoCliente.EMPOLGADOS;
        }
        else if (tipo.equalsIgnoreCase(TipoCliente.FANATICOS.toString())){
            tipoCliente = TipoCliente.FANATICOS;
        } else
            System.out.println("\nFavor inserir um tipo de cliente entre cadastrados, empolgados e fanáticos.\n");

        return tipoCliente;
    }

    public static CategoriaJogos validarCategoriaJogos(String categoria) {

        CategoriaJogos categoriaJogos = null;
        
        if (categoria.equalsIgnoreCase(CategoriaJogos.LANCAMENTO.toString())) {
            categoriaJogos = CategoriaJogos.LANCAMENTO;
        }
        else if (categoria.equalsIgnoreCase(CategoriaJogos.PREMIUM.toString())){
            categoriaJogos = CategoriaJogos.PREMIUM;
        }
        else if (categoria.equalsIgnoreCase(CategoriaJogos.PROMOCAO.toString())){
            categoriaJogos = CategoriaJogos.PROMOCAO;
        }
        else if (categoria.equalsIgnoreCase(CategoriaJogos.REGULAR.toString())){
            categoriaJogos = CategoriaJogos.REGULAR;
        } else
            System.out.println("\nFavor inserir uma categoria entre lancamento, premium, promocao e regular.\n");

        return categoriaJogos;
    }

    public static void addJogo(Compra compra){
        System.out.println("Qual o titulo do jogo: ");
        String tituloJogo = sc.nextLine();

        for (IJogo jogo : listaJogo) {
            if (jogo.getTitulo().equals(tituloJogo)){
                compra.adicionarJogo(jogo);
                return ;
            }     
        }
        System.out.println("Jogo não encontrado");
    }

}

