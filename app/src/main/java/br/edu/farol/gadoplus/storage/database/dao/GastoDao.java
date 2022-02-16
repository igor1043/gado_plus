package br.edu.farol.gadoplus.storage.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.edu.farol.gadoplus.model.Gasto;
import br.edu.farol.gadoplus.model.GastoTotalizado;

@Dao
public interface GastoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Gasto gasto);

    @Delete
    void delete(Gasto gasto);

    @Query("DELETE FROM gasto")
    void deleteAll();

    @Update
    void update(Gasto gasto);

    @Query("SELECT * FROM gasto WHERE id = :Id")
    Gasto getById(int Id);

    @Query("SELECT * from gasto")
    LiveData<List<Gasto>> getAll();

    @Query("SELECT tipo_gasto.nome AS nome, SUM(gasto.valor) AS valor\n" +
            "FROM gasto \n" +
            "INNER JOIN tipo_gasto on tipo_gasto.id = gasto.tipo_gasto_id\n" +
            "GROUP BY tipo_gasto.id")
    LiveData<List<GastoTotalizado>> getGastoToalizado();

}
