package br.edu.farol.gadoplus.storage.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.edu.farol.gadoplus.model.Lote;

@Dao
public interface LoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Lote lote);

    @Delete
    void delete(Lote lote);

    @Query("DELETE FROM lote")
    void deleteAll();

    @Update
    void update(Lote lote);

    @Query("SELECT * FROM lote WHERE id = :Id")
    Lote getById(int Id);

    @Query("SELECT * from lote")
    LiveData<List<Lote>> getAll();

}
