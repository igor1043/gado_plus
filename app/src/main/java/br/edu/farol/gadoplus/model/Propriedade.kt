package br.edu.farol.gadoplus.model

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore

@Entity(tableName = "propriedade")
class Propriedade {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "nome")
    var nome: String? = null

    @ColumnInfo(name = "hectares")
    var hectares = 0.0

    @ColumnInfo(name = "descricao")
    var descricao: String? = null

    @Ignore
    constructor(nome: String?, hectares: Double, descricao: String?) {
        this.nome = nome
        this.hectares = hectares
        this.descricao = descricao
    }

    constructor() {}

    override fun toString(): String {
        return nome!!
    }
}