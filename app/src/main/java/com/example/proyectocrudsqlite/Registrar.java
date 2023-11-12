package com.example.proyectocrudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registrar extends AppCompatActivity {
    EditText etUserReg, etPassReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        etUserReg = findViewById(R.id.etUserReg);
        etPassReg = findViewById(R.id.etPassReg);
    }

    public void Registrar(View v){
        String user = etUserReg.getText().toString();
        String pass = etPassReg.getText().toString();
        SharedPreferences shared = getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_Edit = shared.edit();
        obj_Edit.putString(user,pass);
        obj_Edit.commit();
//        Intent i = new Intent(this,Ingresar.class);
//        i.putExtra("user", etUserReg.getText().toString());
//        i.putExtra("user", etPassReg.getText().toString());
        Toast.makeText(this, "Se ha registrado exitosamente", Toast.LENGTH_SHORT).show();
        finish();
    }
}