package com.example.proyectocrudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Ingresar extends AppCompatActivity {

    EditText etUser, etPass;

    ProgressBar pb;
    Button btnIngresar;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);
        pb = findViewById(R.id.progressBar);
        btnIngresar = findViewById(R.id.btnIngresar);

        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);
    }

    public void ingresar(View v){
        String nombre = etUser.getText().toString();
        SharedPreferences shared = getSharedPreferences("user",Context.MODE_PRIVATE);
        String apellido = shared.getString(nombre,"");
        if (apellido.length()==0){
            Toast.makeText(this, "No se ha encontrado ningún registro", Toast.LENGTH_SHORT).show();
        }else{
            btnIngresar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pb.setVisibility(View.VISIBLE);
                    Timer timer = new Timer();

                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            counter++;
                            pb.setProgress(counter);

                            if (counter==100){
                                timer.cancel();
                                finish();
                                pb.setVisibility(View.INVISIBLE);

                                Intent entrar = new Intent(Ingresar.this, Menu.class);
                                startActivity(entrar);

                                counter = 0;
                            }
                        }
                    };
                    timer.schedule(timerTask,50,50);
                }
            });
        }
    }
}