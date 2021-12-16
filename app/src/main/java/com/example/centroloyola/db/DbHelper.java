package com.example.centroloyola.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NOMBRE="centro.db";
    public static final String TABLE_ALUMNOS="t_alumno";
    public static final String TABLE_REGISTROS="t_registro";
    //private static final String TABLE_REGISTRO_ALUMNOS="t_registro_alumnos";

    public DbHelper(Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_REGISTROS + "(" +
                "id_registro INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL,"+
                "grado TEXT NOT NULL," +
                "curso TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ALUMNOS + "(" +
                "id_alumno INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "edad TEXT NOT NULL," +
                "escuela TEXT," +
                "numero_padre TEXT," +
                "numero_madre TEXT," +
                "numero_otro TEXT," +
                "direccion TEXT," +
                "nota TEXT," +
                "id_registro INTEGER NOT NULL," +
                "FOREIGN KEY(id_registro) REFERENCES t_registro(id_registro) ON delete cascade)");

//        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_REGISTRO_ALUMNOS+"(id_registro INTEGER,\n" +
//                "id_alumno INTEGER,\n" +
//                "PRIMARY KEY(id_registro,id_alumno),\n" +
//                "FOREIGN KEY(id_registro) REFERENCES t_registro(id_registro) ON delete cascade,\n" +
//                "FOREIGN KEY(id_alumno) REFERENCES t_alumno(id_alumno) ON delete cascade)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_ALUMNOS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_REGISTROS);
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_REGISTRO_ALUMNOS);
    }



}
