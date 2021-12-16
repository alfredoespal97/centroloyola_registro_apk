package com.example.centroloyola.registros;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.centroloyola.MainActivity;
import com.example.centroloyola.R;
import com.example.centroloyola.db.DbRegistro;
import com.example.centroloyola.entidades.Registro;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ViewRegistroActivity extends AppCompatActivity {

    EditText txtNombre,txtGrado,txtCurso;
    Button guardar, eliminar,grado;
   // FloatingActionButton fabEditar,fabEliminar;
    Registro registro;
    int id=0;
    boolean correcto=false;
    private String[] array_grado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_registro);

        txtNombre=(EditText)findViewById(R.id.editTextNombre);
        //txtGrado=(EditText)findViewById(R.id.editTextGrado);
        txtCurso=(EditText)findViewById(R.id.editTextCurso);

        guardar=(Button)findViewById(R.id.buttonGuardarRegistro);
        eliminar=(Button)findViewById(R.id.buttonEliminarRegistro);
        grado=(Button)findViewById(R.id.spn_state);
        array_grado = getResources().getStringArray(R.array.grados);

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

        final DbRegistro dbRegistro=new DbRegistro(ViewRegistroActivity.this);
        registro=dbRegistro.verRegistro(id);

        if(registro!= null){
            txtNombre.setText(registro.getNombre());
            //txtGrado.setText(registro.getGrado());
            grado.setText(registro.getGrado());
            txtCurso.setText(registro.getCurso());
//            txtNombre.setInputType(InputType.TYPE_NULL);
//            txtGrado.setInputType(InputType.TYPE_NULL);
//            txtCurso.setInputType(InputType.TYPE_NULL);
        }

        grado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showStateChoiceDialog();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtNombre.getText().toString().equals("") && !txtCurso.getText().toString().equals("")&& !grado.getText().toString().equals("")){
                    correcto=dbRegistro.editarRegistro(id,txtNombre.getText().toString(),grado.getText().toString(),txtCurso.getText().toString());
                    if(correcto){
                        Toast.makeText(ViewRegistroActivity.this,"Registro modificado",Toast.LENGTH_SHORT).show();
                        lista();
                    }else{
                        Toast.makeText(ViewRegistroActivity.this,"Error al modificar el registro",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ViewRegistroActivity.this,"Debe llenar los campos",Toast.LENGTH_SHORT).show();
                }
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(ViewRegistroActivity.this);
                builder.setMessage("¿Desea eliminar este registro?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                               //boolean correcto= dbRegistro.eliminarRegistro(id);
                               if(dbRegistro.eliminarRegistro(id)){
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

    private void showStateChoiceDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setSingleChoiceItems(array_grado, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                grado.setTextColor(Color.BLACK);
                grado.setText(array_grado[which]);
            }
        });
        builder.show();
    }


    private void lista(){
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
