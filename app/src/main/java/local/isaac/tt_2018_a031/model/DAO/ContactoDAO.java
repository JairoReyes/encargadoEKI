package local.isaac.tt_2018_a031.model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import local.isaac.tt_2018_a031.model.Contacto;
import local.isaac.tt_2018_a031.model.Parada;
import local.isaac.tt_2018_a031.model.Usuario;

@Dao
public interface ContactoDAO {

    @Query("SELECT * FROM Contacto WHERE id_usuario=:idUsuario")
    List<Contacto> findAllByIdUsuario (final Integer idUsuario);

    @Query("SELECT * FROM usuario WHERE correo=:correo")
    Usuario findUsuarioByCorreo (final String correo);

    @Query("SELECT * FROM Parada")
    List<Parada> findAllByIdParada ();

    @Query("SELECT * FROM PARADA WHERE id_parada=:idParada")
    Parada findAllByIdParada2 (final Integer idParada);

    @Insert
    long insertContacto(Contacto contacto);

    @Insert
    void insertUsuario(Usuario usuario);

    @Query("SELECT * FROM Contacto WHERE telefono=:telefono")
    Contacto findContactoByPhone (final String telefono);

    @Delete
    void eliminarContacto(Contacto contacto);

    @Update
    void updateContacto(Contacto contacto);

    @Update
    void updateUsuario(Usuario usuario);


}
