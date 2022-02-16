package br.edu.farol.gadoplus.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "animal")
class Animal {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "nome")
    var nome: String? = null

    @ColumnInfo(name = "lote_id")
    var loteId = 0

    @ColumnInfo(name = "sexo")
    var sexo: String? = null

    @ColumnInfo(name = "dt_entrada")
    var dtEntrada: String? = null

    @ColumnInfo(name = "dt_primeira_pesagem")
    var dtPrimeiraPesagem: String? = null

    @ColumnInfo(name = "primeiro_peso")
    var primeiroPeso = 0.0

    @ColumnInfo(name = "raca_id")
    var racaId = 0

    @ColumnInfo(name = "preco_compra")
    var precoCompra = 0.0

    @ColumnInfo(name = "dt_nascimento")
    var dtNascimento: String? = null

    @ColumnInfo(name = "dt_desmame")
    var dtDesmame: String? = null

    @ColumnInfo(name = "observacoes")
    var observacoes: String? = null

    constructor(nome: String?, loteId: Int, sexo: String?, dtEntrada: String?,
                dtPrimeiraPesagem: String?, primeiroPeso: Double, racaId: Int, precoCompra: Double,
                dtNascimento: String?, dtDesmame: String?, observacoes: String?) {
        this.nome = nome
        this.loteId = loteId
        this.sexo = sexo
        this.dtEntrada = dtEntrada
        this.dtPrimeiraPesagem = dtPrimeiraPesagem
        this.primeiroPeso = primeiroPeso
        this.racaId = racaId
        this.precoCompra = precoCompra
        this.dtNascimento = dtNascimento
        this.dtDesmame = dtDesmame
        this.observacoes = observacoes
    }

    @Ignore
    constructor() {
    }

    override fun toString(): String {
        return nome!!
    }
}