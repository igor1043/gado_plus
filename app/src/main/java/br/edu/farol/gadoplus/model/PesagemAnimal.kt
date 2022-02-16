package br.edu.farol.gadoplus.model

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore

@Entity(tableName = "pesagem_animal")
class PesagemAnimal {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "pesagem_id")
    var pesagemId = 0

    @ColumnInfo(name = "animal_id")
    var animalId = 0

    @ColumnInfo(name = "peso")
    var peso = 0.0

    constructor(pesagemId: Int, animalId: Int, peso: Double) {
        this.pesagemId = pesagemId
        this.animalId = animalId
        this.peso = peso
    }

    @Ignore
    constructor() {
    }
}