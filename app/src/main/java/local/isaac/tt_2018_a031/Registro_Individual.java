package local.isaac.tt_2018_a031;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Registro_Individual extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_individual);

        String valor = getIntent().getExtras().getString("nombre");
        Toast.makeText(getApplicationContext(), "nombre " + valor, Toast.LENGTH_SHORT).show();
    }
}
