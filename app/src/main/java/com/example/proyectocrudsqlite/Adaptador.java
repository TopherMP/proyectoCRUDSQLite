package com.example.proyectocrudsqlite;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    ArrayList<Pizzas> listaPizza;
    daoPizza dao;
    Pizzas p;
    Activity a;
    int id = 0;

    public Adaptador(Activity a, ArrayList<Pizzas> listaPizza , daoPizza dao){
        this.a = a;
        this.listaPizza = listaPizza;
        this.dao = dao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public int getCount(){
        return listaPizza.size();
    }
    @Override
    public Object getItem(int i){
        p = listaPizza.get(i);
        return null;
    }
    @Override
    public long getItemId(int i){
        p=listaPizza.get(i);
        return p.getId();
    }
    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup){
        View v = view;
        if (v == null){
            LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.list_elements, null);
        }

        p = listaPizza.get(posicion);

        TextView nombre = v.findViewById(R.id.nameTxtView);
        TextView ingredientes = v.findViewById(R.id.ingreTxtView);
        TextView precio = v.findViewById(R.id.precioTxtView);
        Button edit = v.findViewById(R.id.btnEditar);
        Button eliminar = v.findViewById(R.id.btnEliminar);

        nombre.setText(p.getNombre());
        ingredientes.setText(p.getIngredientes());
        precio.setText(p.getPrecio());

        edit.setTag(posicion);
        eliminar.setTag(posicion);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(v.getTag().toString());

                final Dialog dialog = new Dialog(a);
                dialog.setTitle("Editar registro");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialogo);
                dialog.show();

                final EditText nombre = dialog.findViewById(R.id.etNombre);
                final EditText ingredientes = dialog.findViewById(R.id.etIngredientes);
                final EditText precio = dialog.findViewById(R.id.etPrecio);
                Button guardar = dialog.findViewById(R.id.btnAdd);
                Button cancelar = dialog.findViewById(R.id.btnCancel);

                p = listaPizza.get(pos);
                setId(p.getId());

                nombre.setText(p.getNombre());
                ingredientes.setText(p.getIngredientes());
                precio.setText(p.getPrecio());

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            p = new Pizzas(getId(),
                                    nombre.getText().toString(),
                                    ingredientes.getText().toString(),
                                    precio.getText().toString());
                            dao.editarPizza(p);
                            listaPizza = dao.vertodo();
                            notifyDataSetChanged();
                            dialog.dismiss();
                        } catch (Exception e) {
                            Toast.makeText(a, "ERROR", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(v.getTag().toString());
                p = listaPizza.get(pos);
                setId(p.getId());
                AlertDialog.Builder del = new AlertDialog.Builder(a);
                del.setMessage("Seguro que deseas eliminar?");
                del.setCancelable(false);
                del.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dao.eliminarPizza(getId());
                        listaPizza = dao.vertodo();
                        notifyDataSetChanged();
                    }
                });
                del.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                });
                del.show();
            }
        });
        return v;
    }
}
