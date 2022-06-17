package aplicacao;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import cliente.Cliente;
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

    // private Map<Cliente> clientes;
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
		return null;
	}

	public Jogo jogoMenosVendido() {
		return null;
	}
}

