package com.example.centroloyola.alumnos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.centroloyola.R;
import com.example.centroloyola.db.DbAlumno;

public class InsertAlumnoActivity extends AppCompatActivity {

    EditText nombre,edad, escuela,npadre,nmadre,notro,direccion,nota;
    Button guardar;
    int id_registro=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_alumno);

        nombre=(EditText)findViewById(R.id.editTextNombre);
        edad=(EditText)findViewById(R.id.editTextEdad);
        escuela=(EditText)findViewById(R.id.editTextEscuela);
        npadre=(EditText)findViewById(R.id.editTextPadre);
        nmadre=(EditText)findViewById(R.id.editTextMadre);
        notro=(EditText)findViewById(R.id.editTextOtroNumero);
        nota=(EditText)findViewById(R.id.editTextNota);
        direccion=(EditText)findViewById(R.id.editTextDireccion);
        guardar=(Button) findViewById(R.id.buttonGuardarAlumno);

        Bundle bundle=getIntent().getExtras();
        if(bundle != null){
            id_registro= bundle.getInt("ID");
        }

        Toast.makeText(InsertAlumnoActivity.this,String.valueOf(id_registro),Toast.LENGTH_SHORT).show();

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbAlumno db= new DbAlumno(InsertAlumnoActivity.this);
                long id=db.insertarAlumno(nombre.getText().toString(), Integer.parseInt(edad.getText().toString()),escuela.getText().toString(), npadre.getText().toString(),
                        nmadre.getText().toString(),notro.getText().toString(),direccion.getText().toString(),nota.getText().toString(),id_registro);
                if(id>0){
                    Toast.makeText(InsertAlumnoActivity.this,"Alumno guardado",Toast.LENGTH_SHORT).show();
                    limpiar();
                }else{
                    Toast.makeText(InsertAlumnoActivity.this,"Error al guardar alumno",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void limpiar() {
        nombre.setText("");
        edad.setText("");
        escuela.setText("");
        nmadre.setText("");
        npadre.setText("");
        notro.setText("");
        direccion.setText("");
        nota.setText("");
    }
}
