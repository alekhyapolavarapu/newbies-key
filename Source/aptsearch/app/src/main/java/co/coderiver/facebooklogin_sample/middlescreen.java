package co.coderiver.facebooklogin_sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.channguyen.rsv.RangeSliderView;

public class middlescreen extends AppCompatActivity {
    private RangeSliderView smallSlider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middlescreen);

        smallSlider = (RangeSliderView) findViewById(
                R.id.rsv_small);
        smallSlider.setRangeCount(4);
        smallSlider.setInitialIndex(0);
        final RangeSliderView.OnSlideListener listener = new RangeSliderView.OnSlideListener() {
            @Override
            public void onSlide(int index) {
                Intent moveto = new Intent(middlescreen.this ,
                        MapsActivity.class);
                startActivity(moveto);
            }
        };
        smallSlider.setOnSlideListener(listener);
    }
    public void crnt_location_click(View view){
        Intent intent = new Intent(middlescreen.this ,
                MapsActivity.class);
        startActivity(intent);
    }
    public void particular_location_click(View view){
        Intent intent = new Intent(middlescreen.this ,
                Mapsaroundlocation.class);
        startActivity(intent);
    }
}
