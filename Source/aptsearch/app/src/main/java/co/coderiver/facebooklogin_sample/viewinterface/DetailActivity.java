package co.coderiver.facebooklogin_sample.viewinterface;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.channguyen.rsv.RangeSliderView;

import co.coderiver.facebooklogin_sample.MapsActivity;
import co.coderiver.facebooklogin_sample.R;
import co.coderiver.facebooklogin_sample.chatactivity;

public class DetailActivity extends AppCompatActivity {
    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_QUOTE = "EXTRA_QUOTE";
    private static final String EXTRA_ATTR = "EXTRA_ATTR";

    private RangeSliderView smallSlider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        smallSlider = (com.github.channguyen.rsv.RangeSliderView) findViewById(
                R.id.rsv_small);
        smallSlider.setRangeCount(4);
        smallSlider.setInitialIndex(3);
        Bundle extras = getIntent().getBundleExtra(BUNDLE_EXTRAS);

        ((TextView)findViewById(R.id.lbl_quote_text)).setText(extras.getString(EXTRA_QUOTE));
        ((TextView)findViewById(R.id.lbl_quote_attribution)).setText(extras.getString(EXTRA_ATTR));
        ImageButton phnBtn = (ImageButton) findViewById(R.id.phnBtn);
        ImageButton mapBtn = (ImageButton) findViewById(R.id.mapBtn);
        Button chatBtn = (Button) findViewById(R.id.chatBtn);

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent redirect = new Intent(DetailActivity.this, MapsActivity.class);
                startActivity(redirect);
                finish();
            }
        });


        phnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:8167458953"));

                if (ActivityCompat.checkSelfPermission(DetailActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);
            }
        });
    }
    public void chatonclick(View view){
        Intent redirect = new Intent(DetailActivity.this, chatactivity.class);
        startActivity(redirect);
    }
}