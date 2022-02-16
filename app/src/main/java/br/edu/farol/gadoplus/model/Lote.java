package br.edu.farol.gadoplus.model;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "lote")
public class Lote{
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "nome")
    private String nome;

    @ColumnInfo(name = "propriedade_id")
    private int propriedadeId;

    @ColumnInfo(name = "descricao")
    private String descricao;

    public Lote(String nome, int propriedadeId, String descricao){
        this.nome = nome;
        this.propriedadeId = propriedadeId;
        this.descricao = descricao;
    }
    @Ignore
    public Lote(){}

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

    public int getPropriedadeId() {
        return propriedadeId;
    }

    public void setPropriedadeId(int propriedadeId) {
        this.propriedadeId = propriedadeId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String toString(){ return nome; }
}
