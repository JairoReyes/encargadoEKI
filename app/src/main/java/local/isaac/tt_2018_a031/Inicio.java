package local.isaac.tt_2018_a031;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import local.isaac.tt_2018_a031.repository.ContactoRepository;
import local.isaac.tt_2018_a031.viewmodel.LoginViewModel;

public class Inicio extends AppCompatActivity {

    ContactoRepository contactoRepository= new ContactoRepository(this);
    public static final String preferencias = "MyPrefs" ;
    SharedPreferences sharedpreferences;

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        Button inicio_sesion = (Button) findViewById(R.id.inicioSesion);
        sharedpreferences = getSharedPreferences(preferencias, Context.MODE_PRIVATE);




        inicio_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), Maps.class);
                startActivityForResult(intent, 0);
            }
        });


    }
}
