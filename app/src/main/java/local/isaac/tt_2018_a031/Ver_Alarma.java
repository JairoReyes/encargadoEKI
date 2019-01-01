package local.isaac.tt_2018_a031;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Ver_Alarma extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver__alarma);

        TextView mensaje_principal = (TextView) findViewById(R.id.mensaje_principal);
        TextView unidad = (TextView) findViewById(R.id.unidad);
        TextView conductor = (TextView) findViewById(R.id.conductor);
        TextView fecha = (TextView) findViewById(R.id.fecha);
        TextView hora = (TextView) findViewById(R.id.hora);
        Button boton = (Button) findViewById(R.id.button);

        //en este caso puse boton fallo emergencia
        mensaje_principal.setText(R.string.boton_fallo_eme);
        unidad.setText("UNIDAD: ");
        conductor.setText("CONDUCTOR: ");
        fecha.setText("FECHA: ");
        hora.setText("HORA: ");

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
