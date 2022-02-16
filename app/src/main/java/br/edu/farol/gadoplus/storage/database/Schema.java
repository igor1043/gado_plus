package br.edu.farol.gadoplus.storage.database;

public class Schema {
    public static final String CREATE_TABLE_PROPRIEDADE = "CREATE TABLE " + Propriedade.TABLE + " (" +
            Propriedade.ID         +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Propriedade.NOME       + " VARCHAR (20), " +
            Propriedade.HECTARES   + " DOUBLE DEFAULT (0), " +
            Propriedade.DESCRICAO  + " TEXT (200), " +
            Propriedade.ATIVO      + " INTEGER DEFAULT (1));";



    private static class BaseTable {
        static final String ID = "id";
        static final String ATIVO = "ativo";
    }

    static class Propriedade extends BaseTable {
        static final String TABLE = "propriedade";
        static final String NOME = "nome";
        static final String HECTARES = "hectares";
        static final String DESCRICAO = "descricao";
    }
}
