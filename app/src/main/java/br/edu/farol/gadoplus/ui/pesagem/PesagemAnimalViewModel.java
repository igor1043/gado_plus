package br.edu.farol.gadoplus.ui.pesagem;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import br.edu.farol.gadoplus.model.PesagemAnimal;
import br.edu.farol.gadoplus.storage.database.repository.PesagemAnimalRepository;

public class PesagemAnimalViewModel extends AndroidViewModel {

    private PesagemAnimalRepository repository;
    private LiveData<List<PesagemAnimal>> allPesagenAnimals;

    public PesagemAnimalViewModel(@NonNull Application application) {
        super(application);
        repository = new PesagemAnimalRepository(application);
        allPesagenAnimals = repository.getAll();

    }

    public void insert(PesagemAnimal pesagemAnimal) {
        repository.insert(pesagemAnimal);
    }

    public void update(PesagemAnimal pesagemAnimal) {
        repository.update(pesagemAnimal);
    }

    public void delete(PesagemAnimal pesagemAnimal) {
        repository.delete(pesagemAnimal);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public PesagemAnimal getById(int id){return repository.getById(id);}

    public LiveData<List<PesagemAnimal>> getAll() {
        return allPesagenAnimals;
    }

    public LiveData<List<PesagemAnimal>> getAll(int idPesagem) {
        return repository.getAll(idPesagem);
    }
}