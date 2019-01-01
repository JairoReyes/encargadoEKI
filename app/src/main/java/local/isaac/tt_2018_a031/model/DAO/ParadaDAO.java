package local.isaac.tt_2018_a031.model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import local.isaac.tt_2018_a031.model.Parada;

@Dao
public interface ParadaDAO {


    @Query("SELECT * FROM Parada")
    List<Parada> findAllByIdParada ();

    @Query("SELECT * FROM PARADA WHERE id_parada=:idParada")
    Parada findAllByIdParada2 (final Integer idParada);

}
