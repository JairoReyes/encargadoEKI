package local.isaac.tt_2018_a031;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Ver_Alarma extends AppCompatActivity {
    private NotificationCompat.Builder mbuilder;
    private int idNotification = 1;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver__alarma);
        //startService(new Intent(this,ServiceAlarmas.class));

        TextView mensaje_principal = (TextView) findViewById(R.id.mensaje_principal);
        TextView unidad = (TextView) findViewById(R.id.unidad);
        TextView conductor = (TextView) findViewById(R.id.conductor);
        TextView fecha = (TextView) findViewById(R.id.fecha);
        TextView hora = (TextView) findViewById(R.id.hora);
        Button boton = (Button) findViewById(R.id.button);
        ImageView imagen_alarma = (ImageView) findViewById(R.id.imagen_alarma);




        String nombre = getIntent().getExtras().getString("nombre");
        String tipo_alerta = getIntent().getExtras().getString("tipo_alerta");
        String id_trolebus = getIntent().getExtras().getString("id_trolebus");
        String fechas = getIntent().getExtras().getString("fecha");
        String id_alerta = getIntent().getExtras().getString("id_alerta");
        //en este caso puse boton fallo emergencia
        if(tipo_alerta.equals("Emergencia"))
            imagen_alarma.setImageResource(R.drawable.emergencia);
        else if(tipo_alerta.equals("Panico"))
            imagen_alarma.setImageResource(R.drawable.panico);
        else if(tipo_alerta.equals("Vial"))
            imagen_alarma.setImageResource(R.drawable.vial);
        else
            imagen_alarma.setImageResource(R.drawable.averia);
        mensaje_principal.setText("El botón de " + tipo_alerta + " ha sido presionado con el id " + id_alerta);
        unidad.setText("UNIDAD: " + id_trolebus);
        conductor.setText("CONDUCTOR: " + nombre);
        fecha.setText("FECHA: " + fechas.split(" ")[0]);
        hora.setText("HORA: " + fechas.split(" ")[1]);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //stopService(new Intent(Ver_Alarma.this,ServiceAlarmas.class));
                Intent intentAction = new Intent(Ver_Alarma.this,Maps.class);

//This is optional if you have more than one buttons and want to differentiate between two
                intentAction.putExtra("action","actionName");

                pendingIntent = PendingIntent.getActivity(Ver_Alarma.this,1,intentAction,PendingIntent.FLAG_ONE_SHOT);
                //pendingIntent = PendingIntent.getBroadcast(Ver_Alarma.this,1,intentAction,PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationManager notificationManager = (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
                mbuilder = new NotificationCompat.Builder(Ver_Alarma.this,null);
                if(Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
                    int importancia = NotificationManager.IMPORTANCE_MAX;
                    CharSequence nombre = "Ofertas";
                    @SuppressLint("WrongConstant") NotificationChannel mChannel =  new NotificationChannel("canal1",nombre,importancia);

                    mChannel.setDescription("esta es una prueba");
                    mChannel.enableVibration(true);

                    notificationManager.createNotificationChannel(mChannel);

                    mbuilder = new NotificationCompat.Builder(Ver_Alarma.this, "canal1");
                }
                mbuilder.setSmallIcon(R.drawable.ic_logoeki).setContentTitle("Jairo").setContentText("Alan")
                        .setColor(Ver_Alarma.this.getResources().getColor(R.color.colorAccent))
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .setPriority(Notification.PRIORITY_MAX);


                notificationManager.notify(idNotification,mbuilder.build());



                //finish();
            }
        });
    }

}
