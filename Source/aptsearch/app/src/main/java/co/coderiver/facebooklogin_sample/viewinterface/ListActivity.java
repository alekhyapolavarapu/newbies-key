package co.coderiver.facebooklogin_sample.viewinterface;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.channguyen.rsv.RangeSliderView;

import java.util.ArrayList;

import co.coderiver.facebooklogin_sample.R;
import co.coderiver.facebooklogin_sample.adapter.DerpAdapter;
import co.coderiver.facebooklogin_sample.model.DerpData;
import co.coderiver.facebooklogin_sample.model.ListItem;

/**
 * Created by polavarapu on 10/21/2016.
 */
public class ListActivity extends AppCompatActivity implements DerpAdapter.ItemClickCallback {
    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_QUOTE = "EXTRA_QUOTE";
    private static final String EXTRA_ATTR = "EXTRA_ATTR";
    private RangeSliderView smallSlider;
    private RecyclerView recyclerView;
    private DerpAdapter adapter;
    private ArrayList listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        smallSlider = (com.github.channguyen.rsv.RangeSliderView) findViewById(
                R.id.rsv_small);
        smallSlider.setRangeCount(4);
        smallSlider.setInitialIndex(2);
        listData = (ArrayList) DerpData.getListData();

        recyclerView = (RecyclerView) findViewById(R.id.rec_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new DerpAdapter(DerpData.getListData(), this);
        recyclerView.setAdapter(adapter);
        adapter.setItemClickCallback(this);
    }

    @Override
    public void onItemClick(int p) {
        ListItem item = (ListItem) listData.get(p);

        Intent i = new Intent(this, DetailActivity.class);

        Bundle extras = new Bundle();
        extras.putString(EXTRA_QUOTE, item.getTitle());
        extras.putString(EXTRA_ATTR, item.getSubTitle());
        i.putExtra(BUNDLE_EXTRAS, extras);

        startActivity(i);
    }

    @Override
    public void onSecondaryIconClick(int p) {
        ListItem item = (ListItem) listData.get(p);
        //update our data
        if (item.isFavourite()){
            item.setFavourite(false);
        } else {
            item.setFavourite(true);
        }
        //pass new data to adapter and update
        adapter.setListData(listData);
        adapter.notifyDataSetChanged();

    }
}
