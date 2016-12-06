package co.coderiver.facebooklogin_sample;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import co.coderiver.facebooklogin_sample.viewinterface.ListActivity;

public class Mapsaroundlocation extends FragmentActivity implements OnMapReadyCallback {
    AutoCompleteTextView Actv;
    String[] language={"Houston","Hornsby","Hoxton park","House of crabs","houison street","Worlds of fun,KCMO,MO","woodland Hills, LosAngeles,CA","Woodyard Bar-B-Que Merriam Lane,KS","Woodland Heights,Houston,TX","Houston Road","Hurstville","Kensington","Katoomba","Kellyville","Kansas city shuffle","Kansai Japanese restaurant","Kansas","California","Kansas city","Dallas","hyderabad"};
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapsaroundlocation);
        Actv=(AutoCompleteTextView)findViewById(R.id.TFaddress);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,language);
        Actv.setThreshold(1);
        Actv.setAdapter(adapter);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        //LatLng sydney = new LatLng(-34, 151);

        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney));
    }
    public void onSearch(View view){
        //EditText location_tf = (EditText)findViewById(R.id.TFaddress);
        String location = Actv.getText().toString();
        List<Address> addresslist = null;
        if(location!=null || location.equals("")){
            Geocoder geocoder = new Geocoder(this);
            try {
                addresslist = geocoder.getFromLocationName(location,1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addresslist.get(0);
            LatLng latlng = new LatLng(address.getLatitude() , address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latlng).title("Address").snippet("837 West 20th Street Houston, TX 77008").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotpadslogo)));
            LatLng marker1 = new LatLng(29.803775, -95.413699);
            mMap.addMarker(new MarkerOptions().position(marker1).title("Address").snippet("8301 Nyssa Street Houston, TX 77078").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotpadslogo)));
            LatLng marker2 = new LatLng(29.859767, -95.273623);
            mMap.addMarker(new MarkerOptions().position(marker2).title("Address").snippet("kckk").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotpadslogo)));
            LatLng marker3 = new LatLng(29.920489, -95.465884);
            mMap.addMarker(new MarkerOptions().position(marker3).title("Address").snippet("8332 strret Halls Bayou").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotpadslogo)));
            LatLng marker4 = new LatLng(29.920489, -95.465884);
            mMap.addMarker(new MarkerOptions().position(marker4).title("address").snippet("kckk").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotpadslogo)));
            LatLng marker5 = new LatLng(29.915727, -95.589480);
            mMap.addMarker(new MarkerOptions().position(marker5).title("Address").snippet("11302 Yorkshire Oaks Drive Houston, TX 77065").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotpadslogo)));
            LatLng marker6 = new LatLng(29.902633, -95.250277);
            mMap.addMarker(new MarkerOptions().position(marker6).title("Address").snippet("13631 Nightingale Drive Houston, TX 77050").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotpadslogo)));
            LatLng marker7 = new LatLng(29.919298, -95.457644);
            mMap.addMarker(new MarkerOptions().position(marker7).title("Address").snippet("13363 street Harris County").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotpadslogo)));
            LatLng marker8 = new LatLng(29.935961, -95.715823);
            mMap.addMarker(new MarkerOptions().position(marker8).title("Address").snippet("10700 Fry Road Cypress, TX 77433").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotpadslogo)));
            LatLng marker9 = new LatLng(29.575911, -95.343661);
            mMap.addMarker(new MarkerOptions().position(marker9).title("Address").snippet("1841 Adamo Lane Pearland, TX 77581").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotpadslogo)));
            LatLng marker10 = new LatLng(29.741790, -95.541415);
            mMap.addMarker(new MarkerOptions().position(marker10).title("Address").snippet("100-196 East Rivercrest Drive Houston, TX 77063").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotpadslogo)));


            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,8));

        }
    }
    public void blistviewclick(View view){
        Intent redirect = new Intent(Mapsaroundlocation.this, ListActivity.class);
        startActivity(redirect);
    }
}