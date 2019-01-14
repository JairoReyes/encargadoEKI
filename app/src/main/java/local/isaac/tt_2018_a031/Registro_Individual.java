package local.isaac.tt_2018_a031;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Registro_Individual extends AppCompatActivity implements View.OnClickListener {

    private EditText fecha_inicio;
    private EditText fecha_final;

    private ImageButton calendario1;
    private ImageButton calendario2;
    private int dia,mes,ano;
    private int d,m,a;

    private Calendar calendario;
    private Calendar c;

    private SimpleDateFormat sdf;
    private Date fecha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_individual);

        fecha_inicio = (EditText) findViewById(R.id.fecha_inicial);
        fecha_final = (EditText) findViewById(R.id.fecha_final);

        calendario1 = (ImageButton) findViewById(R.id.calendario1);
        calendario2 = (ImageButton) findViewById(R.id.calendario2);

        calendario1.setOnClickListener(this);
        calendario2.setOnClickListener(this);

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

        d=dia;
        m=mes+1;
        a=ano;
        //HASTA ACA

        //INICIAMOS LOS EDIT TEXT CON LA FECHA ACTUAL
        fecha_inicio.setText(dia+"/"+(mes+1)+"/"+ano);
        fecha_final.setText(dia+"/"+(mes+1)+"/"+ano);




    }

    @Override
    public void onClick(View v) {
        if(v == calendario1){


            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    fecha_inicio.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    d=dayOfMonth;
                    m=month;
                    a=year;
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
                    if(year>a){
                        fecha_final.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                    else{
                        if(year == a){
                            if(month > m){
                                fecha_final.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                            }
                            else{
                                if(month == m){
                                    if(dayOfMonth >= d){
                                        fecha_final.setText(dayOfMonth+"/"+(month+1)+"/"+year);
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
}
