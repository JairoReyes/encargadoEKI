package local.isaac.tt_2018_a031.repository;

import android.content.Context;
import android.os.AsyncTask;

import java.util.concurrent.ExecutionException;

import local.isaac.tt_2018_a031.configbd.AppDatabase;
import local.isaac.tt_2018_a031.configbd.AppRepository;
import local.isaac.tt_2018_a031.model.Modelo;
import local.isaac.tt_2018_a031.model.Trolebus;

public class TrolebusRepository {

    private AppRepository appRepository;
    private String qrUnidad;
    private Integer idTrolebus;
    private Integer idModelo;

    public TrolebusRepository(Context context){
        appRepository = AppDatabase.getInstance(context).getAppRepository();
    }

    public Trolebus obtenerUnidadPorQR (String codigoQR){
        qrUnidad = codigoQR;
        try{
            return new ObtenerTrolebusPorQRAsyncTask().execute().get();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Modelo obtenerModeloPorIdTrolebus(Integer idModelo, Integer idTrolebus){
        this.idModelo = idModelo;
        this.idTrolebus = idTrolebus;
        try{
            return new ObtenerModeloPorIdTrolebusAsyncTask().execute().get();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class ObtenerTrolebusPorQRAsyncTask extends AsyncTask<Void,Void,Trolebus>{
        @Override
        protected Trolebus doInBackground(Void...url){
            return appRepository.getTrolebusDAO().findByCodigoQR(qrUnidad);
        }
    }

    private class ObtenerModeloPorIdTrolebusAsyncTask extends AsyncTask<Void,Void,Modelo>{
        @Override
        protected Modelo doInBackground(Void...url){
            return appRepository.getTrolebusDAO().findModeloByIdTrolebus(idModelo,idTrolebus);
        }
    }
}
