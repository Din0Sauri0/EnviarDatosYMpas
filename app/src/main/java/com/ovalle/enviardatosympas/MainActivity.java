package com.ovalle.enviardatosympas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {
    EditText txtLatitud, txtLongitud, txtNombre;
    Button btnMaps, btnLimpiar;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtLatitud = findViewById(R.id.txtLatitud);
        txtLongitud = findViewById(R.id.txtLongitud);
        txtNombre = findViewById(R.id.txtNombre);

        //inicializando el opbjeto fusedlocation.
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);

        txtLongitud.setEnabled(false);
        txtLatitud.setEnabled(false);

        obtenerCoordenadas();

        btnMaps = findViewById(R.id.btnMaps);
        btnLimpiar = findViewById(R.id.btnLimpiar);

        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float Lat, Long;
                String Nombre;

                Lat = Float.parseFloat(txtLatitud.getText().toString());
                Long = Float.parseFloat(txtLongitud.getText().toString());
                Nombre = txtNombre.getText().toString();
                //enviar datos atraves de los Intent.
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("Latitud", Lat);
                intent.putExtra("Longitud", Long);
                intent.putExtra("Nombre", Nombre);
                startActivity(intent);


            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    //Metodo que obtendra las cordenadas.
    public void obtenerCoordenadas() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){
                    //Programar acciones;
                    txtLatitud.setText(String.valueOf(location.getLatitude()));
                    txtLongitud.setText(String.valueOf(location.getLongitude()));
                }

            }
        });
    }
}