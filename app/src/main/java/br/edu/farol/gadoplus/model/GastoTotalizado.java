package br.edu.farol.gadoplus.model;

import androidx.room.ColumnInfo;

public class GastoTotalizado {
    @ColumnInfo(name = "nome")
    private String nome;

    @ColumnInfo(name = "valor")
    private double valor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
