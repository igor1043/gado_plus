package br.edu.farol.gadoplus.storage.database.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import br.edu.farol.gadoplus.model.Raca;
import br.edu.farol.gadoplus.storage.database.AppDatabase;
import br.edu.farol.gadoplus.storage.database.dao.RacaDao;

public class RacaRepository {
    private RacaDao racaDao;
    private LiveData<List<Raca>> allRacas;

    public RacaRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        racaDao = database.racaDao();
        allRacas = racaDao.getAll();
    }

    public void insert(Raca raca) {
        new InsertRacaAsyncTask(racaDao).execute(raca);
    }

    public void update(Raca raca) {
        new UpdateRacaAsyncTask(racaDao).execute(raca);
    }

    public void delete(Raca raca) {
        new DeleteRacaAsyncTask(racaDao).execute(raca);
    }

    public void deleteAll() {
        new DeleteAllRacasAsyncTask(racaDao).execute();
    }

    public LiveData<List<Raca>> getAll() {
        return allRacas;
    }

    public Raca getById(int id){ return racaDao.getById(id);}

    private static class InsertRacaAsyncTask extends AsyncTask<Raca, Void, Void> {
        private RacaDao racaDao;

        private InsertRacaAsyncTask(RacaDao racaDao) {
            this.racaDao = racaDao;
        }

        @Override
        protected Void doInBackground(Raca... raca) {
            racaDao.insert(raca[0]);
            return null;
        }
    }

    private static class UpdateRacaAsyncTask extends AsyncTask<Raca, Void, Void> {
        private RacaDao racaDao;

        private UpdateRacaAsyncTask(RacaDao racaDao) {
            this.racaDao = racaDao;
        }

        @Override
        protected Void doInBackground(Raca... propriedades) {
            racaDao.update(propriedades[0]);
            return null;
        }
    }

    private static class DeleteRacaAsyncTask extends AsyncTask<Raca, Void, Void> {
        private RacaDao racaDao;

        private DeleteRacaAsyncTask(RacaDao racaDao) {
            this.racaDao = racaDao;
        }

        @Override
        protected Void doInBackground(Raca... propriedades) {
            racaDao.delete(propriedades[0]);
            return null;
        }
    }

    private static class DeleteAllRacasAsyncTask extends AsyncTask<Void, Void, Void> {
        private RacaDao racaDao;

        private DeleteAllRacasAsyncTask(RacaDao racaDao) {
            this.racaDao = racaDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            racaDao.deleteAll();
            return null;
        }
    }
}
