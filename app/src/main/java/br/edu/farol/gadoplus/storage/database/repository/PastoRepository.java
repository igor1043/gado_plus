package br.edu.farol.gadoplus.storage.database.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import br.edu.farol.gadoplus.model.Pasto;
import br.edu.farol.gadoplus.storage.database.AppDatabase;
import br.edu.farol.gadoplus.storage.database.dao.PastoDao;

public class PastoRepository {
    private PastoDao pastoDao;
    private LiveData<List<Pasto>> allPasto;

    public PastoRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        pastoDao = database.pastoDao();
        allPasto = pastoDao.getAll();
    }

    public void insert(Pasto pasto) {
        new InsertPastoAsyncTask(pastoDao).execute(pasto);
    }

    public void update(Pasto pasto) {
        new UpdatePastoAsyncTask(pastoDao).execute(pasto);
    }

    public void delete(Pasto pasto) {
        new DeletePastoAsyncTask(pastoDao).execute(pasto);
    }

    public void deleteAll() {
        new DeleteAllPastoAsyncTask(pastoDao).execute();
    }

    public LiveData<List<Pasto>> getAll() {
        return allPasto;
    }

    public Pasto getById(int id){ return pastoDao.getById(id);}

    private static class InsertPastoAsyncTask extends AsyncTask<Pasto, Void, Void> {
        private PastoDao pastoDao;

        private InsertPastoAsyncTask(PastoDao pastoDao) {
            this.pastoDao = pastoDao;
        }

        @Override
        protected Void doInBackground(Pasto... pasto) {
            pastoDao.insert(pasto[0]);
            return null;
        }

    }

    private static class UpdatePastoAsyncTask extends AsyncTask<Pasto, Void, Void> {
        private PastoDao pastoDao;

        private UpdatePastoAsyncTask(PastoDao pastoDao) {
            this.pastoDao = pastoDao;
        }

        @Override
        protected Void doInBackground(Pasto... pasto) {
            pastoDao.update(pasto[0]);
            return null;
        }
    }

    private static class DeletePastoAsyncTask extends AsyncTask<Pasto, Void, Void> {
        private PastoDao pastoDao;

        private DeletePastoAsyncTask(PastoDao pastoDao) {
            this.pastoDao = pastoDao;
        }

        @Override
        protected Void doInBackground(Pasto... pasto) {
            pastoDao.delete(pasto[0]);
            return null;
        }
    }

    private static class DeleteAllPastoAsyncTask extends AsyncTask<Void, Void, Void> {
        private PastoDao pastoDao;

        private DeleteAllPastoAsyncTask(PastoDao pastoDao) {
            this.pastoDao = pastoDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            pastoDao.deleteAll();
            return null;
        }
    }
}
