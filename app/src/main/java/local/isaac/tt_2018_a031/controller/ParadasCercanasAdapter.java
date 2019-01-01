package local.isaac.tt_2018_a031.controller;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import local.isaac.tt_2018_a031.R;
import local.isaac.tt_2018_a031.model.Parada;

public class ParadasCercanasAdapter extends RecyclerView.Adapter<ParadasCercanasAdapter.MyViewHolder> {


    private PackageManager mPackageManager;
    private List<Parada> paradasCercanas;
    private Context mContext;

    public ParadasCercanasAdapter(Context context, List<Parada> paradasCercanas) {
        this.paradasCercanas = paradasCercanas;
        mContext = context;
        mPackageManager = mContext.getPackageManager();
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(parent.getContext());
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Parada parada = paradasCercanas.get(position);
        double distancia = parada.getDistanciaA();
        if (distancia < 1000) {
            DecimalFormat df = new DecimalFormat("#");
            holder.nombreParada.setText(parada.getNombre());
            holder.distanciaParada.setText(df.format(distancia) + " mts");
        }
        else{
            distancia = distancia/1000;
            DecimalFormat df = new DecimalFormat("#.###");
            holder.nombreParada.setText(parada.getNombre());
            holder.distanciaParada.setText(df.format(distancia) + " Km");
        }
    }

    @Override
    public int getItemCount() {
        return paradasCercanas == null ? 0 : paradasCercanas.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView miniTrole;
        TextView nombreParada;
        TextView distanciaParada;

        public MyViewHolder(Context context) {
            this(LayoutInflater.from(context).inflate(R.layout.parada_item_list, null));
        }

        private MyViewHolder(View itemView) {
            super(itemView);
            miniTrole = (ImageView) itemView.findViewById(R.id.mini_trole);
            nombreParada = (TextView) itemView.findViewById(R.id.nombre_parada);
            distanciaParada = (TextView) itemView.findViewById(R.id.distancia_a_parada);
        }
    }



}
