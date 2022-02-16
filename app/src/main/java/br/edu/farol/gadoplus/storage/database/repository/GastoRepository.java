package br.edu.farol.gadoplus.storage.database.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import br.edu.farol.gadoplus.model.Gasto;
import br.edu.farol.gadoplus.model.GastoTotalizado;
import br.edu.farol.gadoplus.storage.database.AppDatabase;
import br.edu.farol.gadoplus.storage.database.dao.GastoDao;

public class GastoRepository {
    private GastoDao gastoDao;
    private LiveData<List<Gasto>> allGastos;

    public GastoRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        gastoDao = database.gastoDao();
        allGastos = gastoDao.getAll();
    }

    public void insert(Gasto gasto) {
        new InsertGastoAsyncTask(gastoDao).execute(gasto);
    }

    public void update(Gasto gasto) {
        new UpdateGastoAsyncTask(gastoDao).execute(gasto);
    }

    public void delete(Gasto gasto) {
        new DeleteGastoAsyncTask(gastoDao).execute(gasto);
    }

    public void deleteAll() {
        new DeleteAllGastosAsyncTask(gastoDao).execute();
    }

    public LiveData<List<Gasto>> getAll() {
        return allGastos;
    }

    public LiveData<List<GastoTotalizado>> getGastoToalizado() {
        return gastoDao.getGastoToalizado();
    }

    public Gasto getById(int id){ return gastoDao.getById(id);}

    private static class InsertGastoAsyncTask extends AsyncTask<Gasto, Void, Void> {
        private GastoDao gastoDao;

        private InsertGastoAsyncTask(GastoDao gastoDao) {
            this.gastoDao = gastoDao;
        }

        @Override
        protected Void doInBackground(Gasto... gasto) {
            gastoDao.insert(gasto[0]);
            return null;
        }
    }

    private static class UpdateGastoAsyncTask extends AsyncTask<Gasto, Void, Void> {
        private GastoDao gastoDao;

        private UpdateGastoAsyncTask(GastoDao gastoDao) {
            this.gastoDao = gastoDao;
        }

        @Override
        protected Void doInBackground(Gasto... gastos) {
            gastoDao.update(gastos[0]);
            return null;
        }
    }

    private static class DeleteGastoAsyncTask extends AsyncTask<Gasto, Void, Void> {
        private GastoDao gastoDao;

        private DeleteGastoAsyncTask(GastoDao gastoDao) {
            this.gastoDao = gastoDao;
        }

        @Override
        protected Void doInBackground(Gasto... gastos) {
            gastoDao.delete(gastos[0]);
            return null;
        }
    }

    private static class DeleteAllGastosAsyncTask extends AsyncTask<Void, Void, Void> {
        private GastoDao gastoDao;

        private DeleteAllGastosAsyncTask(GastoDao gastoDao) {
            this.gastoDao = gastoDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            gastoDao.deleteAll();
            return null;
        }
    }
}
