package br.edu.farol.gadoplus.storage.database.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import br.edu.farol.gadoplus.model.Propriedade;
import br.edu.farol.gadoplus.storage.database.AppDatabase;
import br.edu.farol.gadoplus.storage.database.dao.PropriedadeDao;

public class PropriedadeRepository {
    private PropriedadeDao propriedadeDao;
    private LiveData<List<Propriedade>> allPropriedades;

    public PropriedadeRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        propriedadeDao = database.propriedadeDao();
        allPropriedades = propriedadeDao.getAll();
    }

    public void insert(Propriedade propriedade) {
        new InsertPropriedadeAsyncTask(propriedadeDao).execute(propriedade);
    }

    public void update(Propriedade propriedade) {
        new UpdatePropriedadeAsyncTask(propriedadeDao).execute(propriedade);
    }

    public void delete(Propriedade propriedade) {
        new DeletePropriedadeAsyncTask(propriedadeDao).execute(propriedade);
    }

    public void deleteAll() {
        new DeleteAllPropriedadesAsyncTask(propriedadeDao).execute();
    }

    public LiveData<List<Propriedade>> getAll() {
        return allPropriedades;
    }

    public Propriedade getById(int id){ return propriedadeDao.getById(id);}

    private static class InsertPropriedadeAsyncTask extends AsyncTask<Propriedade, Void, Void> {
        private PropriedadeDao propriedadeDao;

        private InsertPropriedadeAsyncTask(PropriedadeDao propriedadeDao) {
            this.propriedadeDao = propriedadeDao;
        }

        @Override
        protected Void doInBackground(Propriedade... propriedade) {
            propriedadeDao.insert(propriedade[0]);
            return null;
        }
    }

    private static class UpdatePropriedadeAsyncTask extends AsyncTask<Propriedade, Void, Void> {
        private PropriedadeDao propriedadeDao;

        private UpdatePropriedadeAsyncTask(PropriedadeDao propriedadeDao) {
            this.propriedadeDao = propriedadeDao;
        }

        @Override
        protected Void doInBackground(Propriedade... propriedades) {
            propriedadeDao.update(propriedades[0]);
            return null;
        }
    }

    private static class DeletePropriedadeAsyncTask extends AsyncTask<Propriedade, Void, Void> {
        private PropriedadeDao propriedadeDao;

        private DeletePropriedadeAsyncTask(PropriedadeDao propriedadeDao) {
            this.propriedadeDao = propriedadeDao;
        }

        @Override
        protected Void doInBackground(Propriedade... propriedades) {
            propriedadeDao.delete(propriedades[0]);
            return null;
        }
    }

    private static class DeleteAllPropriedadesAsyncTask extends AsyncTask<Void, Void, Void> {
        private PropriedadeDao propriedadeDao;

        private DeleteAllPropriedadesAsyncTask(PropriedadeDao propriedadeDao) {
            this.propriedadeDao = propriedadeDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            propriedadeDao.deleteAll();
            return null;
        }
    }
}
