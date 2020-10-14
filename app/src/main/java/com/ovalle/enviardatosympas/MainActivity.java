package com.ovalle.enviardatosympas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText txtLatitud, txtLongitud, txtNombre;
    Button btnMaps, btnLimpiar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtLatitud = findViewById(R.id.txtLatitud);
        txtLongitud = findViewById(R.id.txtLongitud);
        txtNombre = findViewById(R.id.txtNombre);

        btnMaps = findViewById(R.id.btnMaps);
        btnLimpiar = findViewById(R.id.btnLimpiar);

        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float Lat,Long;
                String Nombre;

                Lat = Float.parseFloat(txtLatitud.getText().toString());
                Long = Float.parseFloat(txtLongitud.getText().toString());
                Nombre = txtNombre.getText().toString();

                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("Latitud", Lat);
                intent.putExtra("Longitud", Long);
                intent.putExtra("Nombre", Nombre);
                startActivity(intent);
            }
        });
    }
}