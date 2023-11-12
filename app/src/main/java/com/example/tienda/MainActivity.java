package com.example.tienda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Iniciar(View view){
        Intent inicio = new Intent(this, IniciarSesion.class);
        startActivity(inicio);
    }


    public void Registrar(View view){
        Intent registrar = new Intent(this, Registrarse.class);
        startActivity(registrar);
    }
}