package br.edu.farol.gadoplus.storage.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.edu.farol.gadoplus.model.Animal;

@Dao
public interface AnimalDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Animal animal);

    @Delete
    void delete(Animal animal);

    @Query("DELETE FROM animal")
    void deleteAll();

    @Update
    void update(Animal animal);

    @Query("SELECT * FROM animal WHERE id = :id LIMIT 1")
    Animal getById(int id);

    @Query("SELECT * from animal")
    LiveData<List<Animal>> getAll();

}
