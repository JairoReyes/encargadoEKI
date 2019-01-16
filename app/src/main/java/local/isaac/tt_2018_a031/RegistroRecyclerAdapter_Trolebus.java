package local.isaac.tt_2018_a031;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RegistroRecyclerAdapter_Trolebus extends RecyclerView.Adapter<RegistroRecyclerAdapter_Trolebus.MyViewHolder> {

    Context context;
    ArrayList<String> nombre;
    ArrayList<String> apellido;
    ArrayList<String> fecha_i;
    ArrayList<String> parada_inicio;
    ArrayList<String> fecha_f;
    ArrayList<String> parada_fin;
    ArrayList<String> tiempo;
    ArrayList<String> calificacion;

    LayoutInflater inflater;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tnombre,tapellido,tfecha_i,tparada_i,tfecha_f,tparada_f,ttiempo,tcalificacion,ttitulo;

        public MyViewHolder(View view) {
            super(view);
            tnombre = (TextView) view.findViewById(R.id.texto_nombre);
            tapellido = (TextView) view.findViewById(R.id.texto_apellido);
            tfecha_i = (TextView) view.findViewById(R.id.texto_fechaI);
            tparada_i = (TextView) view.findViewById(R.id.texto_paradaI);
            tfecha_f = (TextView) view.findViewById(R.id.texto_fechaF);
            tparada_f = (TextView) view.findViewById(R.id.texto_paradaF);
            ttiempo = (TextView) view.findViewById(R.id.texto_tiempo);
            tcalificacion = (TextView) view.findViewById(R.id.texto_calificacion);
            ttitulo = (TextView) view.findViewById(R.id.titulo);
            //year = (TextView) view.findViewById(R.id.year);
        }
    }

    /*
        public RecyclerAdapter(List<Movie> moviesList) {
            this.moviesList = moviesList;
        }
    */
    public RegistroRecyclerAdapter_Trolebus(Context context, ArrayList<String> nombre, ArrayList<String> apellido, ArrayList<String> fecha_i, ArrayList<String> parada_inicio, ArrayList<String> fecha_f, ArrayList<String> parada_fin, ArrayList<String> tiempo, ArrayList<String> calificacion ) {
        this.context = context;
        this.nombre = nombre;
        this.apellido = apellido;
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
        View itemView = inflater.inflate(R.layout.registros_trolebus_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //Movie movie = moviesList.get(position);
        //holder.title.setText(titulos.get(position));
        holder.ttitulo.setText("Registro " + (position+1));
        holder.tnombre.setText(nombre.get(position));
        holder.tapellido.setText(apellido.get(position));
        holder.tfecha_i.setText(fecha_i.get(position));
        holder.tparada_i.setText(parada_inicio.get(position));
        holder.tfecha_f.setText(fecha_f.get(position));
        holder.tparada_f.setText(parada_fin.get(position));
        holder.ttiempo.setText(tiempo.get(position));
        holder.tcalificacion.setText(calificacion.get(position));
        //holder.genre.setText(movie.getGenre());
        //if(imagenes == null)
        //  Picasso.get().load(R.drawable.descarga).into(holder.image);
        //else
        //  Picasso.get().load(imagenes.get(position)).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return nombre.size();
    }
}