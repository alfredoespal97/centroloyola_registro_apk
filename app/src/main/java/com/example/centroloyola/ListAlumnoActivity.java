package com.example.centroloyola;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.centroloyola.adaptadores.ListaAlumnosAdapter;
import com.example.centroloyola.alumnos.InsertAlumnoActivity;
import com.example.centroloyola.db.DbAlumno;
import com.example.centroloyola.entidades.Alumno;
import com.example.centroloyola.registros.ViewRegistroActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListAlumnoActivity extends AppCompatActivity {

    private FloatingActionButton add;
    private int id_registro=0;
    private String name;
    private RecyclerView listadoAlumno;
    private ArrayList<Alumno> listaArrayAlumno;
    private DbAlumno dbAlumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_alumno);

        Bundle bundle=getIntent().getExtras();
        if(bundle != null){
            id_registro= Integer.parseInt(bundle.getString("ID"));
            name=bundle.getString("NAME");
        }
        setTitle(name);

        listadoAlumno=(RecyclerView) findViewById(R.id.listAlumnos);
        listadoAlumno.setLayoutManager(new LinearLayoutManager(this));

        dbAlumno=new DbAlumno(ListAlumnoActivity.this);

        listaArrayAlumno=new ArrayList<>();

        refrescarLista();

        add=(FloatingActionButton)findViewById(R.id.floatingActionButtonAgregarAlumno);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(ListAlumnoActivity.this, InsertAlumnoActivity.class);
//                intent.putExtra("ID",id_registro);
//               // Toast.makeText(ListAlumnoActivity.this,String.valueOf(id_registro),Toast.LENGTH_SHORT).show();
//                startActivity(intent);
                showCustomDialog();
            }
        });
    }

    private void refrescarLista() {
        ListaAlumnosAdapter adapter=new ListaAlumnosAdapter(dbAlumno.mostrarAlumnos(id_registro));
        listadoAlumno.setAdapter(adapter);
    }

    private void showCustomDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_add_alumno);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        final EditText nombre=dialog.findViewById(R.id.editTextNombre);
        final EditText edad=dialog.findViewById(R.id.editTextEdad);
        final EditText escuela=dialog.findViewById(R.id.editTextEscuela);
        final EditText npadre=dialog.findViewById(R.id.editTextPadre);
        final EditText nmadre=dialog.findViewById(R.id.editTextMadre);
        final EditText notro=dialog.findViewById(R.id.editTextOtroNumero);
        final EditText nota=dialog.findViewById(R.id.editTextNota);
        final EditText direccion=dialog.findViewById(R.id.editTextDireccion);

        ((ImageButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ((Button) dialog.findViewById(R.id.bt_save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbAlumno db= new DbAlumno(ListAlumnoActivity.this);
                long id=db.insertarAlumno(nombre.getText().toString(),
                        Integer.parseInt(edad.getText().toString()),
                        escuela.getText().toString(),
                        npadre.getText().toString(),
                        nmadre.getText().toString(),
                        notro.getText().toString(),
                        direccion.getText().toString(),
                        nota.getText().toString()
                        ,id_registro);
                if(id>0){
                    Toast.makeText(ListAlumnoActivity.this,"Alumno guardado",Toast.LENGTH_SHORT).show();
                    //limpiar();
                }else{
                    Toast.makeText(ListAlumnoActivity.this,"Error al guardar alumno",Toast.LENGTH_SHORT).show();
                }
                refrescarLista();
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_registro,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.editarRegistro:
                Intent intent=new Intent(ListAlumnoActivity.this, ViewRegistroActivity.class);
                intent.putExtra("ID",id_registro);
                startActivity(intent);
                return true;
            case R.id.calendario:
                Intent i=new Intent(ListAlumnoActivity.this, AssistenActivity.class);
                i.putExtra("ID",id_registro);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
    }
}
