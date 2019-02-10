package local.isaac.tt_2018_a031;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
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
import local.isaac.tt_2018_a031.PDO.ConductorPDO;
import local.isaac.tt_2018_a031.PDO.ConductorRegistro;
import local.isaac.tt_2018_a031.rest.APIClient;
import local.isaac.tt_2018_a031.rest.APIInterface;
import local.isaac.tt_2018_a031.viewmodel.AlertaViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceAlarmas extends Service implements NavigationView.OnNavigationItemSelectedListener {


    private MiThread hilo;
    private APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

    private NotificationCompat.Builder mbuilder;
    PendingIntent pendingIntent;


    public ServiceAlarmas(){
    }

    @Override
    public void onCreate() {
        hilo = new MiThread();
    }


    @Override
    public int onStartCommand(Intent intent, int flag, int idProcess){
        super.onStartCommand(intent,flag,idProcess);
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

            for(int i=1; i<=100; i++) {
                System.out.println("Esto es el servicio" + i);
                Call<AlertaPDO> call = apiInterface.alertas();
                call.enqueue(new Callback<AlertaPDO>() {
                    @Override
                    public void onResponse(Call<AlertaPDO> call, Response<AlertaPDO> response) {
                        if(response.isSuccessful()){
                            String var = "";
                            List<AlertaRegistro> alertas = response.body().getAlertaResponse().getListaAlerta();
                            if(alertas != null) {
                                Intent intentAction = new Intent(getApplicationContext(),Maps.class);
                                intentAction.putExtra("action","actionName");

                                pendingIntent = PendingIntent.getActivity(getApplicationContext(),1,intentAction,PendingIntent.FLAG_ONE_SHOT);
                                //pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),1,intentAction,PendingIntent.FLAG_UPDATE_CURRENT);

                                NotificationManager notificationManager = (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
                                mbuilder = new NotificationCompat.Builder(getApplicationContext(),null);
                                if(Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
                                    int importancia = NotificationManager.IMPORTANCE_MAX;
                                    CharSequence nombre = "Ofertas";
                                    @SuppressLint("WrongConstant") NotificationChannel mChannel =  new NotificationChannel("canal1",nombre,importancia);

                                    mChannel.setDescription("esta es una prueba");
                                    mChannel.enableVibration(true);

                                    notificationManager.createNotificationChannel(mChannel);

                                    mbuilder = new NotificationCompat.Builder(getApplicationContext(), "canal1");
                                }
                                mbuilder.setSmallIcon(R.drawable.ic_logoeki).setContentTitle("Jairo").setContentText("Alan")
                                        .setColor(getApplicationContext().getResources().getColor(R.color.colorAccent))
                                        .setContentIntent(pendingIntent)
                                        .setAutoCancel(true)
                                        .setPriority(Notification.PRIORITY_MAX);


                                notificationManager.notify(1,mbuilder.build());
                            }
                            else
                                Toast.makeText(getApplicationContext(), "No hay registros", Toast.LENGTH_LONG).show();
                            //Toast.makeText(getApplicationContext(),var,Toast.LENGTH_LONG).show();
                        }else{
                            System.out.println("No fue successful");
                        }
                    }

                    @Override
                    public void onFailure(Call<AlertaPDO> call, Throwable t) {
                        System.out.println("Error al recibir lista de conductores");
                    }
                });
                try {
                    Thread.sleep(10000);
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

}
