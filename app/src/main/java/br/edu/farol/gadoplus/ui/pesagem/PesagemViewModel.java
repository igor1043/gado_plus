package br.edu.farol.gadoplus.ui.pesagem;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import br.edu.farol.gadoplus.model.Pesagem;
import br.edu.farol.gadoplus.storage.database.repository.PesagemRepository;

public class PesagemViewModel extends AndroidViewModel {

    private PesagemRepository repository;
    private LiveData<List<Pesagem>> allPesagens;

    public PesagemViewModel(@NonNull Application application) {
        super(application);
        repository = new PesagemRepository(application);
        allPesagens = repository.getAll();
    }

    public void insert(Pesagem pesagem) {
        repository.insert(pesagem);
    }

    public void update(Pesagem pesagem) {
        repository.update(pesagem);
    }

    public void delete(Pesagem pesagem) {
        repository.delete(pesagem);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Pesagem getById(int id){return repository.getById(id);}

    public LiveData<List<Pesagem>> getAll() {
        return allPesagens;
    }
}