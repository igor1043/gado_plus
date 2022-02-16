package br.edu.farol.gadoplus.storage.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.edu.farol.gadoplus.model.TipoGasto;

@Dao
public interface TipoGastoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TipoGasto tipoGasto);

    @Delete
    void delete(TipoGasto tipoGasto);

    @Query("DELETE FROM tipo_gasto")
    void deleteAll();

    @Update
    void update(TipoGasto tipoGasto);

    @Query("SELECT * FROM tipo_gasto WHERE id = :Id")
    TipoGasto getById(int Id);

    @Query("SELECT * from tipo_gasto")
    LiveData<List<TipoGasto>> getAll();

}
