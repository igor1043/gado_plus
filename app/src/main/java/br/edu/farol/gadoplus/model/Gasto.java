package br.edu.farol.gadoplus.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "gasto")
public class Gasto {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "tipo_gasto_id")
    private int tipoGastoId;

    @ColumnInfo(name = "animal_id")
    private int animalId;

    @ColumnInfo(name = "data")
    private String data;

    @ColumnInfo(name = "valor")
    private double valor;

    @ColumnInfo(name = "descricao")
    private String descricao;

    public Gasto(int tipoGastoId, int animalId, String data, double valor, String descricao){
        this.tipoGastoId = tipoGastoId;
        this.animalId = animalId;
        this.data = data;
        this.valor = valor;
        this.descricao = descricao;
    }

    @Ignore
    public Gasto(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipoGastoId() {
        return tipoGastoId;
    }

    public void setTipoGastoId(int tipoGastoId) {
        this.tipoGastoId = tipoGastoId;
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
