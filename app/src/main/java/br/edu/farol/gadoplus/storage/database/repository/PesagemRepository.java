package br.edu.farol.gadoplus.storage.database.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import br.edu.farol.gadoplus.model.Pesagem;
import br.edu.farol.gadoplus.storage.database.AppDatabase;
import br.edu.farol.gadoplus.storage.database.dao.PesagemDao;

public class PesagemRepository {
    private PesagemDao pesagemDao;
    private LiveData<List<Pesagem>> allPesagens;

    public PesagemRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        pesagemDao = database.pesagemDao();
        allPesagens = pesagemDao.getAll();
    }

    public void insert(Pesagem pesagem) {
        new InsertPesagemAsyncTask(pesagemDao).execute(pesagem);
    }

    public void update(Pesagem pesagem) {
        new UpdatePesagemAsyncTask(pesagemDao).execute(pesagem);
    }

    public void delete(Pesagem pesagem) {
        new DeletePesagemAsyncTask(pesagemDao).execute(pesagem);
    }

    public void deleteAll() {
        new DeleteAllPesagensAsyncTask(pesagemDao).execute();
    }

    public LiveData<List<Pesagem>> getAll() {
        return allPesagens;
    }

    public Pesagem getById(int id){ return pesagemDao.getById(id);}

    private static class InsertPesagemAsyncTask extends AsyncTask<Pesagem, Void, Void> {
        private PesagemDao pesagemDao;

        private InsertPesagemAsyncTask(PesagemDao pesagemDao) {
            this.pesagemDao = pesagemDao;
        }

        @Override
        protected Void doInBackground(Pesagem... pesagem) {
            pesagemDao.insert(pesagem[0]);
            return null;
        }
    }

    private static class UpdatePesagemAsyncTask extends AsyncTask<Pesagem, Void, Void> {
        private PesagemDao pesagemDao;

        private UpdatePesagemAsyncTask(PesagemDao pesagemDao) {
            this.pesagemDao = pesagemDao;
        }

        @Override
        protected Void doInBackground(Pesagem... pesagens) {
            pesagemDao.update(pesagens[0]);
            return null;
        }
    }

    private static class DeletePesagemAsyncTask extends AsyncTask<Pesagem, Void, Void> {
        private PesagemDao pesagemDao;

        private DeletePesagemAsyncTask(PesagemDao pesagemDao) {
            this.pesagemDao = pesagemDao;
        }

        @Override
        protected Void doInBackground(Pesagem... pesagens) {
            pesagemDao.delete(pesagens[0]);
            return null;
        }
    }

    private static class DeleteAllPesagensAsyncTask extends AsyncTask<Void, Void, Void> {
        private PesagemDao pesagemDao;

        private DeleteAllPesagensAsyncTask(PesagemDao pesagemDao) {
            this.pesagemDao = pesagemDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            pesagemDao.deleteAll();
            return null;
        }
    }
}
