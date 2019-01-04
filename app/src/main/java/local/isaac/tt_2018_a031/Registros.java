package local.isaac.tt_2018_a031;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import local.isaac.tt_2018_a031.model.Parada;

public class Registros extends AppCompatActivity {

    private Spinner mSpinner;
    ListViewAdapter adapter;
    private RecyclerView recyclerView;
    ArrayList<String> titulos = new ArrayList<String>();
    ArrayList<Integer> imagen = new ArrayList<Integer>();
    private RecyclerAdapter rAdapter = new RecyclerAdapter(Registros.this,titulos,imagen);;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);

        mSpinner = (Spinner) findViewById(R.id.mSpinner);
        //listview = (ListView) findViewById(R.id.listView);
        ArrayList<String> elementos = new ArrayList<>();
        elementos.add("Unidad de trolebús");
        elementos.add("Nombre de conductor");
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        ArrayAdapter adp = new ArrayAdapter(this,android.R.layout.simple_spinner_item,elementos);
        mSpinner.setAdapter(adp);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String elemento = (String) mSpinner.getAdapter().getItem(position);
                Toast.makeText(parent.getContext(),"este es " + elemento + " " + position, Toast.LENGTH_SHORT).show();
                if(position == 0){

                    titulos.clear();
                    imagen.clear();
                    titulos.add("este es 1");
                    imagen.add(R.drawable.descarga);
                    titulos.add("este es 2");
                    imagen.add(R.drawable.descarga);
                    titulos.add("este es 3");
                    imagen.add(R.drawable.descarga);
                    titulos.add("este es 4");
                    imagen.add(R.drawable.descarga);


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
                    titulos.add("este es 1");
                    imagen.add(R.drawable.house);
                    titulos.add("este es 2");
                    imagen.add(R.drawable.house);
                    titulos.add("este es 3");
                    imagen.add(R.drawable.house);
                    titulos.add("este es 4");
                    imagen.add(R.drawable.house);

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
}
