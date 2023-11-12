package com.example.proyectocrudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Button entrar, reg, map;

    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reg = findViewById(R.id.btnReg);
        map = findViewById(R.id.btnMap);
        entrar = findViewById(R.id.btnEntrar);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ingresar = new Intent(MainActivity.this, Ingresar.class);
                startActivity(ingresar);
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gMap = new Intent(MainActivity.this, Map.class);
                startActivity(gMap);
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(MainActivity.this, Registrar.class);
                startActivity(registrar);
            }
        });
    }
}