package br.edu.farol.gadoplus.ui.pastos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.edu.farol.gadoplus.model.Lote;
import br.edu.farol.gadoplus.model.Pasto;
import br.edu.farol.gadoplus.storage.database.repository.LoteRepository;
import br.edu.farol.gadoplus.storage.database.repository.PastoRepository;

public class PastosViewModel extends AndroidViewModel {

    private PastoRepository repository;
    private LiveData<List<Pasto>> allPastos;

    public PastosViewModel(@NonNull Application application) {
        super(application);
        repository = new PastoRepository(application);
        allPastos = repository.getAll();

    }

    public void insert(Pasto pasto) {
        repository.insert(pasto);
    }

    public void update(Pasto pasto) {
        repository.update(pasto);
    }

    public void delete(Pasto pasto) {
        repository.delete(pasto);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Pasto getById(int id){return repository.getById(id);}

    public LiveData<List<Pasto>> getAll() {
        return allPastos;
    }
}