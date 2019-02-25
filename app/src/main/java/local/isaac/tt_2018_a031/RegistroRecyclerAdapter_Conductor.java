package local.isaac.tt_2018_a031;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class RegistroRecyclerAdapter_Conductor extends RecyclerView.Adapter<RegistroRecyclerAdapter_Conductor.MyViewHolder> {

    Context context;
    ArrayList<String> placa;
    ArrayList<String> modelo;
    ArrayList<String> fecha_i;
    ArrayList<String> parada_inicio;
    ArrayList<String> fecha_f;
    ArrayList<String> parada_fin;
    ArrayList<String> tiempo;
    ArrayList<String> calificacion;

    LayoutInflater inflater;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tplaca,tmodelo,tfecha_i,tparada_i,tfecha_f,tparada_f,ttiempo,ttitulo;
        public RatingBar tcalificacion;

        public MyViewHolder(View view) {
            super(view);
            tplaca = (TextView) view.findViewById(R.id.texto_placa);
            tmodelo = (TextView) view.findViewById(R.id.texto_modelo);
            tfecha_i = (TextView) view.findViewById(R.id.texto_fechaI);
            tparada_i = (TextView) view.findViewById(R.id.texto_paradaI);
            tfecha_f = (TextView) view.findViewById(R.id.texto_fechaF);
            tparada_f = (TextView) view.findViewById(R.id.texto_paradaF);
            ttiempo = (TextView) view.findViewById(R.id.texto_tiempo);
            tcalificacion = (RatingBar) view.findViewById(R.id.texto_calificacion);
            ttitulo = (TextView) view.findViewById(R.id.titulo);
            //year = (TextView) view.findViewById(R.id.year);
        }
    }

    /*
        public RecyclerAdapter(List<Movie> moviesList) {
            this.moviesList = moviesList;
        }
    */
    public RegistroRecyclerAdapter_Conductor(Context context, ArrayList<String> placa, ArrayList<String> modelo, ArrayList<String> fecha_i, ArrayList<String> parada_inicio, ArrayList<String> fecha_f, ArrayList<String> parada_fin, ArrayList<String> tiempo, ArrayList<String> calificacion ) {
        this.context = context;
        this.placa = placa;
        this.modelo = modelo;
        this.fecha_i = fecha_i;
        this.parada_inicio = parada_inicio;
        this.fecha_f = fecha_f;
        this.parada_fin = parada_fin;
        this.tiempo = tiempo;
        this.calificacion = calificacion;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.registros_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //Movie movie = moviesList.get(position);
        //holder.title.setText(titulos.get(position));
        holder.ttitulo.setText("Registro " + (position+1));
        holder.tplaca.setText(placa.get(position));
        holder.tmodelo.setText(modelo.get(position));
        holder.tfecha_i.setText(fecha_i.get(position));
        holder.tparada_i.setText(parada_inicio.get(position));
        holder.tfecha_f.setText(fecha_f.get(position));
        holder.tparada_f.setText(parada_fin.get(position));
        holder.ttiempo.setText(tiempo.get(position));
        holder.tcalificacion.setRating(Float.parseFloat(calificacion.get(position)));
        //holder.tcalificacion.setText(calificacion.get(position));
        //holder.genre.setText(movie.getGenre());
        //if(imagenes == null)
          //  Picasso.get().load(R.drawable.descarga).into(holder.image);
        //else
          //  Picasso.get().load(imagenes.get(position)).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return placa.size();
    }
}