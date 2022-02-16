package br.edu.farol.gadoplus.ui.gastos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

import br.edu.farol.gadoplus.model.TipoGasto;
import br.edu.farol.gadoplus.storage.database.repository.TipoGastoRepository;

public class TipoGastosViewModel extends AndroidViewModel {

    private TipoGastoRepository repository;
    private LiveData<List<TipoGasto>> allTipoGastos;

    public TipoGastosViewModel(@NonNull Application application) {
        super(application);
        repository = new TipoGastoRepository(application);
        allTipoGastos = repository.getAll();

    }

    public void insert(TipoGasto tipoGasto) {
        repository.insert(tipoGasto);
    }

    public void update(TipoGasto tipoGasto) {
        repository.update(tipoGasto);
    }

    public void delete(TipoGasto tipoGasto) {
        repository.delete(tipoGasto);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public TipoGasto getById(int id){return repository.getById(id);}

    public LiveData<List<TipoGasto>> getAll() {
        return allTipoGastos;
    }
}