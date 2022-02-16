package br.edu.farol.gadoplus.ui.lotes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import br.edu.farol.gadoplus.model.Lote;
import br.edu.farol.gadoplus.storage.database.repository.LoteRepository;

public class LotesViewModel extends AndroidViewModel {

    private LoteRepository repository;
    private LiveData<List<Lote>> allLotes;

    public LotesViewModel(@NonNull Application application) {
        super(application);
        repository = new LoteRepository(application);
        allLotes = repository.getAll();

    }

    public void insert(Lote lote) {
        repository.insert(lote);
    }

    public void update(Lote lote) {
        repository.update(lote);
    }

    public void delete(Lote lote) {
        repository.delete(lote);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Lote getById(int id){return repository.getById(id);}

    public LiveData<List<Lote>> getAll() {
        return allLotes;
    }
}