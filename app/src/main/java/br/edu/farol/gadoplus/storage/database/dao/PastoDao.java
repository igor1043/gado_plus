package br.edu.farol.gadoplus.storage.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.edu.farol.gadoplus.model.Pasto;

@Dao
public interface PastoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Pasto pasto);

    @Delete
    void delete(Pasto pasto);

    @Query("DELETE FROM pasto")
    void deleteAll();

    @Update
    void update(Pasto pasto);

    @Query("SELECT * FROM pasto WHERE id = :Id")
    Pasto getById(int Id);

    @Query("SELECT * from pasto")
    LiveData<List<Pasto>> getAll();

}
