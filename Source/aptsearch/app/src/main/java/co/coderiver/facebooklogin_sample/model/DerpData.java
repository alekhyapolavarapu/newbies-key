package co.coderiver.facebooklogin_sample.model;

/**
 * Created by polavarapu on 10/21/2016.
 */

//DerpData.java
import java.util.ArrayList;
        import java.util.List;

import co.coderiver.facebooklogin_sample.R;

import static co.coderiver.facebooklogin_sample.R.drawable.color_icons_blue_home;


public class DerpData {
    private static final String[] titles = {"2BHK flat at 1200$ with all amenities",
            "Flat with all facilities " +
                    "situated at center of town and pets are allowed" +
                    " just for at 2400$ 4BHK",
            "The flat provides full fledged facilities with extraordinary surroundings",
            "3bhk flat at kansas city in missouri near to umkc" +
                    " leased by umkc holmes",
            "excellent amenities at less cost" +
                    " with 4 bedrooms and 2 bath" +
                    " at just $2500" +
                    " with pets allowed ",
            "Provides you a good environment near the campus" +
                    " most suitable for students" +
                    " with less penny"
    };
    private static final String[] subTitles = {"lapez Barron",
            "John ",
            "Zim warner",
            "Kenneth J",
            "Adam smith",
            "bary moore",
            "Jim Rohn"

    };
    private static final int icon = color_icons_blue_home;

    public static List<ListItem> getListData() {
        List<ListItem> data = new ArrayList<>();

        //Repeat process 4 times, so that we have enough data to demonstrate a scrollable
        //RecyclerView
        for (int x = 0; x < 4; x++) {
            //create ListItem with dummy data, then add them to our List
            for (int i = 0; i < titles.length; i++) {
                ListItem item = new ListItem();
                item.setTitle(titles[i]);
                item.setSubTitle(subTitles[i]);
                data.add(item);
            }
        }
        return data;
    }
}