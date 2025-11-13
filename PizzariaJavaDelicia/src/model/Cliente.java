package model;

public class Cliente {
    private String nome;
    private String telefone;
    private boolean vip;

    public Cliente(String nome, String telefone, boolean vip) {
        this.nome = nome;
        this.telefone = telefone;
        this.vip = vip;
    }

    // Getters
    public String getNome() { return nome; }
    public String getTelefone() { return telefone; }
    public boolean isVip() { return vip; }

    @Override
    public String toString() {
        return nome + (vip ? " (VIP)" : "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return telefone.equals(cliente.telefone);
    }

    @Override
    public int hashCode() {
        return telefone.hashCode();
    }
}