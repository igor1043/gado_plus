package br.edu.farol.gadoplus.model

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "raca")
class Raca(@field:ColumnInfo(name = "nome") var nome: String) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    override fun toString(): String {
        return nome
    }
}