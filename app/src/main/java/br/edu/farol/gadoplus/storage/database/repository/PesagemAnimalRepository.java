package br.edu.farol.gadoplus.storage.database.repository;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;
import br.edu.farol.gadoplus.model.PesagemAnimal;
import br.edu.farol.gadoplus.storage.database.AppDatabase;
import br.edu.farol.gadoplus.storage.database.dao.PesagemAnimalDao;

public class PesagemAnimalRepository {
    private PesagemAnimalDao pesagemAnimalDao;
    private LiveData<List<PesagemAnimal>> allPesagemAnimals;

    public PesagemAnimalRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        pesagemAnimalDao = database.pesagemAnimalDao();
        allPesagemAnimals = pesagemAnimalDao.getAll();
    }

    public void insert(PesagemAnimal pesagemAnimal) {
        new InsertPesagemAnimalAsyncTask(pesagemAnimalDao).execute(pesagemAnimal);
    }

    public void update(PesagemAnimal pesagemAnimal) {
        new UpdatePesagemAnimalAsyncTask(pesagemAnimalDao).execute(pesagemAnimal);
    }

    public void delete(PesagemAnimal pesagemAnimal) {
        new DeletePesagemAnimalAsyncTask(pesagemAnimalDao).execute(pesagemAnimal);
    }

    public void deleteAll() {
        new DeleteAllPesagemAnimalsAsyncTask(pesagemAnimalDao).execute();
    }

    public LiveData<List<PesagemAnimal>> getAll() {
        return allPesagemAnimals;
    }
    public LiveData<List<PesagemAnimal>> getAll(int idPesagem) {
        return pesagemAnimalDao.getAll(idPesagem);
    }

    public PesagemAnimal getById(int id){ return pesagemAnimalDao.getById(id);}

    private static class InsertPesagemAnimalAsyncTask extends AsyncTask<PesagemAnimal, Void, Void> {
        private PesagemAnimalDao pesagemAnimalDao;

        private InsertPesagemAnimalAsyncTask(PesagemAnimalDao pesagemAnimalDao) {
            this.pesagemAnimalDao = pesagemAnimalDao;
        }

        @Override
        protected Void doInBackground(PesagemAnimal... pesagemAnimal) {
            pesagemAnimalDao.insert(pesagemAnimal[0]);
            return null;
        }
    }

    private static class UpdatePesagemAnimalAsyncTask extends AsyncTask<PesagemAnimal, Void, Void> {
        private PesagemAnimalDao pesagemAnimalDao;

        private UpdatePesagemAnimalAsyncTask(PesagemAnimalDao pesagemAnimalDao) {
            this.pesagemAnimalDao = pesagemAnimalDao;
        }

        @Override
        protected Void doInBackground(PesagemAnimal... pesagemAnimals) {
            pesagemAnimalDao.update(pesagemAnimals[0]);
            return null;
        }
    }

    private static class DeletePesagemAnimalAsyncTask extends AsyncTask<PesagemAnimal, Void, Void> {
        private PesagemAnimalDao pesagemAnimalDao;

        private DeletePesagemAnimalAsyncTask(PesagemAnimalDao pesagemAnimalDao) {
            this.pesagemAnimalDao = pesagemAnimalDao;
        }

        @Override
        protected Void doInBackground(PesagemAnimal... pesagemAnimals) {
            pesagemAnimalDao.delete(pesagemAnimals[0]);
            return null;
        }
    }

    private static class DeleteAllPesagemAnimalsAsyncTask extends AsyncTask<Void, Void, Void> {
        private PesagemAnimalDao pesagemAnimalDao;

        private DeleteAllPesagemAnimalsAsyncTask(PesagemAnimalDao pesagemAnimalDao) {
            this.pesagemAnimalDao = pesagemAnimalDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            pesagemAnimalDao.deleteAll();
            return null;
        }
    }
}
