package br.edu.farol.gadoplus.storage.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.edu.farol.gadoplus.model.Raca;

@Dao
public interface RacaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Raca raca);

    @Delete
    void delete(Raca raca);

    @Query("DELETE FROM raca")
    void deleteAll();

    @Update
    void update(Raca raca);

    @Query("SELECT * FROM raca WHERE id = :Id")
    Raca getById(int Id);

    @Query("SELECT * from raca")
    LiveData<List<Raca>> getAll();

}
