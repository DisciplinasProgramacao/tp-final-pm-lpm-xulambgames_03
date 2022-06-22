package aplicacao;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import cliente.Cliente;
import cliente.ClienteFactory;
import compra.Compra;
import dao.DAO;
import dao.DaoGenerico;
import excecao.TipoInvalidoExcecao;
import jogo.Jogo;
import jogo.JogoFactory;

public class App {
    
    static List<Cliente> listaCliente;
    static List<Jogo> listaJogo;

    private static int opcao;
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {

        DAO<Cliente> daoCliente = new DaoGenerico<>("Clientes.DAT");
        DAO<Jogo> daoJogo = new DaoGenerico<>("Jogos.DAT");

        listaCliente = daoCliente.getAll();
        listaJogo = daoJogo.getAll();   

    
        do {
            System.out.println("\n                  *****      Xulamb Games 03      *****                ");
            System.out.println("       ================================================================= ");
            System.out.println("      |                                                                 |");
            System.out.println("      |    1.  Cadastrar cliente                                        |");
            System.out.println("      |    2.  Cadastrar jogo                                           |");
            System.out.println("      |    3 . Lançar compra                                            |");
            System.out.println("      |    4.  Histórico de um cliente específico                       |");
            System.out.println("      |    5.  Valor mensal vendido                                     |");
            System.out.println("      |    6.  Valor médio das compras                                  |");
            System.out.println("      |    7.  Jogo mais vendido                                        |");
            System.out.println("      |    8.  Jogo menos vendido                                       |");
            System.out.println("      |    9.  Alterar o tipo do cliente                                |");
            System.out.println("      |    10. Alterar a categoria do jogo                              |");
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
                    System.out.printf("O valor mensal vendido foi de: %.2f reais\n",valorMensalVendido());

                    break;

                case 6:
                System.out.printf("O valor medio das compras foi de: %.2f reais\n",valorMedioDasCompras());

                    break;

                case 7:
                    System.out.println("O jogo mais vendido foi: "+jogoMaisVendido()); 

                    break;

                case 8:
                System.out.println("O jogo menos vendido foi: "+jogoMenosVendido());
                    
                    break;

                case 9:
                    
                    break;
                
                case 10:
                    
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

        for (Jogo jogo : listaJogo) {
            if (jogo.getNumComprados() == maisComprado)
                return jogo;
        }
        return null;
    }

    public static Jogo jogoMenosVendido() {

        int menosComprado = listaJogo.stream()
                    .mapToInt(j -> j.getNumComprados()).min().getAsInt();

        for (Jogo jogo : listaJogo) {
            if (jogo.getNumComprados() == menosComprado)
                return jogo;
        }
        return null;  
	}

    public static void cadastrarCliente() {

        sc.nextLine();
        System.out.println("Informe o seu nome: ");
        String nome = sc.nextLine();

        boolean jaExistente = false;
        String nomeDeUsuario;
        do {
        System.out.println("Informe o seu nome de usuário: ");
        nomeDeUsuario = sc.nextLine();

        for (Cliente cliente : listaCliente ) {
            if (nomeDeUsuario.equals(cliente.getNomeDeUsuario())) {
                System.out.println("\nUsuário já cadastrado\n");
                jaExistente = true;
                break;
            } else 
             jaExistente = false;
        }
        } while(jaExistente);
        jaExistente = false;

        System.out.println("Informe sua senha: ");
        String senha = sc.nextLine();
        
        while (!isValidPassword(senha)) {
            senhaInvalida();
            System.out.println("Informe sua senha: ");
            senha = sc.nextLine();
        }        
        String email;
        do {
            System.out.println("Informe seu email: ");
                email = sc.nextLine();
                while (!isValidEmailAddressRegex(email)) { //Validar se o email é valido
                    System.out.println("Informe seu email: ");
                    email = sc.nextLine();
                }
            for (Cliente cliente : listaCliente ) { //Validar se o email ja existe
                if (email.equals(cliente.getEmail())) {
                    System.out.println("\nEmail já cadastrado\n");
                    jaExistente = true;
                    break;
                } else
                jaExistente = false;
            } 
        } while(jaExistente);
   
        System.out.println("Informe qual o tipo do cliente: ");
        String tipo = sc.nextLine();

        try {
            Cliente cliente = ClienteFactory.creator(tipo);
            cliente.setNome(nome);
            cliente.setEmail(email);
            cliente.setNomeDeUsuario(nomeDeUsuario);
            cliente.setSenha(senha);
            listaCliente.add(cliente);
        } catch (TipoInvalidoExcecao e) {
            System.out.println(e.getMessage());
        }
    }

    public static void cadastrarJogo() {

        sc.nextLine();
        boolean jaExistente = false;
        String titulo;
        do {
            System.out.println("Informe o titulo do jogo ");
            titulo = sc.nextLine();

        for (Jogo jogo : listaJogo ) {
            if (titulo.equals(jogo.getTitulo())) {
                System.out.println("\nJogo já cadastrado\n");
                jaExistente = true;
                break;
            } else 
             jaExistente = false;
        }
        } while(jaExistente);
        jaExistente = false;

        System.out.println("Informe o preço do jogo: ");
        double preco = sc.nextDouble();

        sc.nextLine();
        System.out.println("Informe o genero do jogo: ");
        String genero = sc.nextLine();

        System.out.println("Informe a classificação indicativa: ");
        int classificacaoIndicativa = sc.nextInt(); 
        
        sc.nextLine();

        System.out.println("Informe a produtora do jogo: ");
        String produtora = sc.nextLine();

        System.out.println("Informe a seguir a categoria do jogo: ");
        String categoria = sc.nextLine();
        
        System.out.println("Informe o desconto do jogo: ");
        double desconto = sc.nextDouble();

        try {
            Jogo jogo = JogoFactory.creator(categoria);
            jogo.setTitulo(titulo);
            jogo.setPrecoBase(preco);
            jogo.setGenero(genero);
            jogo.setClassificacaoIndicativa(classificacaoIndicativa);
            jogo.setProdutora(produtora);
            jogo.setDesconto(desconto);
            listaJogo.add(jogo); 
        } catch (TipoInvalidoExcecao e) {
        System.out.println(e.getMessage());
        }
    }

    public static void cadastrarCompra() {  

        sc.nextLine();
        System.out.println("Qual o nome do usuário: ");
        String nomeUsuario = sc.nextLine();

        Cliente cliente = null;
        for (Cliente iCliente : listaCliente) {
            if (iCliente.getNome().equals(nomeUsuario)){
                cliente = iCliente;
                break; // parar de percorrer ao encontrar
            }
        } 
        if (cliente == null) {
            System.out.println("Cliente não encontrado");
            return ;
        }

        System.out.println("Qual o meio de pagamento? : ");
        String meioPagamento = sc.nextLine();

        Compra compra = new Compra(meioPagamento);
        
        String s; //Apenas continua ao pressionar s
        do {
            addJogo(compra);
            System.out.println("Gostaria de adicionar outro jogo? s/n");
            s = sc.nextLine();
        } while (s.equals("s"));

        cliente.addCompras(compra); //Após adicionar todos os jogos, método addCompra calcula os descontos.

        System.out.println("Compra finalizada. Valor total:"+cliente.calcularValorCompras(compra));       
    }

    public static void historicoDoCliente() {

        sc.nextLine();
        System.out.println("Qual o nome do usuário: ");
        String nomeUsuario = sc.nextLine();

        Cliente cliente = null;
        for (Cliente iCliente : listaCliente) {
            if (iCliente.getNome().equals(nomeUsuario)){
                cliente = iCliente;
                break; // parar de percorrer ao encontrar
            }
        } 
        if (cliente == null) {
            System.out.println("Cliente não encontrado");
            return ;
        }
        System.out.println(cliente.getExtrato());
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

    public static void addJogo(Compra compra){
        System.out.println("Qual o titulo do jogo: ");
        String tituloJogo = sc.nextLine();

        for (Jogo jogo : listaJogo) {
            if (jogo.getTitulo().equals(tituloJogo)){
                compra.adicionarJogo(jogo);
                return ;
            }     
        }
        System.out.println("Jogo não encontrado");
    }

   

}

