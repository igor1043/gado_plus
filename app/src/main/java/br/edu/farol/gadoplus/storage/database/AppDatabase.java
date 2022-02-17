package br.edu.farol.gadoplus.storage.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import br.edu.farol.gadoplus.model.Animal;
import br.edu.farol.gadoplus.model.Gasto;
import br.edu.farol.gadoplus.model.Lote;
import br.edu.farol.gadoplus.model.Pesagem;
import br.edu.farol.gadoplus.model.PesagemAnimal;
import br.edu.farol.gadoplus.model.Propriedade;
import br.edu.farol.gadoplus.model.Raca;
import br.edu.farol.gadoplus.model.TipoGasto;
import br.edu.farol.gadoplus.storage.database.dao.AnimalDao;
import br.edu.farol.gadoplus.storage.database.dao.GastoDao;
import br.edu.farol.gadoplus.storage.database.dao.LoteDao;
import br.edu.farol.gadoplus.storage.database.dao.PesagemAnimalDao;
import br.edu.farol.gadoplus.storage.database.dao.PesagemDao;
import br.edu.farol.gadoplus.storage.database.dao.PropriedadeDao;
import br.edu.farol.gadoplus.storage.database.dao.RacaDao;
import br.edu.farol.gadoplus.storage.database.dao.TipoGastoDao;


@Database(
        entities = {
                Animal.class,
                Gasto.class,
                Lote.class,
                Pesagem.class,
                PesagemAnimal.class,
                Propriedade.class,
                Raca.class,
                TipoGasto.class
        },
        version = 4,
        exportSchema = false
)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract AnimalDao animalDao();
    public abstract GastoDao gastoDao();
    public abstract LoteDao loteDao();
    public abstract PesagemDao pesagemDao();
    public abstract PesagemAnimalDao pesagemAnimalDao();
    public abstract PropriedadeDao propriedadeDao();
    public abstract RacaDao racaDao();
    public abstract TipoGastoDao tipoGastoDao();


    public static final String DATABSE_NAME = "database.db";

    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null)
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABSE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(roomCallback)
                    .build();
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private RacaDao racaDao;
        private TipoGastoDao tipoGasto;

        private PopulateDbAsyncTask(AppDatabase db) {
            racaDao = db.racaDao();
            tipoGasto = db.tipoGastoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            racaDao.insert(new Raca("Nelore"));
            racaDao.insert(new Raca("Angus"));
            racaDao.insert(new Raca("Brahman"));
            racaDao.insert(new Raca("Brangus"));
            racaDao.insert(new Raca("Tabapuã"));
            racaDao.insert(new Raca("Senepol"));
            racaDao.insert(new Raca("Hereford"));

            tipoGasto.insert(new TipoGasto("Aquisição de animais"));
            tipoGasto.insert(new TipoGasto("Alimentação"));
            tipoGasto.insert(new TipoGasto("Mão-de-obra"));
            tipoGasto.insert(new TipoGasto("Sanidade"));
            tipoGasto.insert(new TipoGasto("Impostos"));
            tipoGasto.insert(new TipoGasto("Depreciação"));
            tipoGasto.insert(new TipoGasto("Despesas diversas"));


            return null;
        }
    }
}

