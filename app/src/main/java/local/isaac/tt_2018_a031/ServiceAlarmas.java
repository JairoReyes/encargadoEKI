package local.isaac.tt_2018_a031;

import android.app.Application;
import android.app.IntentService;
import android.app.Service;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import local.isaac.tt_2018_a031.PDO.AlertaPDO;
import local.isaac.tt_2018_a031.PDO.AlertaRegistro;
import local.isaac.tt_2018_a031.viewmodel.AlertaViewModel;

public class ServiceAlarmas extends Service implements NavigationView.OnNavigationItemSelectedListener {


    private MiThread hilo;
    private Maps mMapFragment;
    private AlertaViewModel alertaViewModel;
    public static final String preferencias = "MyPrefs" ;
    SharedPreferences sharedpreferences;




    public ServiceAlarmas(){
    }

    @Override
    public void onCreate() {
        hilo = new MiThread();
        //alertaViewModel = ViewModelProviders.of(this).get(AlertaViewModel.class);
    }


    @Override
    public int onStartCommand(Intent intent, int flag, int idProcess){
        super.onStartCommand(intent,flag,idProcess);
        alertaViewModel = intent.getParcelableExtra("AlertViewModel");
        hilo.start();
        return START_STICKY;
    }

    public void onDestroy(){
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    class MiThread extends Thread {
        @Override
        public void run() {

            //alertaViewModel.setAlertaResponse(null);
           /* alertaViewModel.getAlertaResponse().observe((LifecycleOwner)getApplication(),(AlertaPDO alertaResponse) -> {
                procesarRespuestaAlerta(alertaResponse);
            });*/


            for(int i=1; i<=100; i++) {
                System.out.println("Esto es el servicio" + i);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /*Intent intent = new Intent("enviar");
                Bundle bundle = new Bundle();
                bundle.putDouble("doblesito",2.0);
                intent.putExtras(bundle);
                LocalBroadcastManager.getInstance(ServiceAlarmas.this).sendBroadcast(intent);*/
            }
        }
    }


    public void procesarRespuestaAlerta(AlertaPDO alertaResponse){
        if(alertaResponse.getAlertaResponse() != null){
            List<AlertaRegistro> alertas = alertaResponse.getAlertaResponse().getListaAlerta();

            if(alertas != null) {
                for (AlertaRegistro alerta : alertas)
                    saveDataAlerta(alerta.getFecha(), alerta.getLatitud(), alerta.getLongitud(), alerta.getTipo_alerta());
            }
            else
                Toast.makeText(this, "No hay registros", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast httpError = Toast.makeText(this, "Fallo al recibir Alertaes", Toast.LENGTH_SHORT);
            httpError.show();
            alertaViewModel.setAlertaResponse(null);
        }
    }

    public void saveDataAlerta(String fecha,String latitud, String longitud, String tipo){


        Toast.makeText(this,"prueba",Toast.LENGTH_LONG);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("nombre", nombre);
        editor.putString("foto",foto);
        editor.putString("id",id);
        editor.putBoolean("activity_executed", true);
        editor.commit();
        titulos.add(nombre);
        imagen.add(foto);
        ids.add(id);*/

    }
}
