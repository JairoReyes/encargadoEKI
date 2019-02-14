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
import local.isaac.tt_2018_a031.PDO.TrolebusPDO;
import local.isaac.tt_2018_a031.PDO.TrolebusRegistro;
import local.isaac.tt_2018_a031.viewmodel.ConductorViewModel;
import local.isaac.tt_2018_a031.viewmodel.TrolebusViewModel;

public class Registros extends AppCompatActivity {

    private ConductorViewModel conductorViewModel;
    private TrolebusViewModel trolebusViewModel;
    public static final String preferencias = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    private Spinner mSpinner;
    ListViewAdapter adapter;
    private RecyclerView recyclerView;
    ArrayList<String> titulos = new ArrayList<String>();
    ArrayList<String> imagen = new ArrayList<String>();
    ArrayList<String> ids = new ArrayList<String>();
    private RecyclerAdapter rAdapter;
    private int bandera= 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);

        //startService(new Intent(this,ServiceAlarmas.class));
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        sharedpreferences = getSharedPreferences(preferencias, Context.MODE_PRIVATE);
        conductorViewModel = ViewModelProviders.of(this).get(ConductorViewModel.class);
        trolebusViewModel = ViewModelProviders.of(this).get(TrolebusViewModel.class);
        mSpinner = (Spinner) findViewById(R.id.mSpinner);
        //listview = (ListView) findViewById(R.id.listView);
        ArrayList<String> elementos = new ArrayList<>();
        elementos.add("Unidad de trolebús");
        elementos.add("Nombre de conductor");

        rAdapter = new RecyclerAdapter(Registros.this,titulos,imagen,ids);








        ArrayAdapter adp = new ArrayAdapter(this,android.R.layout.simple_spinner_item,elementos);
        mSpinner.setAdapter(adp);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String elemento = (String) mSpinner.getAdapter().getItem(position);
                titulos.clear();
                imagen.clear();
                ids.clear();
                if(position == 0){
                    bandera = 0;
                    ////TROLEBUS
                    trolebusViewModel.setTrolebusResponse(null);
                    trolebusViewModel.getTrolebusResponse().observe(Registros.this, (TrolebusPDO trolebusResponse) -> {
                        procesarRespuestaTrolebus(trolebusResponse);
                    });
                }
                else {
                    bandera = 1;
                    ///CONDUCTORES
                    conductorViewModel.setConductorResponse(null);
                    conductorViewModel.getConductorResponse().observe(Registros.this, (ConductorPDO conductorResponse) -> {
                        procesarRespuestaConductor(conductorResponse);
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {


                TextView nombre = (TextView) view.findViewById(R.id.list_row_id);

                if(bandera == 1) {
                    //stopService(new Intent(Registros.this,ServiceAlarmas.class));
                    Intent intent = new Intent(Registros.this, Registro_Individual_Conductor.class);
                    intent.putExtra("id", nombre.getText().toString());
                    startActivityForResult(intent, 0);
                }
                else if (bandera == 0){
                    //stopService(new Intent(Registros.this,ServiceAlarmas.class));
                    Intent intent = new Intent(Registros.this, Registro_Individual_Trolebus.class);
                    intent.putExtra("id", nombre.getText().toString());
                    startActivityForResult(intent, 0);
                }

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }


    public void procesarRespuestaConductor(ConductorPDO conductorResponse){
        if(conductorResponse.getConductorResponse() != null){
            List<ConductorRegistro> conductores = conductorResponse.getConductorResponse().getListaCondcutor();

            if(conductores != null) {
                for (ConductorRegistro conductor : conductores)
                    saveDataConductor(conductor.getNombre(), conductor.getFoto(), conductor.getIdUsuario());
            }
            else
                Toast.makeText(this, "No hay registros", Toast.LENGTH_SHORT).show();

            rAdapter = new RecyclerAdapter(Registros.this,titulos,imagen,ids);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.addItemDecoration(new DividerItemDecoration(Registros.this, LinearLayoutManager.VERTICAL));
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(rAdapter);
        }
        else{
            Toast httpError = Toast.makeText(this, "Fallo al recibir conductores", Toast.LENGTH_SHORT);
            httpError.show();
            conductorViewModel.setConductorResponse(null);
        }
    }

    public void procesarRespuestaTrolebus(TrolebusPDO trolebusResponse){
        if(trolebusResponse.getTrolebusResponse() != null){
            List<TrolebusRegistro> trolebuses = trolebusResponse.getTrolebusResponse().getListaTrolebus();

            if(trolebuses != null) {
                for (TrolebusRegistro trolebus : trolebuses)
                    saveDataTrolebus(trolebus.getPlaca(), trolebus.getIdtrolebus());
            }
            else
                Toast.makeText(this, "No hay registros", Toast.LENGTH_SHORT).show();

            rAdapter = new RecyclerAdapter(Registros.this,titulos,ids);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.addItemDecoration(new DividerItemDecoration(Registros.this, LinearLayoutManager.VERTICAL));
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(rAdapter);
        }
        else{
            Toast httpError = Toast.makeText(this, "Fallo al recibir conductores", Toast.LENGTH_SHORT);
            httpError.show();
            conductorViewModel.setConductorResponse(null);
        }
    }


    public void saveDataConductor(String nombre,String foto, String id){

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("nombre", nombre);
        editor.putString("foto",foto);
        editor.putString("id",id);
        editor.putBoolean("activity_executed", true);
        editor.commit();
        titulos.add(nombre);
        imagen.add(foto);
        ids.add(id);

    }

    public void saveDataTrolebus(String placa,String id){
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("id_trolebus",id);
        editor.putString("placa",placa);
        editor.putBoolean("activity_executed",true);
        editor.commit();
        titulos.add(placa);
        ids.add(id);
    }

}
