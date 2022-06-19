package aplicacao;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import cliente.Cliente;
import cliente.ICliente;
import compra.Compra;
import jogo.IJogo;
import jogo.Jogo;

public class App {
    
    static List<Cliente> listaCliente;
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
        return listaCliente.stream().mapToDouble(c -> c.getCompras().stream().filter(m -> m.getDataCompra().getMonthValue()==mesReferencia)
                                .mapToDouble(Compra :: getValorPago).sum()).sum();
        
	}

	public double valorMedioDasCompras() { // Valor das vendas totais
        return listaCliente.stream().mapToDouble(c -> c.getCompras().stream()
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
            System.out.println("Informe sua senha: ");
            senha = sc.nextLine();
        }        

        System.out.println("Informe seu email: ");
        String email = sc.nextLine();

        while (!isValidEmailAddressRegex(email)) {
            System.out.println("Informe seu email: ");
            email = sc.nextLine();
        }
        
        ICliente cliente = new Cliente(email, nomeDeUsuario, senha, cliente);
        listaCliente.put(cliente);

        
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

        System.out.println("\nFavor criar uma senha que respeita as sequintes regras:");
        System.out.println(); 
        System.out.println("conter entre 8 e 20 caracteres.");
        System.out.println("Conter pelo menos um dígito.");
        System.out.println("Conter pelo menos um alfabeto maiúsculo.");
        System.out.println("Conter pelo menos um alfabeto minúsculo.");
        System.out.println("Conter pelo menos um caractere especial que inclui ! @ # $% & *() - + = ^ .");
        System.out.println("Não conter nenhum espaço em branco.\n");
  
        String regex = "^(?=.*[0-9])"
                       + "(?=.*[a-z])(?=.*[A-Z])"
                       + "(?=.*[@#$%^&+=])"
                       + "(?=\\S+$).{8,20}$";
        Pattern p = Pattern.compile(regex);
        if (senha == null) {
            return false;
        }
        Matcher m = p.matcher(senha);
        return m.matches();
    }
}

