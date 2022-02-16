package br.edu.farol.gadoplus.storage.database

import br.edu.farol.gadoplus.storage.database.Schema.BaseTable

object Schema {
    const val CREATE_TABLE_PROPRIEDADE = "CREATE TABLE " + Propriedade.TABLE + " (" +
            BaseTable.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Propriedade.NOME + " VARCHAR (20), " +
            Propriedade.HECTARES + " DOUBLE DEFAULT (0), " +
            Propriedade.DESCRICAO + " TEXT (200), " +
            BaseTable.ATIVO + " INTEGER DEFAULT (1));"

    open class BaseTable {
        companion object {
            const val ID = "id"
            const val ATIVO = "ativo"
        }
    }

    internal object Propriedade : BaseTable() {
        const val TABLE = "propriedade"
        const val NOME = "nome"
        const val HECTARES = "hectares"
        const val DESCRICAO = "descricao"
    }
}