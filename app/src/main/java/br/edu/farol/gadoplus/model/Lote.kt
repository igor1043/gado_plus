package br.edu.farol.gadoplus.model

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore

@Entity(tableName = "lote")
class Lote {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "nome")
    var nome: String? = null

    @ColumnInfo(name = "propriedade_id")
    var propriedadeId = 0

    @ColumnInfo(name = "descricao")
    var descricao: String? = null

    constructor(nome: String?, propriedadeId: Int, descricao: String?) {
        this.nome = nome
        this.propriedadeId = propriedadeId
        this.descricao = descricao
    }

    @Ignore
    constructor() {
    }

    override fun toString(): String {
        return nome!!
    }
}