package local.isaac.tt_2018_a031;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class ServiceAlarmas extends Service {


    private MiThread hilo;




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
        return Service.START_STICKY;
    }

    public void onDestroy(){

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class MiThread extends Thread {
        @Override
        public void run() {
            for(int i=1; i<=100; i++) {
                System.out.println("Esto es el servicio" + i);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
