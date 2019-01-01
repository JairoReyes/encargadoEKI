package local.isaac.tt_2018_a031.configbd;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import local.isaac.tt_2018_a031.model.Alerta;
import local.isaac.tt_2018_a031.model.Contacto;
import local.isaac.tt_2018_a031.model.DAO.AlertaDAO;
import local.isaac.tt_2018_a031.model.DAO.ContactoDAO;
import local.isaac.tt_2018_a031.model.DAO.ParadaDAO;
import local.isaac.tt_2018_a031.model.DAO.TrolebusDAO;
import local.isaac.tt_2018_a031.model.Modelo;
import local.isaac.tt_2018_a031.model.Parada;
import local.isaac.tt_2018_a031.model.TipoUsuario;
import local.isaac.tt_2018_a031.model.Trolebus;
import local.isaac.tt_2018_a031.model.Usuario;

@Database(entities = {Contacto.class,Alerta.class,Modelo.class,Parada.class, TipoUsuario.class, Trolebus.class,Usuario.class}, version = 1, exportSchema = false)
public abstract class AppRepository extends RoomDatabase{

    private static AppRepository INSTANCE;
    private static final String DB_NAME = "eki_pasajero.db";

    public abstract ContactoDAO getContactoDAO();
    public abstract ParadaDAO getParadaDAO();
    public abstract TrolebusDAO getTrolebusDAO();
    public abstract AlertaDAO getAlertaDAO();

}
