package local.isaac.tt_2018_a031;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.arch.lifecycle.ViewModelProviders;
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
import android.widget.Toast;

import local.isaac.tt_2018_a031.PDO.QuitarAlertaPDO;
import local.isaac.tt_2018_a031.viewmodel.QuitarAlertaViewModel;

public class Ver_Alarma extends AppCompatActivity {
    private NotificationCompat.Builder mbuilder;
    private int idNotification = 1;
    PendingIntent pendingIntent;

    private QuitarAlertaViewModel quitarAlertaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver__alarma);

        getWindow().getDecorView().setBackgroundColor(getResources().getColor(android.R.color.transparent));
        //startService(new Intent(this,ServiceAlarmas.class));
        quitarAlertaViewModel = ViewModelProviders.of(this).get(QuitarAlertaViewModel.class);
        TextView mensaje_principal = (TextView) findViewById(R.id.mensaje_principal);
        TextView unidad = (TextView) findViewById(R.id.unidad);
        TextView conductor = (TextView) findViewById(R.id.conductor);
        TextView fecha = (TextView) findViewById(R.id.fecha);
        TextView hora = (TextView) findViewById(R.id.hora);
        TextView placa = (TextView) findViewById(R.id.placa);
        Button boton = (Button) findViewById(R.id.button);
        Button atendida = (Button) findViewById(R.id.atendida);
        ImageView imagen_alarma = (ImageView) findViewById(R.id.imagen_alarma);



        String nombre = getIntent().getExtras().getString("nombre");
        String placas = getIntent().getExtras().getString("placas");
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
        placa.setText("PLACAS; " + placas);
        conductor.setText("CONDUCTOR: " + nombre);
        fecha.setText("FECHA: " + fechas.split(" ")[0]);
        hora.setText("HORA: " + fechas.split(" ")[1]);






        atendida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(Ver_Alarma.this, Maps.class);

                quitarAlertaViewModel.getQuitarAlertaResponse("0", id_alerta).observe(Ver_Alarma.this, (QuitarAlertaPDO quitarAlertaResponse) -> {
                    procesarRespuesta(quitarAlertaResponse);
                });
                //startActivity(intent);
                finish();
            }
        });

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //stopService(new Intent(Ver_Alarma.this,ServiceAlarmas.class));
                //Intent intentAction = new Intent(Ver_Alarma.this,Maps.class);
                //startActivity(intentAction);
                finish();
            }
        });
    }

    public void procesarRespuesta(QuitarAlertaPDO quitarAlertaResponse){
        if(quitarAlertaResponse.getQuitarAlertaResponse() != null){

            Toast.makeText(this, "Alerta atendida", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, quitarAlertaResponse.getError().getText(), Toast.LENGTH_SHORT).show();
            quitarAlertaViewModel.setQuitarAlertaResponse(null);
        }
    }

}
