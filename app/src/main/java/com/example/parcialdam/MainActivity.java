package com.example.parcialdam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button botonDatos,botonMapa;
    EditText ingresoTexto,ingresoTexto2;
    List<String> lista  = new ArrayList<>();
    Switch switchMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonDatos = findViewById(R.id.buttonDatos);
        botonMapa = findViewById(R.id.buttonMapa);
        ingresoTexto = findViewById(R.id.ingresarMarcador1);
        ingresoTexto2 = findViewById(R.id.ingresarMarcador2);
        switchMapa = findViewById(R.id.switchMapa);
        botonMapa.setEnabled(false);

        switchMapa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    botonMapa.setEnabled(true);
                }
                else{
                    botonMapa.setEnabled(false);
                }
            }
        });

        botonDatos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                lista.add(ingresoTexto.getText().toString());
                lista.add(ingresoTexto2.getText().toString());
                Toast.makeText(getApplicationContext(), "Datos guardados!", Toast.LENGTH_LONG).show();
            }
        });

        botonMapa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MapActivity.class);
                if(!lista.isEmpty()){
                    i.putStringArrayListExtra("posicion", (ArrayList<String>) lista);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "Datos enviados al mapa, abriendo...", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Debe guardar los datos", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}