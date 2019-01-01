package local.isaac.tt_2018_a031.model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import local.isaac.tt_2018_a031.model.Modelo;
import local.isaac.tt_2018_a031.model.Trolebus;

@Dao
public interface TrolebusDAO {

    @Query("SELECT * FROM TROLEBUS WHERE codigo_qr=:codigoQR")
    Trolebus findByCodigoQR(String codigoQR);

    @Query("SELECT m.*  FROM MODELO as m INNER JOIN TROLEBUS as t ON m.id_modelo = t.id_modelo WHERE m.id_modelo = :modelo and t.id_trolebus = :idTrolebus")
    Modelo findModeloByIdTrolebus(Integer modelo,Integer idTrolebus);

}
