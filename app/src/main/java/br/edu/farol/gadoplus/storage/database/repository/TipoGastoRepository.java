package br.edu.farol.gadoplus.storage.database.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import br.edu.farol.gadoplus.model.TipoGasto;
import br.edu.farol.gadoplus.storage.database.AppDatabase;
import br.edu.farol.gadoplus.storage.database.dao.TipoGastoDao;

public class TipoGastoRepository {
    private TipoGastoDao tipoGastoDao;
    private LiveData<List<TipoGasto>> allTipoGasto;

    public TipoGastoRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        tipoGastoDao = database.tipoGastoDao();
        allTipoGasto = tipoGastoDao.getAll();
    }

    public void insert(TipoGasto tipoGasto) {
        new InsertTipoGastoAsyncTask(tipoGastoDao).execute(tipoGasto);
    }

    public void update(TipoGasto tipoGasto) {
        new UpdateTipoGastoAsyncTask(tipoGastoDao).execute(tipoGasto);
    }

    public void delete(TipoGasto tipoGasto) {
        new DeleteTipoGastoAsyncTask(tipoGastoDao).execute(tipoGasto);
    }

    public void deleteAll() {
        new DeleteAllTipoGastoAsyncTask(tipoGastoDao).execute();
    }

    public LiveData<List<TipoGasto>> getAll() {
        return allTipoGasto;
    }

    public TipoGasto getById(int id){ return tipoGastoDao.getById(id);}

    private static class InsertTipoGastoAsyncTask extends AsyncTask<TipoGasto, Void, Void> {
        private TipoGastoDao tipoGastoDao;

        private InsertTipoGastoAsyncTask(TipoGastoDao tipoGastoDao) {
            this.tipoGastoDao = tipoGastoDao;
        }

        @Override
        protected Void doInBackground(TipoGasto... tipoGasto) {
            tipoGastoDao.insert(tipoGasto[0]);
            return null;
        }
    }

    private static class UpdateTipoGastoAsyncTask extends AsyncTask<TipoGasto, Void, Void> {
        private TipoGastoDao tipoGastoDao;

        private UpdateTipoGastoAsyncTask(TipoGastoDao tipoGastoDao) {
            this.tipoGastoDao = tipoGastoDao;
        }

        @Override
        protected Void doInBackground(TipoGasto... propriedades) {
            tipoGastoDao.update(propriedades[0]);
            return null;
        }
    }

    private static class DeleteTipoGastoAsyncTask extends AsyncTask<TipoGasto, Void, Void> {
        private TipoGastoDao tipoGastoDao;

        private DeleteTipoGastoAsyncTask(TipoGastoDao tipoGastoDao) {
            this.tipoGastoDao = tipoGastoDao;
        }

        @Override
        protected Void doInBackground(TipoGasto... propriedades) {
            tipoGastoDao.delete(propriedades[0]);
            return null;
        }
    }

    private static class DeleteAllTipoGastoAsyncTask extends AsyncTask<Void, Void, Void> {
        private TipoGastoDao tipoGastoDao;

        private DeleteAllTipoGastoAsyncTask(TipoGastoDao tipoGastoDao) {
            this.tipoGastoDao = tipoGastoDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            tipoGastoDao.deleteAll();
            return null;
        }
    }
}
