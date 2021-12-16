package com.example.centroloyola.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.centroloyola.entidades.Registro;

import java.util.ArrayList;
import java.util.Currency;

public class DbRegistro extends DbHelper {
    Context context;

    public DbRegistro(Context context) {
        super(context);
        this.context=context;
    }

    public long insertarRegistro(String nombre,String grado, String curso){
        long id=0;
        try {
            DbHelper dbHelper=new DbHelper(context);
            SQLiteDatabase db=dbHelper.getWritableDatabase();

            ContentValues values= new ContentValues();
            values.put("nombre",nombre);
            values.put("grado",grado);
            values.put("curso",curso);
            id=db.insert(TABLE_REGISTROS,null,values);
        }catch (Exception ex){
            ex.toString();
        }

        return id;
    }

    public ArrayList<Registro> mosrtarRegistros(){
        DbHelper dbHelper=new DbHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        ArrayList<Registro> listado=new ArrayList<>();
        Registro registro=null;
        Cursor cursorRegistro=null;

        cursorRegistro=db.rawQuery("SELECT * FROM "+ TABLE_REGISTROS,null);

        if(cursorRegistro.moveToFirst()){
            do{
                registro=new Registro();
                registro.setId(cursorRegistro.getInt(0));
                registro.setNombre(cursorRegistro.getString(1));
                registro.setGrado(cursorRegistro.getString(2));
                registro.setCurso(cursorRegistro.getString(3));
                listado.add(registro);
            }while (cursorRegistro.moveToNext());
        }
        cursorRegistro.close();

        return listado;
    }

    public Registro verRegistro(int id){
        DbHelper dbHelper=new DbHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        Registro registro=null;
        Cursor cursorRegistro=null;

        cursorRegistro=db.rawQuery("SELECT * FROM "+ TABLE_REGISTROS+" WHERE id_registro= "+ id + " LIMIT 1",null);

        if(cursorRegistro.moveToFirst()){
            registro=new Registro();
            registro.setId(cursorRegistro.getInt(0));
            registro.setNombre(cursorRegistro.getString(1));
            registro.setGrado(cursorRegistro.getString(2));
            registro.setCurso(cursorRegistro.getString(3));
        }
        cursorRegistro.close();

        return registro;
    }


    public boolean editarRegistro(int id,String nombre,String grado, String curso){
        boolean correcto=false;

        DbHelper dbHelper=new DbHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        try {
            db.execSQL(" UPDATE "+TABLE_REGISTROS+" SET nombre='"+nombre+"',grado='"+grado+"',curso='"+curso+"' WHERE id_registro='"+id+"' ");
            correcto=true;
        }catch (Exception ex){
            ex.toString();
            correcto=false;
        }finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarRegistro(int id){
        boolean correcto=false;

        DbHelper dbHelper=new DbHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        try {
            db.execSQL(" DELETE FROM "+TABLE_REGISTROS+" WHERE id_registro='"+id+"'");
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
