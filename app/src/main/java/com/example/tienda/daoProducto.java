package com.example.tienda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoProducto {
    SQLiteDatabase bd;
    ArrayList<Producto> Lista = new ArrayList<Producto>();

    Producto c;
    Context ct;
    String nombreBD;
    String tabla = "create table if not exists producto(id integer primary key autoincrement, producto text, descripcion text, precio text)";

    public daoProducto(Context c){
        this.ct = c;
        bd = c.openOrCreateDatabase(nombreBD,Context.MODE_PRIVATE,null);
        bd.execSQL(tabla);
    }

    public boolean insertar(Producto c){
        ContentValues contenedor = new ContentValues();
        contenedor.put("producto",c.getProducto());
        contenedor.put("descripcion",c.getDescripcion());
        contenedor.put("precio",c.getPrecio());
        return(bd.insert("producto",null,contenedor))>0;
    }
    public boolean eliminar(int id){
        return (bd.delete("producto","id="+id,null))>0;
    }
    public boolean editar(Producto c){
        ContentValues contenedor = new ContentValues();
        contenedor.put("producto",c.getProducto());
        contenedor.put("descripcion",c.getDescripcion());
        contenedor.put("precio",c.getPrecio());
        return (bd.update("producto",contenedor,"id="+c.getId(),null))>0;
    }
    public ArrayList<Producto>verTodo(){
        Lista.clear();
        Cursor cursor = bd.rawQuery("select * from producto",null);
        if (cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                Lista.add(new Producto(cursor.getInt(0),
                        cursor.getString(1),cursor.getString(2),
                        cursor.getString(3), cursor.getString(4),
                        cursor.getString(5)));
            } while (cursor.moveToNext());
        }
        return Lista;
    }

    public Producto verUno(int posicion){
        Cursor cursor = bd.rawQuery("select * from producto",null);
        cursor.moveToPosition(posicion);
        c = new Producto(cursor.getInt(0),
                cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4),
                cursor.getString(5));
        return c;
    }
}
