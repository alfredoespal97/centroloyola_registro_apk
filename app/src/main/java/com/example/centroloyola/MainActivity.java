package com.example.centroloyola;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.centroloyola.adaptadores.ListaRegistrosAdapter;
import com.example.centroloyola.db.DbRegistro;
import com.example.centroloyola.entidades.Registro;
import com.example.centroloyola.registros.InsertRegistroActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton add;

    RecyclerView listadoRegistro;
    ArrayList<Registro> listaArrayRegistros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        DbHelper dbHelper=new DbHelper(MainActivity.this);
//        SQLiteDatabase db=dbHelper.getWritableDatabase();
//        if(db!=null){
//            Toast.makeText(getApplicationContext(),"BD creada exitosamente",Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(getApplicationContext(),"Fallo en crear BD",Toast.LENGTH_SHORT).show();
//        }

        listadoRegistro=(RecyclerView) findViewById(R.id.listRegistro);
        listadoRegistro.setLayoutManager(new LinearLayoutManager(this));

        DbRegistro dbRegistro=new DbRegistro(MainActivity.this);

        listaArrayRegistros=new ArrayList<>();

        ListaRegistrosAdapter adapter=new ListaRegistrosAdapter(dbRegistro.mosrtarRegistros());

        listadoRegistro.setAdapter(adapter);

        add=(FloatingActionButton)findViewById(R.id.floatingActionButtonAgregarRegistro);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, InsertRegistroActivity.class));
            }
        });


    }
}
