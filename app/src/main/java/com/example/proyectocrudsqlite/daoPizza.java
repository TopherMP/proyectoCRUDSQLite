package com.example.proyectocrudsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoPizza {
    SQLiteDatabase db;
    ArrayList<Pizzas> listaPizzas = new ArrayList<Pizzas>();
    Context ct;
    Pizzas p;
    String nombreDB = "DBPizza";
    String tabla = "create table if not exists pizza(id INTEGER PRIMARY KEY autoincrement, nombre TEXT, ingredientes TEXT, precio INTEGER)";

    public daoPizza(Context ct){
        this.ct = ct;
        db = ct.openOrCreateDatabase(nombreDB,Context.MODE_PRIVATE,null);
        db.execSQL(tabla);
    }
    public boolean insertarPizza(Pizzas p){
        ContentValues content = new ContentValues();
        content.put("nombre",p.getNombre());
        content.put("ingredientes",p.getIngredientes());
        content.put("precio",p.getPrecio());
        return db.insert("pizza",null,content)>0;
    }
    public boolean eliminarPizza(int id){
        return db.delete("pizza","id="+id,null)>0;
    }
    public boolean editarPizza(Pizzas p){
        ContentValues content = new ContentValues();
        content.put("nombre",p.getNombre());
        content.put("ingredientes",p.getIngredientes());
        content.put("precio",p.getPrecio());
        return db.update("pizza",content,"id="+p.getId(),null)>0;
    }
    public ArrayList<Pizzas> vertodo(){
        listaPizzas.clear();
        Cursor cursor = db.rawQuery("select * from pizza",null);
        if (cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                listaPizzas.add(new Pizzas(
                                cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3)
                        )
                );
            }while(cursor.moveToNext());
        }
        return listaPizzas;
    }
}
