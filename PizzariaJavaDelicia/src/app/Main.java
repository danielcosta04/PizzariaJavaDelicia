package app;

import model.Cliente;
import model.Pedido;
import service.PizzariaService;

import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final PizzariaService pizzaria = new PizzariaService();
    private static int proximoNumero = 1;

    public static void main(String[] args) {
        // Sabores iniciais
        pizzaria.adicionarSabor("Calabresa");
        pizzaria.adicionarSabor("Mussarela");
        pizzaria.adicionarSabor("Frango com Catupiry");
        pizzaria.adicionarSabor("Margheritta");

        int op;
        do {
            menu();
            op = lerInt();
            executar(op);
        } while (op != 0);
        sc.close();
    }

    private static void menu() {
        System.out.println("\n" + "=".repeat(45));
        System.out.println("   PIZZARIA JAVA DELÍCIA");
        System.out.println("=".repeat(45));
        System.out.println("1.  Adicionar Sabor");
        System.out.println("2.  Listar Sabores");
        System.out.println("3.  Fazer Pedido");
        System.out.println("4.  Listar Pedidos");
        System.out.println("5.  Buscar Pedido");
        System.out.println("6.  Enviar para Fila");
        System.out.println("7.  Entregar Próximo");
        System.out.println("8.  Ver Próximo");
        System.out.println("9.  Ordenar por Valor");
        System.out.println("10. Ordenar por Cliente");
        System.out.println("11. Cancelar Pedido");
        System.out.println("12. Desfazer Cancelamento");
        System.out.println("13. Ranking de Sabores");
        System.out.println("14. Vendas por Sabor");
        System.out.println("0.  Sair");
        System.out.print("→ ");
    }

    private static void executar(int op) {
        switch (op) {
            case 1 -> adicionarSabor();
            case 2 -> pizzaria.listarSabores();
            case 3 -> fazerPedido();
            case 4 -> pizzaria.listarPedidos();
            case 5 -> buscarPedido();
            case 6 -> enviarParaFila();
            case 7 -> pizzaria.entregarProximo();
            case 8 -> pizzaria.verProximo();
            case 9 -> { pizzaria.ordenarPorValor(); pizzaria.listarPedidos(); }
            case 10 -> { pizzaria.ordenarPorCliente(); pizzaria.listarPedidos(); }
            case 11 -> cancelarPedido();
            case 12 -> pizzaria.desfazerCancelamento();
            case 13 -> pizzaria.exibirRanking();
            case 14 -> consultarVendas();
            case 0 -> System.out.println("Até logo!");
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void adicionarSabor() {
        System.out.print("Sabor: ");
        String s = sc.nextLine();
        System.out.println(pizzaria.adicionarSabor(s) ? "Adicionado." : "Já existe.");
    }

    private static void fazerPedido() {
        System.out.print("Nome cliente: ");
        String nome = sc.nextLine();
        System.out.print("Telefone: ");
        String tel = sc.nextLine();
        System.out.print("VIP? (s/n): ");
        boolean vip = sc.nextLine().trim().equalsIgnoreCase("s");
        Cliente cliente = new Cliente(nome, tel, vip);

        System.out.print("Sabor: ");
        String sabor = sc.nextLine();
        System.out.print("Tamanho (P/M/G): ");
        char tam = sc.nextLine().toUpperCase().charAt(0);
        System.out.print("Valor R$: ");
        double valor = sc.nextDouble();
        sc.nextLine();

        Pedido p = new Pedido(proximoNumero++, cliente, sabor, tam, valor);
        pizzaria.adicionarPedido(p);
    }

    private static void buscarPedido() {
        System.out.print("Número do pedido: ");
        int num = lerInt();
        Pedido p = pizzaria.buscarPedido(num);
        System.out.println(p != null ? p : "Não encontrado.");
    }

    private static void enviarParaFila() {
        System.out.print("Número do pedido: ");
        int num = lerInt();
        Pedido p = pizzaria.buscarPedido(num);
        if (p != null) pizzaria.enviarParaFila(p);
        else System.out.println("Pedido não encontrado.");
    }

    private static void cancelarPedido() {
        System.out.print("Número do pedido: ");
        int num = lerInt();
        Pedido p = pizzaria.buscarPedido(num);
        if (p != null) pizzaria.cancelarPedido(p);
        else System.out.println("Pedido não encontrado.");
    }

    private static void consultarVendas() {
        System.out.print("Sabor: ");
        String s = sc.nextLine();
        System.out.println("Vendas: " + pizzaria.vendasDoSabor(s));
    }

    private static int lerInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Número inválido. Tente novamente: ");
            }
        }
    }
}