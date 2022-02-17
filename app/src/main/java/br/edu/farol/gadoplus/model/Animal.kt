package br.edu.farol.gadoplus.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "animal")
class Animal {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "n_brinco_identificador")
    var nBrincoIdentificador: String? = null

    @ColumnInfo(name = "status")
    var status = 0

    @ColumnInfo(name = "sexo")
    var sexo: String? = null

    @ColumnInfo(name = "tipo")
    var tipo: String? = null

    @ColumnInfo(name = "raca_id")
    var racaId = 0

    @ColumnInfo(name = "lote_id")
    var loteId = 0

    @ColumnInfo(name = "repro_realizadas")
    var reproRealizadas = 0

    @ColumnInfo(name = "dt_compra")
    var dtCompra: String? = null

    @ColumnInfo(name = "origem")
    var origem: String? = null

    @ColumnInfo(name = "dt_nascimento")
    var dtNascimento: String? = null

    @ColumnInfo(name = "dt_entrada")
    var dtEntrada: String? = null

    @ColumnInfo(name = "dt_desmame")
    var dtDesmame: String? = null

    @ColumnInfo(name = "pasto_id")
    var pastoId = 0

    @ColumnInfo(name = "primeiro_peso")
    var primeiroPeso = 0.0

    @ColumnInfo(name = "preco_compra")
    var precoCompra = 0.0

    @ColumnInfo(name = "preco_venda")
    var precoVenda = 0.0

    @ColumnInfo(name = "n_brinco_pai")
    var nBrincoPai = 0

    @ColumnInfo(name = "n_brinco_mae")
    var nBrincoMae = 0

    @ColumnInfo(name = "motivo_morte")
    var motivoMorte = 0

    @ColumnInfo(name = "dt_morte")
    var dtMorte: String? = null

    @ColumnInfo(name = "id_inseminacao")
    var idInseminacao = 0

    @ColumnInfo(name = "id_movimentacao")
    var idMovimentacao = 0

    @ColumnInfo(name = "observacoes")
    var observacoes: String? = null

    constructor(nBrincoIdentificador : String?, loteId: Int, sexo: String?, dtEntrada: String?,
                primeiroPeso: Double, racaId: Int, precoCompra: Double,
                dtNascimento: String?, dtDesmame: String?, observacoes: String?) {
        this.nBrincoIdentificador = nBrincoIdentificador
        this.loteId = loteId
        this.sexo = sexo
        this.dtEntrada = dtEntrada
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
        return nBrincoIdentificador!!
    }
}