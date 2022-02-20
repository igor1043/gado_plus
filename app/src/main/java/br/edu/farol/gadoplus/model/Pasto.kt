package br.edu.farol.gadoplus.model

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore

@Entity(tableName = "pasto")
class Pasto {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "nome")
    var nome: String? = null

    @ColumnInfo(name = "tipo_pasto")
    var tipoPasto: String? = null

    @ColumnInfo(name = "descricao")
    var descricao: String? = null

    constructor(nome: String?, tipoPasto: String?, descricao: String?) {
        this.nome = nome
        this.tipoPasto = tipoPasto
        this.descricao = descricao
    }

    @Ignore
    constructor() {
    }

    override fun toString(): String {
        return nome!!
    }
}