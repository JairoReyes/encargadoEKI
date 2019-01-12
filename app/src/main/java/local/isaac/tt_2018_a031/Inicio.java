package local.isaac.tt_2018_a031;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import local.isaac.tt_2018_a031.PDO.LoginPDO;
import local.isaac.tt_2018_a031.repository.ContactoRepository;
import local.isaac.tt_2018_a031.viewmodel.LoginViewModel;

public class Inicio extends AppCompatActivity {

    ContactoRepository contactoRepository= new ContactoRepository(this);
    public static final String preferencias = "MyPrefs" ;
    SharedPreferences sharedpreferences;

    private LoginViewModel loginViewModel;
    private ProgressBar progressBar;

    private EditText expediente;
    private EditText pass;
    private Button inicio_sesion;

    private String expedienteText;
    private String passText;

    private String regexExpediente = "^[\\w@\\\\#$%&,*.()+]*$";
    private String regexContrasena = "^[\\w@\\\\#$%&,*.()+]{8,}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        inicio_sesion = (Button) findViewById(R.id.inicioSesion);
        sharedpreferences = getSharedPreferences(preferencias, Context.MODE_PRIVATE);

        expediente = (EditText)findViewById(R.id.loginUs);
        pass = (EditText)findViewById(R.id.loginCon);



        inicio_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expedienteText = expediente.getText().toString();
                passText = pass.getText().toString();

                //verificando que cumpla con las expresiones regulares
                if(expedienteText.matches(regexExpediente) && passText.matches(regexContrasena))
                    postLogin(expedienteText,passText);
                else{
                    Toast errorLogin = Toast.makeText(getApplicationContext(), "Verifique los campos e intente de nuevo.", Toast.LENGTH_SHORT);
                    errorLogin.show();
                }

                //Intent intent = new Intent(v.getContext(), Maps.class);
                //startActivityForResult(intent, 0);
            }
        });


    }

    public void postLogin(String numero, String cont) {
        progressBar = (ProgressBar)findViewById(R.id.ProgressBar);
        progressBar.setVisibility(View.VISIBLE);
        inicio_sesion.setVisibility(View.GONE);
        expediente.setVisibility(View.GONE);
        pass.setVisibility(View.GONE);


        loginViewModel.getLoginResponse(numero, cont).observe(this, (LoginPDO loginResponse) -> {
            progressBar.setVisibility(View.GONE);
            inicio_sesion.setVisibility(View.VISIBLE);
            expediente.setVisibility(View.VISIBLE);
            pass.setVisibility(View.VISIBLE);
            procesarRespuesta(loginResponse);
        });
    }

    public void procesarRespuesta(LoginPDO loginResponse){
        if(loginResponse.getLoginResponse() != null){
            String nombre = loginResponse.getLoginResponse().getNombre();
            String apellido = loginResponse.getLoginResponse().getApellido();
            String edad = loginResponse.getLoginResponse().getEdad();
            String expediente = loginResponse.getLoginResponse().getExpediente();
            String idUsuario = loginResponse.getLoginResponse().getIdUsuario();
            saveData(nombre,apellido,edad,expediente,idUsuario);

        }
        else if(loginResponse.getError().getText().startsWith("Fallo en la conexión")){
            expediente.setText("");
            pass.setText("");
            loginViewModel.setLoginResponse(null);
            Toast fail = Toast.makeText(this, loginResponse.getError().getText(), Toast.LENGTH_SHORT);
            fail.show();
        }
        else{
            Toast httpError = Toast.makeText(this, loginResponse.getError().getText(), Toast.LENGTH_SHORT);
            httpError.show();
            loginViewModel.setLoginResponse(null);
        }
    }

    public void saveData(String nombre,String apellido,String edad,String expediente,String id_usuario){

        SharedPreferences pref = getSharedPreferences("LoginPreferences",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("nombre", nombre);
        editor.putString("apellido", apellido);
        editor.putString("edad",edad);
        editor.putString("expediente", expediente);
        editor.putString("id_usuario",id_usuario);
        editor.apply();

        Intent intent = new Intent(this, Maps.class);
        startActivity(intent);
        finish();

    }

}
