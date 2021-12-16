package com.example.centroloyola.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.centroloyola.ListAlumnoActivity;
import com.example.centroloyola.R;
import com.example.centroloyola.registros.ViewRegistroActivity;
import com.example.centroloyola.entidades.Registro;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListaRegistrosAdapter extends RecyclerView.Adapter<ListaRegistrosAdapter.RegistroViewHolder>{
    ArrayList<Registro> listaResgitros;

    public ListaRegistrosAdapter(ArrayList<Registro> listaResgitros){
        this.listaResgitros=listaResgitros;
    }
    @NonNull
    @Override
    public RegistroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_registro,null,false);
        return new RegistroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegistroViewHolder holder, int position) {
        holder.viewnombre.setText(listaResgitros.get(position).getNombre());
        holder.viewgrado.setText(listaResgitros.get(position).getGrado());
        holder.viewcurso.setText(listaResgitros.get(position).getCurso());
    }

    @Override
    public int getItemCount() {
        return listaResgitros.size();
    }

    public class RegistroViewHolder extends RecyclerView.ViewHolder {

        TextView viewnombre,viewgrado,viewcurso;
        public RegistroViewHolder(@NonNull View itemView) {
            super(itemView);
            viewnombre=itemView.findViewById(R.id.viewNombre);
            viewgrado=itemView.findViewById(R.id.viewGrado);
            viewcurso=itemView.findViewById(R.id.viewCurso);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id= String.valueOf(listaResgitros.get(getAdapterPosition()).getId());
                    Context context=view.getContext();
                    Intent intent=new Intent(context, ListAlumnoActivity.class);
                    intent.putExtra("ID",id);
                    intent.putExtra("NAME",listaResgitros.get(getAdapterPosition()).getNombre());
                    //Toast.makeText(context,id,Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);
                }
            });
        }
    }
}
