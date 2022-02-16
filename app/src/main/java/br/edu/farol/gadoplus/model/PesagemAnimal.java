package br.edu.farol.gadoplus.model;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "pesagem_animal")
public class PesagemAnimal {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "pesagem_id")
    private int pesagemId;

    @ColumnInfo(name = "animal_id")
    private int animalId;

    @ColumnInfo(name = "peso")
    private double peso;

    public PesagemAnimal(int pesagemId, int animalId, double peso){
        this.pesagemId = pesagemId;
        this.animalId = animalId;
        this.peso = peso;
    }

    @Ignore
    public PesagemAnimal(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPesagemId() {
        return pesagemId;
    }

    public void setPesagemId(int pesagemId) {
        this.pesagemId = pesagemId;
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}
