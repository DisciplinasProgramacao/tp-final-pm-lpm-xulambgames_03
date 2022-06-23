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

    private static String opcao;
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

            opcao = sc.next();
            System.out.println("");
            if (validarOpcao(opcao))

                switch (Integer.parseInt(opcao)) {
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
                        System.out.printf("O valor mensal vendido foi de: %.2f reais\n", valorMensalVendido());

                        break;

                    case 6:
                        System.out.printf("O valor medio das compras foi de: %.2f reais\n", valorMedioDasCompras());

                        break;

                    case 7:
                        jogoMaisVendido();

                        break;

                    case 8:
                        jogoMenosVendido();

                        break;

                    case 9:
                        alterarTipoCliente();

                        break;

                    case 10:
                        alterarCategoriaJogo();

                        break;

                    case 0:
                        break;
                }

        } while (!opcao.equals("0"));

        daoJogo.salvarTodos(listaJogo);
        daoCliente.salvarTodos(listaCliente);
        System.out.println("Sistema encerrado!");

    }

    public static double valorMensalVendido() { // Valor do mes atual
        int mesReferencia = LocalDate.now().getMonthValue();
        int anoReferencia = LocalDate.now().getYear();

        return listaCliente.stream()
                .mapToDouble(
                        c -> (c).getCompras().stream().filter(m -> m.getDataCompra().getMonthValue() == mesReferencia
                                && m.getDataCompra().getYear() == anoReferencia)
                                .mapToDouble(Compra::getValorPago).sum())
                .sum();
    }

    public static double valorMedioDasCompras() { // Valor das vendas totais
        double aux = 0.0;
        if (listaCliente.size() != 0)
            aux = listaCliente.stream().mapToDouble(c -> (c).getCompras().stream()
                    .mapToDouble(Compra::getValorPago).sum()).average().getAsDouble();
        return aux;
    }

    public static void jogoMaisVendido() {
        if (listaJogo.size() != 0) {

            int maisComprado = listaJogo.stream()
                    .mapToInt(j -> j.getNumComprados()).max().getAsInt();

            for (Jogo jogo : listaJogo) {
                if (jogo.getNumComprados() == maisComprado) {
                    System.out
                            .println("O Jogo: " + jogo.getTitulo() + " foi comprado: " + jogo.getNumComprados()
                                    + " vezes");
                }
            }
        } else {
            System.out.println("Não existe nenhum jogo!");
        }

    }

    public static void jogoMenosVendido() {
        if (listaJogo.size() != 0) {

            int maisComprado = listaJogo.stream()
                    .mapToInt(j -> j.getNumComprados()).min().getAsInt();

            for (Jogo jogo : listaJogo) {
                if (jogo.getNumComprados() == maisComprado) {
                    System.out
                            .println("O Jogo: " + jogo.getTitulo() + " foi comprado: " + jogo.getNumComprados()
                                    + " vezes");
                }
            }
        } else {
            System.out.println("Não existe nenhum jogo!");
        }
    }

    public static void cadastrarCliente() {

        sc.nextLine();

        System.out.println("Informe qual o tipo do cliente: ");
        String tipo = sc.nextLine();
        try {
            Cliente cliente = ClienteFactory.creator(tipo);

            System.out.println("Informe o seu nome: ");
            String nome = sc.nextLine();

            boolean validador = false;
            String nomeDeUsuario;
            do {
                System.out.println("Informe o seu nome de usuário: ");
                nomeDeUsuario = sc.nextLine();

                for (Cliente clientes : listaCliente) {
                    if (nomeDeUsuario.equals(clientes.getNomeDeUsuario())) {
                        System.out.println("\nUsuário já cadastrado\n");
                        validador = true;
                        break;
                    } else
                        validador = false;                                                                                                                                                         
                }
            } while (validador);
            validador = false;

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
                while (!isValidEmailAddressRegex(email)) { // Validar se o email é valido
                    System.out.println("Informe seu email: ");
                    email = sc.nextLine();
                }
                for (Cliente clientes : listaCliente) { // Validar se o email ja existe
                    if (email.equals(clientes.getEmail())) {
                        System.out.println("\nEmail já cadastrado\n");
                        validador = true;
                        break;
                    } else
                        validador = false;
                }
            } while (validador);
            validador = false;
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
        boolean validador = false;
        String titulo;

        System.out.println("Informe a seguir a categoria do jogo: ");
        String categoria = sc.nextLine();
        try {
            Jogo jogo = JogoFactory.creator(categoria);
            do {
                System.out.println("Informe o titulo do jogo ");
                titulo = sc.nextLine();

                for (Jogo jogos : listaJogo) {
                    if (titulo.equals(jogos.getTitulo())) {
                        System.out.println("\nJogo já cadastrado\n");
                        validador = true;
                        break;
                    } else
                        validador = false;
                }
            } while (validador);
            validador = false;

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

            System.out.println("Informe o desconto do jogo: ");
            double desconto = sc.nextDouble();

            jogo.setTitulo(titulo);
            jogo.setPrecoBase(preco);
            jogo.setGenero(genero);
            jogo.setClassificacaoIndicativa(classificacaoIndicativa);
            jogo.setProdutora(produtora);
            jogo.setDesconto(desconto);
            listaJogo.add(jogo);
        } catch (TipoInvalidoExcecao e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void cadastrarCompra() {

        Cliente cliente = encontrarCliente();
        if (cliente == null) {
            System.out.println("Cliente não encontrado");
            return;
        }

        System.out.println("Qual o meio de pagamento? : ");
        String meioPagamento = sc.nextLine();

        Compra compra = new Compra(meioPagamento);

        String s; // Apenas continua ao pressionar s
        do {
            addJogo(compra);
            System.out.println("Gostaria de adicionar outro jogo? s/n");
            s = sc.nextLine();
        } while (s.equals("s"));

        cliente.addCompras(compra); // Após adicionar todos os jogos, método addCompra calcula os descontos.

        System.out.println("Compra finalizada. Valor total: R$" + cliente.calcularValorCompras(compra));
    }

    public static void historicoDoCliente() {

        Cliente cliente = encontrarCliente();
        if (cliente == null) {
            System.out.println("Cliente não encontrado");
            return;
        }
        System.out.println(cliente.getExtrato());
    }

    public static void alterarTipoCliente() {

        Cliente cliente = encontrarCliente();
        if (cliente == null) {
            System.out.println("Cliente não encontrado");
            return;
        }
        int posicao = listaCliente.indexOf(cliente);
        System.out.println("Qual o novo tipo será atribuido ao cliente?");
        String tipo = sc.nextLine();
        try {
            cliente = cliente.mudarTipo(tipo);
            listaCliente.remove(posicao);
            listaCliente.add(posicao, cliente);
        } catch (TipoInvalidoExcecao e) {
            System.out.println(e.getMessage());
        }
    }

    public static void alterarCategoriaJogo() {
        sc.nextLine();
        Jogo jogo = encontrarJogo();

        if (jogo == null) {
            System.out.println("Jogo não encontrado");
            return;
        }
        int posicao = listaJogo.indexOf(jogo);
        System.out.println("Qual a nova categoria que será atribuida ao jogo?");
        String categoria = sc.nextLine();
        try {
            jogo = jogo.mudarCategoria(categoria);
            System.out.println("Qual o novo desconto a ser aplicado?");
            double desconto = sc.nextDouble();
            jogo.setDesconto(desconto);
            listaJogo.remove(posicao);
            listaJogo.add(posicao, jogo);
        } catch (TipoInvalidoExcecao e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
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

    public static Cliente encontrarCliente() {
        sc.nextLine();
        System.out.println("Qual o nome do usuário: ");
        String nomeUsuario = sc.nextLine();

        Cliente cliente = null;
        for (Cliente iCliente : listaCliente) {
            if (iCliente.getNome().equals(nomeUsuario)) {
                cliente = iCliente;
                break; // parar de percorrer ao encontrar
            }
        }
        return cliente;
    }

    public static boolean isValidPassword(String senha) {

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

    public static void senhaInvalida() {
        System.out.println("\nFavor criar uma senha que respeita as sequintes regras:");
        System.out.println();
        System.out.println("conter entre 8 e 20 caracteres.");
        System.out.println("Conter pelo menos um dígito.");
        System.out.println("Conter pelo menos um alfabeto maiúsculo.");
        System.out.println("Conter pelo menos um alfabeto minúsculo.");
        System.out.println("Conter pelo menos um caractere especial que inclui ! @ # $% & *() - + = ^ .");
        System.out.println("Não conter nenhum espaço em branco.\n");
    }

    public static void addJogo(Compra compra) {

        Jogo jogo = encontrarJogo();
        if (jogo != null) {
            compra.adicionarJogo(jogo);
        } else
            System.out.println("Jogo não encontrado");
    }

    public static Jogo encontrarJogo() {
        Jogo jogo = null;
        System.out.println("Qual o titulo do jogo: ");
        String tituloJogo = sc.nextLine();

        for (Jogo jogos : listaJogo) {
            if (jogos.getTitulo().equals(tituloJogo)) {
                jogo = jogos;
                break;
            }
        }
        return jogo;
    }

    public static boolean validarOpcao(String string) {
        boolean ehValida = false;
        String expression = "[0-9]+";

        if (string.matches(expression)) {
            ehValida = true;
        } else
            System.out.println("\nFavor inserir uma opção entre 0 e 10!\n");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ehValida;

    }
}
