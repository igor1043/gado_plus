package br.edu.farol.gadoplus.storage.database.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import br.edu.farol.gadoplus.model.Animal;
import br.edu.farol.gadoplus.storage.database.AppDatabase;
import br.edu.farol.gadoplus.storage.database.dao.AnimalDao;

public class AnimalRepository {
    private AnimalDao animalDao;
    private LiveData<List<Animal>> allAnimals;

    public AnimalRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        animalDao = database.animalDao();
        allAnimals = animalDao.getAll();
    }

    public void insert(Animal animal) {
        new InsertAnimalAsyncTask(animalDao).execute(animal);
    }

    public void update(Animal animal) {
        new UpdateAnimalAsyncTask(animalDao).execute(animal);
    }

    public void delete(Animal animal) {
        new DeleteAnimalAsyncTask(animalDao).execute(animal);
    }

    public void deleteAll() {
        new DeleteAllAnimalsAsyncTask(animalDao).execute();
    }

    public LiveData<List<Animal>> getAll() {
        return allAnimals;
    }

    public Animal getById(int id){ return animalDao.getById(id);}

    private static class InsertAnimalAsyncTask extends AsyncTask<Animal, Void, Void> {
        private AnimalDao animalDao;

        private InsertAnimalAsyncTask(AnimalDao animalDao) {
            this.animalDao = animalDao;
        }

        @Override
        protected Void doInBackground(Animal... animal) {
            animalDao.insert(animal[0]);
            return null;
        }
    }

    private static class UpdateAnimalAsyncTask extends AsyncTask<Animal, Void, Void> {
        private AnimalDao animalDao;

        private UpdateAnimalAsyncTask(AnimalDao animalDao) {
            this.animalDao = animalDao;
        }

        @Override
        protected Void doInBackground(Animal... animals) {
            animalDao.update(animals[0]);
            return null;
        }
    }

    private static class DeleteAnimalAsyncTask extends AsyncTask<Animal, Void, Void> {
        private AnimalDao animalDao;

        private DeleteAnimalAsyncTask(AnimalDao animalDao) {
            this.animalDao = animalDao;
        }

        @Override
        protected Void doInBackground(Animal... animals) {
            animalDao.delete(animals[0]);
            return null;
        }
    }

    private static class DeleteAllAnimalsAsyncTask extends AsyncTask<Void, Void, Void> {
        private AnimalDao animalDao;

        private DeleteAllAnimalsAsyncTask(AnimalDao animalDao) {
            this.animalDao = animalDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            animalDao.deleteAll();
            return null;
        }
    }
}
