package com.example.centroloyola.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.centroloyola.entidades.Alumno;
import com.example.centroloyola.entidades.Registro;

import java.util.ArrayList;

public class DbAlumno extends DbHelper{

    Context context;

    public DbAlumno(Context context) {
        super(context);
        this.context=context;
    }

    public long insertarAlumno(String nombre,int edad, String escuela, String numero_padre, String numero_madre, String numero_otro, String direccion, String nota, int id_registro){
        long id=0;
        try {
            DbHelper dbHelper=new DbHelper(context);
            SQLiteDatabase db=dbHelper.getWritableDatabase();

            ContentValues values= new ContentValues();
            values.put("nombre",nombre);
            values.put("edad",edad);
            values.put("escuela",escuela);
            values.put("numero_padre",numero_padre);
            values.put("numero_madre",numero_madre);
            values.put("numero_otro",numero_otro);
            values.put("direccion",direccion);
            values.put("nota",nota);
            values.put("id_registro",id_registro);
            id=db.insert(TABLE_ALUMNOS,null,values);
        }catch (Exception ex){
            ex.toString();
        }

        return id;
    }

    public ArrayList<Alumno> mostrarAlumnos(int id_registro){
        DbHelper dbHelper=new DbHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        ArrayList<Alumno> listado=new ArrayList<>();
        Alumno alumno=null;
        Cursor cursorAlumno=null;

        cursorAlumno=db.rawQuery("SELECT * FROM "+ TABLE_ALUMNOS +" WHERE id_registro = ?",new String[]{String.valueOf(id_registro)});

        if(cursorAlumno.moveToFirst()){
            do{
                alumno=new Alumno();
                alumno.setId(cursorAlumno.getInt(0));
                alumno.setNombre(cursorAlumno.getString(1));
                alumno.setEdad(cursorAlumno.getInt(2));
                listado.add(alumno);
            }while (cursorAlumno.moveToNext());
        }
        cursorAlumno.close();

        return listado;
    }

    public Alumno verAlumno(int id){
        DbHelper dbHelper=new DbHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        Alumno alumno=null;
        Cursor cursorAlumno=null;

        cursorAlumno=db.rawQuery("SELECT * FROM "+ TABLE_ALUMNOS+" WHERE id_alumno= "+ id + " LIMIT 1",null);

        if(cursorAlumno.moveToFirst()){
            alumno=new Alumno();
            alumno.setId(cursorAlumno.getInt(0));
            alumno.setNombre(cursorAlumno.getString(1));
            alumno.setEdad(cursorAlumno.getInt(2));
            alumno.setEscuela(cursorAlumno.getString(3));
            alumno.setNumero_padre(cursorAlumno.getString(4));
            alumno.setNumero_madre(cursorAlumno.getString(5));
            alumno.setNumero_otro(cursorAlumno.getString(6));
            alumno.setDireccion(cursorAlumno.getString(7));
            alumno.setNota(cursorAlumno.getString(8));
            alumno.setId_registro(cursorAlumno.getInt(9));
        }
        cursorAlumno.close();

        return alumno;
    }


    public boolean editarAlumno(int id,String nombre,int edad, String escuela, String numero_padre, String numero_madre, String numero_otro, String direccion, String nota){
        boolean correcto=false;

        DbHelper dbHelper=new DbHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        try {
            db.execSQL(" UPDATE "+TABLE_ALUMNOS+" SET nombre='"+nombre+"'," +
                    "edad='"+edad+"'," +
                    "escuela='"+escuela+"'," +
                    "numero_padre='"+numero_padre+"'," +
                    "numero_madre='"+numero_madre+"'," +
                    "numero_otro='"+numero_otro+"'," +
                    "direccion='"+direccion+"'," +
                    "nota='"+nota+"' WHERE id_alumno='"+id+"' ");
            correcto=true;
        }catch (Exception ex){
            ex.toString();
            correcto=false;
        }finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarAlumno(int id){
        boolean correcto=false;

        DbHelper dbHelper=new DbHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        try {
            db.execSQL(" DELETE FROM "+TABLE_ALUMNOS+" WHERE id_alumno='"+id+"'");
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
