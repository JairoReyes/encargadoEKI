package local.isaac.tt_2018_a031.repository;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

import local.isaac.tt_2018_a031.configbd.AppDatabase;
import local.isaac.tt_2018_a031.configbd.AppRepository;
import local.isaac.tt_2018_a031.model.Contacto;
import local.isaac.tt_2018_a031.model.Usuario;

public class ContactoRepository {

    private AppRepository appRepository;
    private Integer idUsuario;
    private Contacto contacto;
    private Usuario usuario;
    private String telefono;
    private String correo;

    public ContactoRepository(Context context){
        appRepository = AppDatabase.getInstance(context).getAppRepository();
    }

    public List<Contacto> obtenerContactosPorIdUsuario(Integer usuario){
        idUsuario = usuario;
        try {
            return new ObtenerContactosAsyncTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public long insertarContacto(Contacto contacto){
        this.contacto = contacto;
        long idContactoInsertado = 0;
        try {
            idContactoInsertado = new InsertarContactoAsyncTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return idContactoInsertado;
    }

    public void insertarUsuario(Usuario usuario){
        this.usuario = usuario;
        try {
            new InsertarUsuarioAsyncTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public Contacto obtenerContactoPorTelefono(String telefono){
        this.telefono = telefono;
        try {
            return new ObtenerContactoPorTelefonoAsyncTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Usuario obtenerUsuarioPorCorreo(String correo){
        this.correo = correo;
        try {
            return new ObtenerUsuarioPorCorreoAsyncTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void actualizarContacto(Contacto contacto){
        this.contacto = contacto;
        try {
            new ActualizarContactoAsyncTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public void actualizarUsuario(Usuario usuario){
        this.usuario = usuario;
        try {
            new ActualizarUsuarioAsyncTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public void eliminarContacto(Contacto contacto){
        this.contacto = contacto;
        try {
            new EliminarContactoAsyncTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }



    private class ObtenerUsuarioPorCorreoAsyncTask extends AsyncTask<Void, Void, Usuario> {
        @Override
        protected Usuario doInBackground(Void...url){
            return appRepository.getContactoDAO().findUsuarioByCorreo(correo);
        }
    }



    private class ObtenerContactosAsyncTask extends AsyncTask<Void,Void,List<Contacto>>{
        @Override
        protected List<Contacto> doInBackground(Void...url){
            return appRepository.getContactoDAO().findAllByIdUsuario(idUsuario);
        }
    }

    private class InsertarContactoAsyncTask extends AsyncTask<Void, Void, Long> {
        @Override
        protected Long doInBackground(Void...url){
            return appRepository.getContactoDAO().insertContacto(contacto);
        }
    }

    private class InsertarUsuarioAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void...url){
            appRepository.getContactoDAO().insertUsuario(usuario);
            return null;
        }
    }

    private class ObtenerContactoPorTelefonoAsyncTask extends AsyncTask<Void,Void,Contacto>{
        @Override
        protected Contacto doInBackground(Void...url){
            return appRepository.getContactoDAO().findContactoByPhone(telefono);
        }
    }

    private class ActualizarContactoAsyncTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void...url){
            appRepository.getContactoDAO().updateContacto(contacto);
            return null;
        }
    }


    private class ActualizarUsuarioAsyncTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void...url){
            appRepository.getContactoDAO().updateUsuario(usuario);
            return null;
        }
    }

    private class EliminarContactoAsyncTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void...url){
            appRepository.getContactoDAO().eliminarContacto(contacto);
            return null;
        }
    }
}
