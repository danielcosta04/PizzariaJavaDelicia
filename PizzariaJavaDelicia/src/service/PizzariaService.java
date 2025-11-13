package service;

import model.Cliente;
import model.Pedido;

import java.util.*;

public class PizzariaService {

    // 1. Sabores
    private final Set<String> sabores = new HashSet<>();

    // 2. Pedidos abertos
    private final List<Pedido> pedidosAbertos = new ArrayList<>();

    // 3. Fila de entregas
    private final Queue<Pedido> filaEntregas = new LinkedList<>();

    // 4. Pedidos prioritários (menor tempo de preparo)
    private final PriorityQueue<Pedido> pedidosPrioritarios = new PriorityQueue<>();

    // 5. Histórico de cancelados
    private final Stack<Pedido> cancelados = new Stack<>();

    // 6. Vendas por sabor
    private final Map<String, Integer> vendasPorSabor = new HashMap<>();

    // ---------- SABORES ----------
    public boolean adicionarSabor(String sabor) {
        return sabores.add(sabor);
    }

    public boolean removerSabor(String sabor) {
        return sabores.remove(sabor);
    }

    public void listarSabores() {
        if (sabores.isEmpty()) {
            System.out.println("Nenhum sabor cadastrado.");
            return;
        }
        System.out.println("=== SABORES ===");
        sabores.forEach(System.out::println);
    }

    public boolean saborExiste(String sabor) {
        return sabores.contains(sabor);
    }

    // ---------- PEDIDOS ----------
    public void adicionarPedido(Pedido pedido) {
        if (!saborExiste(pedido.getSabor())) {
            System.out.println("Sabor não cadastrado: " + pedido.getSabor());
            return;
        }
        pedidosAbertos.add(pedido);
        System.out.println("Pedido #" + pedido.getNumero() + " registrado.");
    }

    public void listarPedidos() {
        if (pedidosAbertos.isEmpty()) {
            System.out.println("Nenhum pedido aberto.");
            return;
        }
        System.out.println("=== PEDIDOS ABERTOS ===");
        pedidosAbertos.forEach(System.out::println);
    }

    public Pedido buscarPedido(int numero) {
        for (Pedido p : pedidosAbertos) {
            if (p.getNumero() == numero) return p;
        }
        return null;
    }

    public void ordenarPorValor() {
        pedidosAbertos.sort(Pedido.porValor());
        System.out.println("Ordenado por valor.");
    }

    public void ordenarPorCliente() {
        pedidosAbertos.sort(Pedido.porNomeCliente());
        System.out.println("Ordenado por cliente.");
    }

    // ---------- FILA ----------
    public void enviarParaFila(Pedido pedido) {
        if (!pedidosAbertos.remove(pedido)) {
            System.out.println("Pedido não está na lista de abertos.");
            return;
        }
        if (pedido.getCliente().isVip()) {
            pedidosPrioritarios.add(pedido);
            System.out.println("VIP #" + pedido.getNumero() + " → fila prioritária.");
        } else {
            filaEntregas.offer(pedido);
            System.out.println("Pedido #" + pedido.getNumero() + " → fila normal.");
        }
    }

    public Pedido entregarProximo() {
        Pedido proximo = pedidosPrioritarios.isEmpty() ? filaEntregas.poll() : pedidosPrioritarios.poll();
        if (proximo != null) {
            registrarVenda(proximo.getSabor());
            System.out.println("ENTREGUE: " + proximo);
        } else {
            System.out.println("Fila vazia.");
        }
        return proximo;
    }

    public void verProximo() {
        Pedido p = !pedidosPrioritarios.isEmpty() ? pedidosPrioritarios.peek() : filaEntregas.peek();
        System.out.println(p != null ? "Próximo: " + p : "Fila vazia.");
    }

    public boolean filaVazia() {
        return pedidosPrioritarios.isEmpty() && filaEntregas.isEmpty();
    }

    // ---------- CANCELAMENTOS ----------
    public void cancelarPedido(Pedido pedido) {
        boolean removido = pedidosAbertos.remove(pedido) ||
                filaEntregas.remove(pedido) ||
                pedidosPrioritarios.remove(pedido);
        if (removido) {
            cancelados.push(pedido);
            System.out.println("CANCELADO: " + pedido);
        } else {
            System.out.println("Pedido não encontrado.");
        }
    }

    public void desfazerCancelamento() {
        if (cancelados.isEmpty()) {
            System.out.println("Nenhum cancelamento para desfazer.");
            return;
        }
        Pedido p = cancelados.pop();
        pedidosAbertos.add(p);
        System.out.println("DESFEITO: " + p);
    }

    public void verUltimoCancelado() {
        System.out.println(cancelados.isEmpty() ? "Nenhum cancelado." : "Último cancelado: " + cancelados.peek());
    }

    // ---------- VENDAS ----------
    private void registrarVenda(String sabor) {
        vendasPorSabor.merge(sabor, 1, Integer::sum);
    }

    public void exibirRanking() {
        if (vendasPorSabor.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
            return;
        }
        System.out.println("=== RANKING DE SABORES ===");
        vendasPorSabor.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
    }

    public int vendasDoSabor(String sabor) {
        return vendasPorSabor.getOrDefault(sabor, 0);
    }

    public void listarSaboresVendidos() {
        System.out.println(vendasPorSabor.isEmpty() ? "Nenhum sabor vendido." : "Vendidos: " + vendasPorSabor.keySet());
    }
}