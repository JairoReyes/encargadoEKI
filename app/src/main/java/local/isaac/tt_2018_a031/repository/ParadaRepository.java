package local.isaac.tt_2018_a031.repository;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

import local.isaac.tt_2018_a031.configbd.AppDatabase;
import local.isaac.tt_2018_a031.configbd.AppRepository;
import local.isaac.tt_2018_a031.model.Parada;

public class ParadaRepository {

    private AppRepository appRepository;
    private Integer idParada;

    public ParadaRepository(Context context){
        appRepository = AppDatabase.getInstance(context).getAppRepository();
    }



    public List<Parada> obtenerParadasPorIdParadas(){
        try {
            return new ObtenerParadasAsyncTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Parada obtenerParadaPorIdParada(int idParada){
        this.idParada = idParada;
        try {
            return new ObtenerParadaPorIdParadaoAsyncTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }


    private class ObtenerParadaPorIdParadaoAsyncTask extends AsyncTask<Void,Void,Parada>{
        @Override
        protected Parada doInBackground(Void...url){
            return appRepository.getParadaDAO().findAllByIdParada2(idParada);
        }
    }

    private class ObtenerParadasAsyncTask extends AsyncTask<Void,Void,List<Parada>>{
        @Override
        protected List<Parada> doInBackground(Void...url){
            return appRepository.getParadaDAO().findAllByIdParada();
        }
    }



}
