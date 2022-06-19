package aplicacao;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import cliente.Cliente;
import cliente.ClienteFactory;
import cliente.ICliente;
import cliente.TipoCliente;
import compra.Compra;
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
    
        do {
            System.out.println("\n                  *****      Xulamb Games 03      *****                ");
            System.out.println("       ================================================================= ");
            System.out.println("      |                                                                 |");
            System.out.println("      |    1. Cadastrar cliente                                         |");
            System.out.println("      |    2. Cadastrar jogo                                            |");
            System.out.println("      |    3. Lançar compra                                             |");
            System.out.println("      |    4. Histórico de um cliente específico                        |");
            System.out.println("      |    5. Calcular o valor de uma nova compra                       |");
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


                    break;

                case 4:

                    break;

                case 5:

                    break;


                case 0:
                    break;
            }

        } while (opcao != 0);
    }

    // DAO padrão de projeto pra salvar arquivo

	public double valorMensalVendido() { // Valor do mes atual
        int mesReferencia = LocalDate.now().getMonthValue();
        return listaCliente.stream().mapToDouble(c -> ((Cliente) c).getCompras().stream().filter(m -> m.getDataCompra().getMonthValue()==mesReferencia)
                                .mapToDouble(Compra :: getValorPago).sum()).sum();  
	}

	public double valorMedioDasCompras() { // Valor das vendas totais
        return listaCliente.stream().mapToDouble(c -> ((Cliente) c).getCompras().stream()
                                .mapToDouble(Compra :: getValorPago).sum()).average().getAsDouble();
	}

    public Jogo jogoMaisVendido() {
        return (Jogo) listaJogo.stream()
                            .max((a,b) -> a.equals(b) ? 1 : -1).get();      
    }

    public Jogo jogoMenosVendido() {
        return (Jogo) listaJogo.stream()
                            .max((a,b) -> a.equals(b) ? -1 : 1).get();   
	}

    public static void cadastrarCliente() throws Exception {

        sc.nextLine();
        System.out.println("Informe o seu nome: ");
        String nome = sc.nextLine();

        System.out.println("Informe o seu nome de usuário: ");
        String nomeDeUsuario = sc.nextLine();

        System.out.println("Informe sua senha: ");
        String senha = sc.nextLine();

        while (!isValidPassword(senha)) {
            senhinvalida();
            System.out.println("Informe sua senha: ");
            senha = sc.nextLine();
        }        

        System.out.println("Informe seu email: ");
        String email = sc.nextLine();

        while (!isValidEmailAddressRegex(email)) {
            System.out.println("Informe seu email: ");
            email = sc.nextLine();
        }
        
        ICliente cliente = ClienteFactory.creator(TipoCliente.CADASTRADOS, nome, nomeDeUsuario, senha, email);
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

        CategoriaJogos categoriaJogo;
        categoriaJogo = validarCategoria(categoria);

        while (categoriaJogo == null)  {

            System.out.println("Informe a seguir a categoria do jogo: ");
            categoria = sc.nextLine();
            categoriaJogo = validarCategoria(categoria);
        }

        IJogo jogo = JogoFactory.creator(categoriaJogo, titulo, preco, genero, classificacaoIndicativa, produtora);
        
    }

    public static void cadastrarCompra() throws Exception {

        
        
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

    public static void senhinvalida(){
        System.out.println("\nFavor criar uma senha que respeita as sequintes regras:");
        System.out.println(); 
        System.out.println("conter entre 8 e 20 caracteres.");
        System.out.println("Conter pelo menos um dígito.");
        System.out.println("Conter pelo menos um alfabeto maiúsculo.");
        System.out.println("Conter pelo menos um alfabeto minúsculo.");
        System.out.println("Conter pelo menos um caractere especial que inclui ! @ # $% & *() - + = ^ .");
        System.out.println("Não conter nenhum espaço em branco.\n");
    }

    public static CategoriaJogos validarCategoria(String categoria){

        CategoriaJogos categoriaJogos = null;
        if (categoria.toUpperCase().equals(CategoriaJogos.LANCAMENTO.toString())) {
            categoriaJogos = CategoriaJogos.LANCAMENTO;
        }
        else if (categoria.toUpperCase().equals(CategoriaJogos.PREMIUM.toString())){
            categoriaJogos = CategoriaJogos.PREMIUM;
        }
        else if (categoria.toUpperCase().equals(CategoriaJogos.PROMOCAO.toString())){
            categoriaJogos = CategoriaJogos.PROMOCAO;
        }
        else if (categoria.toUpperCase().equals(CategoriaJogos.REGULAR.toString())){
            categoriaJogos = CategoriaJogos.REGULAR;
        } else
            System.out.println("\nFavor inserir uma categoria entre lancamento, premium, promocao e regular.\n");

        return categoriaJogos;
    }
}

