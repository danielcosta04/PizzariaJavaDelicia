package model;

import java.util.Comparator;
import java.util.Random;

public class Pedido implements Comparable<Pedido> {
    private int numero;
    private Cliente cliente;
    private String sabor;
    private char tamanho; // P, M, G
    private double valor;
    private int tempoPreparo; // minutos

    public Pedido(int numero, Cliente cliente, String sabor, char tamanho, double valor) {
        this.numero = numero;
        this.cliente = cliente;
        this.sabor = sabor;
        this.tamanho = tamanho;
        this.valor = valor;
        this.tempoPreparo = calcularTempoPreparo(tamanho);
    }

    private int calcularTempoPreparo(char tamanho) {
        Random r = new Random();
        return switch (tamanho) {
            case 'P' -> 15 + r.nextInt(6);
            case 'M' -> 20 + r.nextInt(6);
            case 'G' -> 25 + r.nextInt(6);
            default -> 20;
        };
    }

    // Getters
    public int getNumero() { return numero; }
    public Cliente getCliente() { return cliente; }
    public String getSabor() { return sabor; }
    public char getTamanho() { return tamanho; }
    public double getValor() { return valor; }
    public int getTempoPreparo() { return tempoPreparo; }

    @Override
    public String toString() {
        return String.format("#%d | %s | %s (%c) | R$ %.2f | %d min",
                numero, cliente, sabor, tamanho, valor, tempoPreparo);
    }

    @Override
    public int compareTo(Pedido o) {
        return Integer.compare(this.tempoPreparo, o.tempoPreparo);
    }

    // Comparators
    public static Comparator<Pedido> porValor() {
        return Comparator.comparingDouble(Pedido::getValor);
    }

    public static Comparator<Pedido> porNomeCliente() {
        return Comparator.comparing(p -> p.getCliente().getNome());
    }
}