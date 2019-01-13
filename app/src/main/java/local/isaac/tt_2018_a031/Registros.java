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
    private RecyclerAdapter rAdapter = new RecyclerAdapter(Registros.this,titulos,imagen);;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        sharedpreferences = getSharedPreferences(preferencias, Context.MODE_PRIVATE);
        conductorViewModel = ViewModelProviders.of(this).get(ConductorViewModel.class);
        trolebusViewModel = ViewModelProviders.of(this).get(TrolebusViewModel.class);
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
                titulos.clear();
                imagen.clear();
                if(position == 0){
                    ////TROLEBUS
                    trolebusViewModel.getTrolebusResponse().observe(Registros.this, (TrolebusPDO trolebusResponse) -> {
                        procesarRespuestaTrolebus(trolebusResponse);
                    });
                }
                else {
                    ///CONDUCTORES
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


    public void procesarRespuestaConductor(ConductorPDO conductorResponse){
        if(conductorResponse.getConductorResponse() != null){
            List<ConductorRegistro> conductores = conductorResponse.getConductorResponse().getListaCondcutor();
            for(ConductorRegistro conductor: conductores)
                saveDataConductor(conductor.getNombre(),conductor.getFoto());

            rAdapter = new RecyclerAdapter(Registros.this,titulos,imagen);
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
            for(TrolebusRegistro trolebus: trolebuses)
                saveDataTrolebus(trolebus.getIdtrolebus());

            rAdapter = new RecyclerAdapter(Registros.this,titulos);
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


    public void saveDataConductor(String nombre,String foto){

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("nombre", nombre);
        editor.putString("foto",foto);
        editor.putBoolean("activity_executed", true);
        editor.commit();
        titulos.add(nombre);
        imagen.add(foto);

    }

    public void saveDataTrolebus(String idtrolebus){
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("id_trolebus",idtrolebus);
        editor.putBoolean("activity_executed",true);
        editor.commit();
        titulos.add(idtrolebus);
    }
}
