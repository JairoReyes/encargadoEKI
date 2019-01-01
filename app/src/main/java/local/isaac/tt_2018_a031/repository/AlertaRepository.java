package local.isaac.tt_2018_a031.repository;

import android.content.Context;
import android.os.AsyncTask;

import java.util.concurrent.ExecutionException;

import local.isaac.tt_2018_a031.configbd.AppDatabase;
import local.isaac.tt_2018_a031.configbd.AppRepository;
import local.isaac.tt_2018_a031.model.Alerta;
import local.isaac.tt_2018_a031.model.Usuario;

public class AlertaRepository {

    private AppRepository appRepository;
    private Alerta alerta;

    public AlertaRepository(Context context){
        appRepository = AppDatabase.getInstance(context).getAppRepository();
    }

    public void insertarAlerta(Alerta alerta){
        this.alerta = alerta;
        try {
            new InsertarAlertaAsyncTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private class InsertarAlertaAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void...url){
            appRepository.getAlertaDAO().insertAlerta(alerta);
            return null;
        }
    }

}
