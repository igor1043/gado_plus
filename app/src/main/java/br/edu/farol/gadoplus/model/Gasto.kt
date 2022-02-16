package br.edu.farol.gadoplus.model

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore

@Entity(tableName = "gasto")
class Gasto {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "tipo_gasto_id")
    var tipoGastoId = 0

    @ColumnInfo(name = "animal_id")
    var animalId = 0

    @ColumnInfo(name = "data")
    var data: String? = null

    @ColumnInfo(name = "valor")
    var valor = 0.0

    @ColumnInfo(name = "descricao")
    var descricao: String? = null

    constructor(tipoGastoId: Int, animalId: Int, data: String?, valor: Double, descricao: String?) {
        this.tipoGastoId = tipoGastoId
        this.animalId = animalId
        this.data = data
        this.valor = valor
        this.descricao = descricao
    }

    @Ignore
    constructor() {
    }
}