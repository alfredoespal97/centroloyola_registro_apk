package com.example.centroloyola.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.centroloyola.entidades.Alumno;

import java.util.ArrayList;

public class DbCalendario extends DbHelper {

    Context context;

    public DbCalendario(Context context) {
        super(context);
        this.context=context;
    }

    public long insertarAsistencia(String fecha,int id_registro, int id_alumno){
        long id=0;
        try {
            DbHelper dbHelper=new DbHelper(context);
            SQLiteDatabase db=dbHelper.getWritableDatabase();

            ContentValues values= new ContentValues();
            values.put("fecha",fecha);
            values.put("id_registro",id_registro);
            values.put("id_alumno",id_alumno);
            id=db.insert(TABLE_CALENDARIO,null,values);
        }catch (Exception ex){
            ex.toString();
        }

        return id;
    }



    public boolean editarAsistencia(int id,String fecha,String id_alumno){
        boolean correcto=false;

        DbHelper dbHelper=new DbHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        try {
            db.execSQL(" UPDATE "+TABLE_CALENDARIO+" SET fecha='"+fecha+"'," +
                    "id_alumno='"+id_alumno+"' WHERE id_calendario='"+id+"' ");
            correcto=true;
        }catch (Exception ex){
            ex.toString();
            correcto=false;
        }finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarAsistencia(int id){
        boolean correcto=false;

        DbHelper dbHelper=new DbHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        try {
            db.execSQL(" DELETE FROM "+TABLE_CALENDARIO+" WHERE id_calendario='"+id+"'");
            correcto=true;
        }catch (Exception ex){
            ex.toString();
            correcto=false;
        }finally {
            db.close();
        }

        return correcto;
    }
}
