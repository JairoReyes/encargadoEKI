package local.isaac.tt_2018_a031.model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import local.isaac.tt_2018_a031.model.Alerta;


@Dao
public interface AlertaDAO {

    @Insert
    long insertAlerta(Alerta alerta);

}
