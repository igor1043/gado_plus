package br.edu.farol.gadoplus.model

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore

@Entity(tableName = "pesagem")
class Pesagem {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "lote_id")
    var loteId = 0

    @ColumnInfo(name = "data")
    var data: String? = null

    @ColumnInfo(name = "descricao")
    var descricao: String? = null

    constructor(loteId: Int, data: String?, descricao: String?) {
        this.loteId = loteId
        this.data = data
        this.descricao = descricao
    }

    @Ignore
    constructor() {
    }
}