package br.edu.farol.gadoplus.storage.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.edu.farol.gadoplus.model.PesagemAnimal;

@Dao
public interface PesagemAnimalDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(PesagemAnimal pesagemAnimal);

    @Delete
    void delete(PesagemAnimal pesagemAnimal);

    @Query("DELETE FROM pesagem_animal")
    void deleteAll();

    @Update
    void update(PesagemAnimal pesagemAnimal);

    @Query("SELECT * FROM pesagem_animal WHERE id = :Id")
    PesagemAnimal getById(int Id);

    @Query("SELECT * from pesagem_animal")
    LiveData<List<PesagemAnimal>> getAll();

    @Query("SELECT * from pesagem_animal WHERE pesagem_id = :id")
    LiveData<List<PesagemAnimal>> getAll(int id);

}
