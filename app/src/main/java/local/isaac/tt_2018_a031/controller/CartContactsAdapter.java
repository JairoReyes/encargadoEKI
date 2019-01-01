package local.isaac.tt_2018_a031.controller;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import local.isaac.tt_2018_a031.R;
import local.isaac.tt_2018_a031.model.Contacto;

public class CartContactsAdapter extends RecyclerView.Adapter<CartContactsAdapter.MyViewHolder>{

    private Context context;
    private List<Contacto> contactos;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView nombre,numero;
        public RelativeLayout backgroundView,foregroundView;

        public MyViewHolder(View view){
            super(view);
            nombre = view.findViewById(R.id.nombre_contacto);
            numero = view.findViewById(R.id.numero_contacto);
            backgroundView = view.findViewById(R.id.background_view);
            foregroundView = view.findViewById(R.id.foreground_view);
        }
    }

    public CartContactsAdapter(Context context,List<Contacto> contactos){
        this.context = context;
        this.contactos = contactos;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.contact_list_item,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position){
        final Contacto contacto = contactos.get(position);
        holder.nombre.setText(contacto.getNombre());
        holder.numero.setText(contacto.getTelefono());
    }

    @Override
    public int getItemCount(){
        return contactos.size();
    }

    public void removeItem(int position){
        contactos.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(Contacto contacto, int position) {
        contactos.add(position, contacto);
        // notify item added by position
        notifyItemInserted(position);
    }


}
