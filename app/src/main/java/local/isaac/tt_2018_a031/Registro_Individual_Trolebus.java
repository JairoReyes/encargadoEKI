package local.isaac.tt_2018_a031;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import local.isaac.tt_2018_a031.PDO.RegistroTrolebusPDO;
import local.isaac.tt_2018_a031.PDO.RegistroTrolebusRegistro;
import local.isaac.tt_2018_a031.viewmodel.RegistroTrolebusViewModel;

public class Registro_Individual_Trolebus extends AppCompatActivity implements View.OnClickListener {


    public static final String preferencias = "MyPref" ;
    SharedPreferences sharedpreferences;

    private EditText fecha_inicio;
    private EditText fecha_final;

    private String id_trolebus;

    private ImageButton calendario1;
    private ImageButton calendario2;
    private int dia,mes,ano;
    private int d,m,a,d2,m2,a2;

    private Calendar calendario;
    private Calendar c;

    private SimpleDateFormat sdf;
    private Date fecha;

    private RecyclerView recyclerView;


    private ArrayList<String> nombres = new ArrayList<String>();
    private ArrayList<String> apellidos = new ArrayList<String>();
    private ArrayList<String> fecha_i = new ArrayList<String>();
    private ArrayList<String> parada_inicio = new ArrayList<String>();
    private ArrayList<String> fecha_f = new ArrayList<String>();
    private ArrayList<String> parada_fin = new ArrayList<String>();
    private ArrayList<String> tiempo = new ArrayList<String>();
    private ArrayList<String> calificacion = new ArrayList<String>();

    private RegistroRecyclerAdapter_Trolebus rAdapter;

    private RegistroTrolebusViewModel registroTrolebusViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_individual_trolebus);


        sharedpreferences = getSharedPreferences(preferencias, Context.MODE_PRIVATE);

        registroTrolebusViewModel = ViewModelProviders.of(this).get(RegistroTrolebusViewModel.class);

        fecha_inicio = (EditText) findViewById(R.id.fecha_inicial);
        fecha_final = (EditText) findViewById(R.id.fecha_final);

        calendario1 = (ImageButton) findViewById(R.id.calendario1);
        calendario2 = (ImageButton) findViewById(R.id.calendario2);

        calendario1.setOnClickListener(this);
        calendario2.setOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        rAdapter = new RegistroRecyclerAdapter_Trolebus(Registro_Individual_Trolebus.this,nombres,apellidos,fecha_i,parada_inicio,fecha_f,parada_fin,tiempo,calificacion);

        //////ESTE PEDAZO DE CODIGO ES PARA PONER LA FECHA MINIMA
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fecha = sdf.parse("2018-01-01");//ESTA ES LA FECHA MINIMA
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        //HASTA ACA ACABA PARA PONER LA FECHA MINIMA

        //ESTA ES PARA FECHA ACTUAL
        c = Calendar.getInstance();
        ano = c.get(Calendar.YEAR);
        mes = c.get(Calendar.MONTH);
        dia = c.get(Calendar.DAY_OF_MONTH);

        d2=d=dia;
        m2=m=mes+1;
        a2=a=ano;
        //HASTA ACA

        //INICIAMOS LOS EDIT TEXT CON LA FECHA ACTUAL
        fecha_inicio.setText(ano+"-"+(mes+1)+"-"+dia);
        fecha_final.setText(ano+"-"+(mes+1)+"-"+dia);

        id_trolebus = getIntent().getExtras().getString("id");

        //Toast.makeText(this, fecha_inicio.getText().toString() + " 00:00:00", Toast.LENGTH_LONG).show();

        //registroTrolebusViewModel.getRegistroTrolebusResponse(id_trolebus,fecha_inicio.getText().toString()+ " 00:00:00",fecha_final.getText().toString() + " 23:59:59").observe(Registro_Individual_Trolebus.this, (RegistroTrolebusPDO registroTrolebusResponse) -> {
        registroTrolebusViewModel.setRegistroTrolebusResponse(null);
        registroTrolebusViewModel.getRegistroTrolebusResponse(id_trolebus,fecha_inicio.getText().toString() + " 00:00:00",fecha_final.getText().toString() + " 23:59:59").observe(Registro_Individual_Trolebus.this, (RegistroTrolebusPDO registroTrolebusResponse) -> {
            procesarRespuestaRegistroTrolebus(registroTrolebusResponse);
        });


    }

    @Override
    public void onClick(View v) {



        if(v == calendario1){


            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    d=dayOfMonth;
                    m=month;
                    a=year;

                    if(year<a2){
                        fecha_inicio.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                        registroTrolebusViewModel.setRegistroTrolebusResponse(null);
                        registroTrolebusViewModel.getRegistroTrolebusResponse(id_trolebus,fecha_inicio.getText().toString() + " 00:00:00",fecha_final.getText().toString() + " 23:59:59").observe(Registro_Individual_Trolebus.this, (RegistroTrolebusPDO registroTrolebusResponse) -> {
                            procesarRespuestaRegistroTrolebus(registroTrolebusResponse);
                        });
                    }
                    else{
                        if(year == a2){
                            if(month < m2){
                                fecha_inicio.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                                registroTrolebusViewModel.setRegistroTrolebusResponse(null);
                                registroTrolebusViewModel.getRegistroTrolebusResponse(id_trolebus,fecha_inicio.getText().toString() + " 00:00:00",fecha_final.getText().toString() + " 23:59:59").observe(Registro_Individual_Trolebus.this, (RegistroTrolebusPDO registroTrolebusResponse) -> {
                                    procesarRespuestaRegistroTrolebus(registroTrolebusResponse);
                                });
                            }
                            else{
                                if(month == m2){
                                    if(dayOfMonth <= d2){
                                        fecha_inicio.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                                        registroTrolebusViewModel.setRegistroTrolebusResponse(null);
                                        registroTrolebusViewModel.getRegistroTrolebusResponse(id_trolebus,fecha_inicio.getText().toString() + " 00:00:00",fecha_final.getText().toString() + " 23:59:59").observe(Registro_Individual_Trolebus.this, (RegistroTrolebusPDO registroTrolebusResponse) -> {
                                            procesarRespuestaRegistroTrolebus(registroTrolebusResponse);
                                        });
                                    }
                                    else{
                                        Toast.makeText(view.getContext(),R.string.fecha_erronea2, Toast.LENGTH_LONG).show();
                                    }
                                }
                                else{
                                    Toast.makeText(view.getContext(),R.string.fecha_erronea2, Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                        else{
                            Toast.makeText(view.getContext(),R.string.fecha_erronea2, Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
                    ,ano,mes,dia);
            datePickerDialog.getDatePicker().setMinDate(calendario.getTimeInMillis());
            datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
            datePickerDialog.show();
        }
        else if(v == calendario2){

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    d2=dayOfMonth;
                    m2=month;
                    a2=year;
                    if(year>a){
                        fecha_final.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                        registroTrolebusViewModel.setRegistroTrolebusResponse(null);
                        registroTrolebusViewModel.getRegistroTrolebusResponse(id_trolebus,fecha_inicio.getText().toString() + " 00:00:00",fecha_final.getText().toString() + " 23:59:59").observe(Registro_Individual_Trolebus.this, (RegistroTrolebusPDO registroTrolebusResponse) -> {
                            procesarRespuestaRegistroTrolebus(registroTrolebusResponse);
                        });
                    }
                    else{
                        if(year == a){
                            if(month > m){
                                fecha_final.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                                registroTrolebusViewModel.setRegistroTrolebusResponse(null);
                                registroTrolebusViewModel.getRegistroTrolebusResponse(id_trolebus,fecha_inicio.getText().toString() + " 00:00:00",fecha_final.getText().toString() + " 23:59:59").observe(Registro_Individual_Trolebus.this, (RegistroTrolebusPDO registroTrolebusResponse) -> {
                                    procesarRespuestaRegistroTrolebus(registroTrolebusResponse);
                                });
                            }
                            else{
                                if(month == m){
                                    if(dayOfMonth >= d){
                                        fecha_final.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                                        registroTrolebusViewModel.setRegistroTrolebusResponse(null);
                                        registroTrolebusViewModel.getRegistroTrolebusResponse(id_trolebus,fecha_inicio.getText().toString() + " 00:00:00",fecha_final.getText().toString() + " 23:59:59").observe(Registro_Individual_Trolebus.this, (RegistroTrolebusPDO registroTrolebusResponse) -> {
                                            procesarRespuestaRegistroTrolebus(registroTrolebusResponse);
                                        });
                                    }
                                    else{
                                        Toast.makeText(view.getContext(),R.string.fecha_erronea, Toast.LENGTH_LONG).show();
                                    }
                                }
                                else{
                                    Toast.makeText(view.getContext(),R.string.fecha_erronea, Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                        else{
                            Toast.makeText(view.getContext(),R.string.fecha_erronea, Toast.LENGTH_LONG).show();
                        }
                    }

                }
            }
                    ,ano,mes,dia);


            datePickerDialog.getDatePicker().setMinDate(calendario.getTimeInMillis());
            datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
            datePickerDialog.show();
        }

    }







    public void procesarRespuestaRegistroTrolebus(RegistroTrolebusPDO registroTrolebusResponse){


        if(registroTrolebusResponse.getRegistroTrolebusResponse() != null){

            nombres.clear();
            apellidos.clear();
            fecha_i.clear();
            parada_inicio.clear();
            fecha_f.clear();
            parada_fin.clear();
            tiempo.clear();
            calificacion.clear();

            List<RegistroTrolebusRegistro> registros = registroTrolebusResponse.getRegistroTrolebusResponse().getListaRegistroTrolebus();

            if(registros != null) {
                for (RegistroTrolebusRegistro registro : registros)
                    saveDataRegistroTrolebus(registro.getNombre(), registro.getApellido(), registro.getFecha_inicio(), registro.getParada_inicio(), registro.getFecha_fin(), registro.getParada_fin(), registro.getTiempo(), registro.getCalificacion());
            }
            else
                Toast.makeText(this, "No hay registros", Toast.LENGTH_SHORT).show();

            rAdapter.notifyDataSetChanged();
            rAdapter = new RegistroRecyclerAdapter_Trolebus(Registro_Individual_Trolebus.this, nombres, apellidos, fecha_i, parada_inicio, fecha_f, parada_fin, tiempo, calificacion);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.addItemDecoration(new DividerItemDecoration(Registro_Individual_Trolebus.this, LinearLayoutManager.VERTICAL));
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(rAdapter);
        }
        else{
            Toast httpError = Toast.makeText(this, "Fallo al recibir  registro de Trolebus", Toast.LENGTH_SHORT);
            httpError.show();
            registroTrolebusViewModel.setRegistroTrolebusResponse(null);
        }
    }


    public void saveDataRegistroTrolebus(String nombre,String apellido, String fechasi, String paradasi, String fechasf, String paradasf, String tiempos, String califs){

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("ynombre", nombre);
        editor.putString("yapellido",apellido);
        editor.putString("yfechai", fechasi);
        editor.putString("yparadai",paradasi);
        editor.putString("yfechaf", fechasf);
        editor.putString("yparadaf",paradasf);
        editor.putString("ytiempo", tiempos);
        editor.putString("ycalif",califs);
        editor.putBoolean("yactivity_executed", true);
        editor.commit();

        nombres.add(nombre);
        apellidos.add(apellido);
        fecha_i.add(fechasi);
        parada_inicio.add(paradasi);
        fecha_f.add(fechasf);
        parada_fin.add(paradasf);
        tiempo.add(tiempos);
        calificacion.add(califs);
    }
}

