package local.isaac.tt_2018_a031;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import local.isaac.tt_2018_a031.PDO.ConductorPDO;
import local.isaac.tt_2018_a031.PDO.ConductorRegistro;
import local.isaac.tt_2018_a031.viewmodel.ConductorViewModel;

public class Registros extends AppCompatActivity {

    private ConductorViewModel conductorViewModel;
    public static final String preferencias = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    private Spinner mSpinner;
    ListViewAdapter adapter;
    private RecyclerView recyclerView;
    ArrayList<String> titulos = new ArrayList<String>();
    ArrayList<String> imagen = new ArrayList<String>();
    private RecyclerAdapter rAdapter = new RecyclerAdapter(Registros.this,titulos,imagen);;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        sharedpreferences = getSharedPreferences(preferencias, Context.MODE_PRIVATE);
        conductorViewModel = ViewModelProviders.of(this).get(ConductorViewModel.class);
        mSpinner = (Spinner) findViewById(R.id.mSpinner);
        //listview = (ListView) findViewById(R.id.listView);
        ArrayList<String> elementos = new ArrayList<>();
        elementos.add("Unidad de trolebús");
        elementos.add("Nombre de conductor");







        ArrayAdapter adp = new ArrayAdapter(this,android.R.layout.simple_spinner_item,elementos);
        mSpinner.setAdapter(adp);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String elemento = (String) mSpinner.getAdapter().getItem(position);
                Toast.makeText(parent.getContext(),"este es " + elemento + " " + position, Toast.LENGTH_SHORT).show();
                if(position == 0){
                    ////////////trolebus
                    titulos.clear();
                    imagen.clear();
                    titulos.add("este es 1");
                    //imagen.add(R.drawable.descarga);
                    titulos.add("este es 2");
                    //imagen.add(R.drawable.descarga);
                    titulos.add("este es 3");
                    //imagen.add(R.drawable.descarga);
                    titulos.add("este es 4");
                    //imagen.add(R.drawable.descarga);


                    //recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                    rAdapter = new RecyclerAdapter(Registros.this,titulos,imagen);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.addItemDecoration(new DividerItemDecoration(Registros.this, LinearLayoutManager.VERTICAL));
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(rAdapter);

                }
                else {

                    titulos.clear();
                    imagen.clear();
                    ///////CONDUCTORES


                    conductorViewModel.getConductorResponse().observe(Registros.this, (ConductorPDO conductorResponse) -> {

                        Toast toast1 = Toast.makeText(getApplicationContext(),"hola" + conductorResponse.getConductorResponse().toString(), Toast.LENGTH_SHORT);
                        toast1.show();
                        //procesarRespuesta(conductorResponse);
                    });


                    //recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                    rAdapter = new RecyclerAdapter(Registros.this,titulos,imagen);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.addItemDecoration(new DividerItemDecoration(Registros.this, LinearLayoutManager.VERTICAL));
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(rAdapter);


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                TextView nombre = (TextView) view.findViewById(R.id.list_row_title);
                Intent intent = new Intent(Registros.this, Registro_Individual.class);

                intent.putExtra("nombre",nombre.getText().toString());
                startActivityForResult(intent, 0);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }


    public void procesarRespuesta(ConductorPDO conductorResponse){
        if(conductorResponse.getConductorResponse() != null){
            List<ConductorRegistro> conductores = conductorResponse.getConductorResponse().getListaCondcutor();
            for(ConductorRegistro conductor: conductores){
                //conductor.getApellido();
                //conductor.getFoto();

                Toast fail = Toast.makeText(this, conductor.getNombre() + conductor.getFoto(), Toast.LENGTH_LONG);
                fail.show();
                saveData(conductor.getNombre(),conductor.getFoto());
            }
            /*
            String nombre = conductorResponse.getConductorResponse().getNombre();
            String apellido = conductorResponse.getConductorResponse().getApellido();
            String edad = conductorResponse.getConductorResponse().getEdad();
            String expediente = conductorResponse.getConductorResponse().getExpediente();
            String idUsuario = conductorResponse.getConductorResponse().getIdUsuario();
            String foto = conductorResponse.getConductorResponse().getFoto();
            */


        }
        else if(conductorResponse.getError().getText().startsWith("Fallo en la conexión")){
            //expediente.setText("");
            //pass.setText("");
            conductorViewModel.setLoginResponse(null);
            Toast fail = Toast.makeText(this, conductorResponse.getError().getText(), Toast.LENGTH_SHORT);
            fail.show();
        }
        else{
            Toast httpError = Toast.makeText(this, conductorResponse.getError().getText(), Toast.LENGTH_SHORT);
            httpError.show();
            conductorViewModel.setLoginResponse(null);
        }
    }


    public void saveData(String nombre,String foto){

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("nombre", nombre);
        //editor.putString("apellido", apellido);
        //editor.putString("edad",edad);
        //editor.putString("expediente", expediente);
        //editor.putString("id_usuario",id_usuario);
        editor.putString("foto",foto);
        editor.putBoolean("activity_executed", true);
        editor.commit();

        //imagen.add(DescargarImagen((ImageView) findViewById(R.id.imageView1)).execute("https://www.365imagenesbonitas.com/wp-content/uploads/2014/07/imagenes-positivas-300x225.jpg"));
        //Bitmap obtener_imagen = get_imagen("https://www.365imagenesbonitas.com/wp-content/uploads/2014/07/imagenes-positivas-300x225.jpg");
        //tuImageView.setImageBitmap(obtener_imagen);
        titulos.add(nombre);
        //imagen.add(obtener_imagen);
        //imagen.add();
        imagen.add(foto);
        /*titulos.add("este es 2");
        imagen.add(R.drawable.house);
        titulos.add("este es 3");
        imagen.add(R.drawable.house);
        titulos.add("este es 4");
        imagen.add(R.drawable.house);*/

    }

/*
    private Bitmap get_imagen(String url) {
        Bitmap bm = null;
        try {
            URL _url = new URL(url);
            URLConnection con = _url.openConnection();
            con.connect();
            InputStream is = con.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {

        }
        return bm;
    }
    */
}
