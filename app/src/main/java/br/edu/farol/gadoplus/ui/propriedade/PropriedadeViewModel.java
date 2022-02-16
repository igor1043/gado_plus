package br.edu.farol.gadoplus.ui.propriedade;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.edu.farol.gadoplus.model.Propriedade;
import br.edu.farol.gadoplus.storage.database.repository.PropriedadeRepository;

public class PropriedadeViewModel extends AndroidViewModel {

    private PropriedadeRepository repository;
    private LiveData<List<Propriedade>> allPropriedades;

    public PropriedadeViewModel(@NonNull Application application) {
        super(application);
        repository = new PropriedadeRepository(application);
        allPropriedades = repository.getAll();

    }

    public void insert(Propriedade propriedade) {
        repository.insert(propriedade);
    }

    public void update(Propriedade propriedade) {
        repository.update(propriedade);
    }

    public void delete(Propriedade propriedade) {
        repository.delete(propriedade);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Propriedade getById(int id){return repository.getById(id);}

    public LiveData<List<Propriedade>> getAll() {
        return allPropriedades;
    }

}

