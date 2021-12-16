package com.example.centroloyola.registros;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.centroloyola.R;
import com.example.centroloyola.db.DbRegistro;

public class InsertRegistroActivity extends AppCompatActivity {

    EditText nombre,grado, curso;
    Button guardar, select_grado;
    private String[] array_grado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_registro);

        nombre=(EditText)findViewById(R.id.editTextNombre);
        //grado=(EditText)findViewById(R.id.editTextGrado);
        curso=(EditText)findViewById(R.id.editTextCurso);
        guardar=(Button) findViewById(R.id.buttonGuardarRegistro);
        select_grado=(Button) findViewById(R.id.spn_state);

        array_grado = getResources().getStringArray(R.array.grados);
        select_grado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showStateChoiceDialog();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbRegistro db= new DbRegistro(InsertRegistroActivity.this);
                long id=db.insertarRegistro(nombre.getText().toString(),select_grado.getText().toString(),curso.getText().toString());
                if(id>0){
                    Toast.makeText(InsertRegistroActivity.this,"Registro guardado",Toast.LENGTH_SHORT).show();
                    limpiar();;
                }else{
                    Toast.makeText(InsertRegistroActivity.this,"Error al guardar registro",Toast.LENGTH_SHORT).show();
                }
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
                select_grado.setTextColor(Color.BLACK);
                select_grado.setText(array_grado[which]);
            }
        });
        builder.show();
    }


    private void limpiar(){
        nombre.setText("");
        //grado.setText("");
        select_grado.setText("");
        curso.setText("");
    }

}
