package br.edu.farol.gadoplus.storage.database.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import br.edu.farol.gadoplus.model.Lote;
import br.edu.farol.gadoplus.storage.database.AppDatabase;
import br.edu.farol.gadoplus.storage.database.dao.LoteDao;

public class LoteRepository {
    private LoteDao loteDao;
    private LiveData<List<Lote>> allLotes;

    public LoteRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        loteDao = database.loteDao();
        allLotes = loteDao.getAll();
    }

    public void insert(Lote lote) {
        new InsertLoteAsyncTask(loteDao).execute(lote);
    }

    public void update(Lote lote) {
        new UpdateLoteAsyncTask(loteDao).execute(lote);
    }

    public void delete(Lote lote) {
        new DeleteLoteAsyncTask(loteDao).execute(lote);
    }

    public void deleteAll() {
        new DeleteAllLotesAsyncTask(loteDao).execute();
    }

    public LiveData<List<Lote>> getAll() {
        return allLotes;
    }

    public Lote getById(int id){ return loteDao.getById(id);}

    private static class InsertLoteAsyncTask extends AsyncTask<Lote, Void, Void> {
        private LoteDao loteDao;

        private InsertLoteAsyncTask(LoteDao loteDao) {
            this.loteDao = loteDao;
        }

        @Override
        protected Void doInBackground(Lote... lote) {
            loteDao.insert(lote[0]);
            return null;
        }
    }

    private static class UpdateLoteAsyncTask extends AsyncTask<Lote, Void, Void> {
        private LoteDao loteDao;

        private UpdateLoteAsyncTask(LoteDao loteDao) {
            this.loteDao = loteDao;
        }

        @Override
        protected Void doInBackground(Lote... lotes) {
            loteDao.update(lotes[0]);
            return null;
        }
    }

    private static class DeleteLoteAsyncTask extends AsyncTask<Lote, Void, Void> {
        private LoteDao loteDao;

        private DeleteLoteAsyncTask(LoteDao loteDao) {
            this.loteDao = loteDao;
        }

        @Override
        protected Void doInBackground(Lote... lotes) {
            loteDao.delete(lotes[0]);
            return null;
        }
    }

    private static class DeleteAllLotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private LoteDao loteDao;

        private DeleteAllLotesAsyncTask(LoteDao loteDao) {
            this.loteDao = loteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            loteDao.deleteAll();
            return null;
        }
    }
}
