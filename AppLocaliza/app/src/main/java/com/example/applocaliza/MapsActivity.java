package com.example.applocaliza;

import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, buscarEndereco {

    private TextView txtLatitude;
    private TextView txtLongitude;
    private TextView txtCidade;
    private TextView txtEstado;
    private TextView txtPais;

    private Location location;
    private LocationManager localtionManager;

    private Address endereco;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        txtLatitude = (TextView) findViewById(R.id.txtLatitude);
        txtLongitude = (TextView) findViewById(R.id.txtLongitude);
        txtCidade = (TextView) findViewById(R.id.txtCidade);
        txtEstado = (TextView) findViewById(R.id.txtEstado);
        txtPais = (TextView) findViewById(R.id.txtPais);

        double latitude = 0.0;
        double longitude = 0.0;


        if (MapsActivity.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
        } else {
            localtionManager = (LocationManager)
                    getSystemService(Context.LOCATION_SERVICE);
            location = localtionManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        }

        if (location != null) {

            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }

        txtLongitude.setText("Longitude: " + longitude);
        txtLatitude.setText("Latitude: " + latitude);

        try {
            endereco = buscarEndereco(latitude, longitude);

            txtCidade.setText("Cidade: " + endereco.getLocality());
            txtEstado.setText("Estado: " + endereco.getAdminArea());
            txtPais.setText("Pais: " + endereco.getCountryName());
        } catch (IOException e) {
            Log.i("GPS", e.getMessage());
        }

    }

    private static int checkSelfPermission(MapsActivity mapsActivity, String accessFineLocation) {


    }

    @Override
    public Address buscarEndereco(double latitude, double longitude)
            throws IOException {

        Geocoder geocoder;
        List<Address> address = null;
        List <Address> addresses;

        geocoder = new Geocoder(getApplicationContext());

        address = geocoder.getFromLocation(latitude,longitude,1);

        if (address.size() > 0) {
            address = (List<Address>) address.get(0);
        }
            return (Address) address;
        // Add a marker in Sydney and move the camera
        LatLng localiza????o = new LatLng(double latitude, double longitude);
        mMap.addMarker(new MarkerOptions().position(localiza????o).title("Localiza????o"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(localiza????o));
    }

    @Override
    public  void  onMapReady ( GoogleMap  googleMap ) {
        mMap = googleMap;

        LatLng localiza????o = new LatLng( double latitude, double longitude; );
        mMap.addMarker(new MarkerOptions().position(localiza????o).title(" Localiza????o"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localiza????o, 15));

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        LatLng localliza????o = new LatLng(double latitude , double longitude);
        googleMap.addMarker(new MarkerOptions()
                .position(localliza????o)
                .title("Sua Localiza????o"));
        LatLng localiza????o;
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(localiza????o));
    }
}