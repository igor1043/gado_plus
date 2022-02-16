package br.edu.farol.gadoplus.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "pesagem")
public class Pesagem{
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "lote_id")
    private int loteId;

    @ColumnInfo(name = "data")
    private String data;

    @ColumnInfo(name = "descricao")
    private String descricao;

    public Pesagem(int loteId, String data, String descricao){
        this.loteId = loteId;
        this.data = data;
        this.descricao = descricao;
    }

    @Ignore
    public Pesagem(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLoteId() {
        return loteId;
    }

    public void setLoteId(int loteId) {
        this.loteId = loteId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}

