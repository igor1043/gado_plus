package br.edu.farol.gadoplus.ui.raca;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

import br.edu.farol.gadoplus.model.Raca;
import br.edu.farol.gadoplus.storage.database.repository.RacaRepository;

public class RacaViewModel extends AndroidViewModel {

    private RacaRepository repository;
    private LiveData<List<Raca>> allRacas;

    public RacaViewModel(@NonNull Application application) {
        super(application);
        repository = new RacaRepository(application);
        allRacas = repository.getAll();

    }

    public void insert(Raca raca) {
        repository.insert(raca);
    }

    public void update(Raca raca) {
        repository.update(raca);
    }

    public void delete(Raca raca) {
        repository.delete(raca);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Raca getById(int id){return repository.getById(id);}

    public LiveData<List<Raca>> getAll() {
        return allRacas;
    }
}