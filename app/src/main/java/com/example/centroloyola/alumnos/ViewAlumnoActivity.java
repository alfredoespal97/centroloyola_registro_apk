package com.example.centroloyola.alumnos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.centroloyola.ListAlumnoActivity;
import com.example.centroloyola.MainActivity;
import com.example.centroloyola.R;
import com.example.centroloyola.db.DbAlumno;
import com.example.centroloyola.db.DbRegistro;
import com.example.centroloyola.entidades.Alumno;
import com.example.centroloyola.entidades.Registro;
import com.example.centroloyola.registros.ViewRegistroActivity;

public class ViewAlumnoActivity extends AppCompatActivity {

    EditText nombre,edad, escuela,npadre,nmadre,notro,direccion,nota;
    Button guardar, eliminar;
    Alumno alumno;
    int id=0;
    boolean correcto=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_alumno);

        nombre=(EditText)findViewById(R.id.editTextNombre);
        edad=(EditText)findViewById(R.id.editTextEdad);
        escuela=(EditText)findViewById(R.id.editTextEscuela);
        npadre=(EditText)findViewById(R.id.editTextPadre);
        nmadre=(EditText)findViewById(R.id.editTextMadre);
        notro=(EditText)findViewById(R.id.editTextOtroNumero);
        nota=(EditText)findViewById(R.id.editTextNota);
        direccion=(EditText)findViewById(R.id.editTextDireccion);
        guardar=(Button) findViewById(R.id.buttonGuardarAlumno);

        guardar=(Button)findViewById(R.id.buttonGuardarAlumno);
        eliminar=(Button)findViewById(R.id.buttonEliminarAlumno);

        if(savedInstanceState == null){
            Bundle extras=getIntent().getExtras();
            if(extras==null){
                id= Integer.parseInt(null);
            }else{
                id=extras.getInt("ID");
            }
        }else{
            id=(int)savedInstanceState.getSerializable("ID");
        }

        final DbAlumno dbAlumno =new DbAlumno(ViewAlumnoActivity.this);
        alumno=dbAlumno.verAlumno(id);
        if(alumno!= null){
            nombre.setText(alumno.getNombre());
            edad.setText(String.valueOf(alumno.getEdad()));
            escuela.setText(alumno.getEscuela());
            npadre.setText(alumno.getNumero_padre());
            nmadre.setText(alumno.getNumero_madre());
            notro.setText(alumno.getNumero_otro());
            nota.setText(alumno.getNota());
            direccion.setText(alumno.getDireccion());
        }

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!nombre.getText().toString().equals("") && !edad.getText().toString().equals("")&& !escuela.getText().toString().equals("") ){
                    correcto=dbAlumno.editarAlumno(id,
                            nombre.getText().toString(),
                            Integer.parseInt(edad.getText().toString()),
                            escuela.getText().toString(),
                            npadre.getText().toString(),
                            nmadre.getText().toString(),
                            notro.getText().toString(),
                            direccion.getText().toString(),
                            nota.getText().toString());
                    if(correcto){
                        Toast.makeText(ViewAlumnoActivity.this,"Alumno modificado",Toast.LENGTH_SHORT).show();
                        lista();
                    }else{
                        Toast.makeText(ViewAlumnoActivity.this,"Error al modificar los datos del alumno",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ViewAlumnoActivity.this,"Debe llenar los campos de Nombre, Edad y Escuela",Toast.LENGTH_SHORT).show();
                }
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(ViewAlumnoActivity.this);
                builder.setMessage("¿Desea eliminar los datos del alumno?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(dbAlumno.eliminarAlumno(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });

    }

    private void lista(){
        Intent intent=new Intent(this, ListAlumnoActivity.class);
        intent.putExtra("ID",String.valueOf(alumno.getId_registro()));
        startActivity(intent);
    }
}
