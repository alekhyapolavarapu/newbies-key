package co.coderiver.facebooklogin_sample;

/**
 * Created by polavarapu on 10/14/2016.
 */
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import co.coderiver.facebooklogin_sample.viewinterface.ListActivity;

import com.github.channguyen.rsv.*;
import com.github.channguyen.rsv.RangeSliderView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;




public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private RangeSliderView smallSlider;
    private GoogleMap mMap;
    public Geocoder geocoder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        smallSlider = (com.github.channguyen.rsv.RangeSliderView) findViewById(
                R.id.rsv_small);
        smallSlider.setRangeCount(4);
        smallSlider.setInitialIndex(1);
        final RangeSliderView.OnSlideListener listener = new RangeSliderView.OnSlideListener() {
            @Override
            public void onSlide(int index) {
                if(index==0){
                    Intent moveto = new Intent(MapsActivity.this ,
                            middlescreen.class);
                    startActivity(moveto);
                }
                else{
                    Intent directto = new Intent(MapsActivity.this ,
                            ListActivity.class);
                    startActivity(directto);
            }}
        };
        smallSlider.setOnSlideListener(listener);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        geocoder = new Geocoder(this);
        StringBuilder userAddress = new StringBuilder();
        // Add a marker in Sydney and move the camera
        LocationManager userCurrentLocation = (LocationManager) this
                .getSystemService(Context.LOCATION_SERVICE);
        LocationListener userCurrentLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        LatLng userCurrentLocationCorodinates = null;
        double latitute = 0, longitude = 0;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat
                .checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //show message or ask permissions from the user.
            return;
        }
        //Getting the current location of the user.
        userCurrentLocation.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                0, 0, userCurrentLocationListener);
        latitute = userCurrentLocation
                .getLastKnownLocation(LocationManager.GPS_PROVIDER)
                .getLatitude();
        longitude = userCurrentLocation
                .getLastKnownLocation(LocationManager.GPS_PROVIDER)
                .getLongitude();
        userCurrentLocationCorodinates = new LatLng(latitute,longitude);
        //Getting the address of the user based on latitude and longitude.
        try {
            List<Address> addresses = geocoder.getFromLocation(latitute, longitude, 1);
            Address address = addresses.get(0);
            userAddress =  new StringBuilder();
            for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                userAddress.append(address.getAddressLine(i)).append("\t");
            }
            userAddress.append(address.getCountryName()).append("\t");

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        //Setting our image as the marker icon.
        mMap.addMarker(new MarkerOptions().position(userCurrentLocationCorodinates)
                .title("Your current address.").snippet(userAddress.toString())
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.img_icon)));
        LatLng marker11 = new LatLng(39.034616, -94.579401);
        mMap.addMarker(new MarkerOptions().position(marker11).title("Marker").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotpadslogo)));
        LatLng marker12 = new LatLng(39.035836, -94.589853);
        mMap.addMarker(new MarkerOptions().position(marker12).title("Marker").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotpadslogo)));
        LatLng marker13 = new LatLng(39.053070, -94.556909);
        mMap.addMarker(new MarkerOptions().position(marker13).title("Marker").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotpadslogo)));
        LatLng marker14 = new LatLng(39.011152, -94.597686);
        mMap.addMarker(new MarkerOptions().position(marker14).title("Marker").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotpadslogo)));
        LatLng marker15 = new LatLng(39.025001, -94.536399);
        mMap.addMarker(new MarkerOptions().position(marker15).title("Marker").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotpadslogo)));
        LatLng marker16 = new LatLng(39.053638, -94.599884);
        mMap.addMarker(new MarkerOptions().position(marker16).title("Marker").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotpadslogo)));
        LatLng marker17 = new LatLng(39.043967, -94.532736);
        mMap.addMarker(new MarkerOptions().position(marker17).title("Marker").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotpadslogo)));
        LatLng marker18 = new LatLng(39.040175, -94.617952);
        mMap.addMarker(new MarkerOptions().position(marker18).title("Marker").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotpadslogo)));
        LatLng marker19 = new LatLng(39.072976, -94.582303);
        mMap.addMarker(new MarkerOptions().position(marker19).title("Marker").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotpadslogo)));
        LatLng marker20 = new LatLng(38.996544, -94.598609);
        mMap.addMarker(new MarkerOptions().position(marker20).title("Marker").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotpadslogo)));

        //Setting the zoom level of the map.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userCurrentLocationCorodinates, 12));


    }



    public void list_click_button(View view){
        Intent redirect = new Intent(MapsActivity.this, ListActivity.class);
        startActivity(redirect);
    }

}
