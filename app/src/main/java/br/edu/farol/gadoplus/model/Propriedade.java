package br.edu.farol.gadoplus.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "propriedade")
public class Propriedade {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "nome")
    private String nome;
    @ColumnInfo(name = "hectares")
    private double hectares;
    @ColumnInfo(name = "descricao")
    private String descricao;

    @Ignore
    public Propriedade(String nome, double hectares, String descricao){
        setNome(nome);
        setHectares(hectares);
        setDescricao(descricao);
    }

    public Propriedade(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getHectares() {
        return hectares;
    }

    public void setHectares(double hectares) {
        this.hectares = hectares;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String toString(){ return nome; }
}
