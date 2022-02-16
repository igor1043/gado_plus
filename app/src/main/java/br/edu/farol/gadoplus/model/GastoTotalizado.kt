package br.edu.farol.gadoplus.model

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

class GastoTotalizado {
    @ColumnInfo(name = "nome")
    var nome: String? = null

    @ColumnInfo(name = "valor")
    var valor = 0.0
}