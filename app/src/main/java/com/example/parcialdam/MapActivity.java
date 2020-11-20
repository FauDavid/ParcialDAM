package com.example.parcialdam;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap myMap;
    List<String> posiciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        myMap=googleMap;
        posiciones = new ArrayList<>();
        posiciones = getIntent().getStringArrayListExtra("posicion");
        Toast.makeText(this, posiciones.toString(),Toast.LENGTH_SHORT).show();

        double u = new Random().nextDouble();
        double v = new Random().nextDouble();

        double x = new Random().nextDouble();
        double y = new Random().nextDouble();

        Random r = new Random();

        int direccionRandomEnGrados = r.nextInt(360);

        int distanciaMinima = 100;
        int distanciaMaxima = 1000;
        int distanciaRandomEnMetros = r.nextInt(distanciaMaxima - distanciaMinima) + distanciaMinima;

        LatLng posicionOriginal1 = new LatLng(u,v);
        LatLng posicionOriginal2 = new LatLng(x,y);

        LatLng posicion1 = SphericalUtil.computeOffset(
                posicionOriginal1,
                distanciaRandomEnMetros,
                direccionRandomEnGrados
        );

        LatLng posicion2 = SphericalUtil.computeOffset(
                posicionOriginal2,
                distanciaRandomEnMetros,
                direccionRandomEnGrados
        );

        myMap.addMarker(new MarkerOptions().position(posicion1).title(posiciones.get(0)));
        myMap.addMarker(new MarkerOptions().position(posicion2).title(posiciones.get(1)));
        PolylineOptions rectOptions = new
                PolylineOptions().add(posicion1).color(Color.BLUE)
                .add(posicion2).color(Color.RED);
        Polyline polyline = myMap.addPolyline(rectOptions);
        myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posicion1, 9));

    }


}