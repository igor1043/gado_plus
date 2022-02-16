package br.edu.farol.gadoplus.storage.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.edu.farol.gadoplus.model.Pesagem;

@Dao
public interface PesagemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Pesagem pesagem);

    @Delete
    void delete(Pesagem pesagem);

    @Query("DELETE FROM pesagem")
    void deleteAll();

    @Update
    void update(Pesagem pesagem);

    @Query("SELECT * FROM pesagem WHERE id = :Id")
    Pesagem getById(int Id);

    @Query("SELECT * from pesagem")
    LiveData<List<Pesagem>> getAll();

}
