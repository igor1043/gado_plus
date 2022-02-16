package br.edu.farol.gadoplus.ui.gastos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import br.edu.farol.gadoplus.model.Gasto;
import br.edu.farol.gadoplus.model.GastoTotalizado;
import br.edu.farol.gadoplus.model.Propriedade;
import br.edu.farol.gadoplus.storage.database.repository.GastoRepository;
import br.edu.farol.gadoplus.storage.database.repository.PropriedadeRepository;

public class GastosViewModel extends AndroidViewModel {

    private GastoRepository repository;
    private LiveData<List<Gasto>> allGastos;

    public GastosViewModel(@NonNull Application application) {
        super(application);
        repository = new GastoRepository(application);
        allGastos = repository.getAll();

    }

    public void insert(Gasto gasto) {
        repository.insert(gasto);
    }

    public void update(Gasto gasto) {
        repository.update(gasto);
    }

    public void delete(Gasto gasto) {
        repository.delete(gasto);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Gasto getById(int id){return repository.getById(id);}

    public LiveData<List<Gasto>> getAll() {
        return allGastos;
    }

    public LiveData<List<GastoTotalizado>> getGastoToalizado() {
        return repository.getGastoToalizado();
    }
}