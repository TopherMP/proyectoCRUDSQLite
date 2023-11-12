package com.example.proyectocrudsqlite;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Menu extends AppCompatActivity {
    List<list_Elements> elem;
    ArrayList<Pizzas> lista;
    daoPizza dao;
    Pizzas p;
    Adaptador adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        dao = new daoPizza(Menu.this);
        lista = dao.vertodo();
        adapter = new Adaptador(this, lista, dao);
        ListView lView = findViewById(R.id.listView);
        lView.setAdapter(adapter);
        ImageButton insertar = findViewById(R.id.btnInsertar);
        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(Menu.this);
                dialog.setTitle("Nuevo registro");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialogo);
                dialog.show();

                final EditText nombre = dialog.findViewById(R.id.etNombre);
                final EditText ingredientes = dialog.findViewById(R.id.etIngredientes);
                final EditText precio = dialog.findViewById(R.id.etPrecio);

                Button guardar = dialog.findViewById(R.id.btnAdd);
                guardar.setText("Agregar");
                Button cancelar = dialog.findViewById(R.id.btnCancel);

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            p = new Pizzas(
                                    nombre.getText().toString(),
                                    ingredientes.getText().toString(),
                                    precio.getText().toString()
                            );
                            dao.insertarPizza(p);
                            lista = dao.vertodo();
                            adapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }catch(Exception e){
                            Toast.makeText(Menu.this, "ERROR", Toast.LENGTH_SHORT).show();
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
    }

    public void Back(View v){
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }
}