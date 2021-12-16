package com.example.centroloyola.adaptadores;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.centroloyola.ListAlumnoActivity;
import com.example.centroloyola.R;
import com.example.centroloyola.alumnos.InsertAlumnoActivity;
import com.example.centroloyola.alumnos.ViewAlumnoActivity;
import com.example.centroloyola.db.DbAlumno;
import com.example.centroloyola.entidades.Alumno;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListaAlumnosAdapter extends RecyclerView.Adapter<ListaAlumnosAdapter.AlumnoViewHolder>{
    ArrayList<Alumno> listaAlumnos;

    public ListaAlumnosAdapter(ArrayList<Alumno> listaAlumnos){
        this.listaAlumnos=listaAlumnos;
    }

    @NonNull
    @Override
    public AlumnoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_alumno,null,false);
        return new AlumnoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlumnoViewHolder holder, int position) {
        holder.nombre.setText(listaAlumnos.get(position).getNombre());
        holder.edad.setText(String.valueOf(listaAlumnos.get(position).getEdad()));
    }

    @Override
    public int getItemCount() {
        return listaAlumnos.size();
    }

    public class AlumnoViewHolder extends RecyclerView.ViewHolder {

        TextView nombre,edad;
        public AlumnoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.txtViewNombre);
            edad=itemView.findViewById(R.id.txtViewEdad);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context=view.getContext();
                    Intent intent=new Intent(context, ViewAlumnoActivity.class);
                    intent.putExtra("ID",listaAlumnos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }


    }
}
