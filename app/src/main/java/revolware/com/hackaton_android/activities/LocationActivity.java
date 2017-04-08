package revolware.com.hackaton_android.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import revolware.com.hackaton_android.R;
import revolware.com.hackaton_android.data_access.RequestListener;
import revolware.com.hackaton_android.data_access.exception.ServerException;
import revolware.com.hackaton_android.data_access.fonts.AssetedTypeface;
import revolware.com.hackaton_android.data_access.model.RssFeed;
import revolware.com.hackaton_android.data_access.rssfeed.RssFeedProvider;
import revolware.com.hackaton_android.views.RssFeedView;

public class LocationActivity extends AppCompatActivity {
    private String country;
    private List<RssFeed> rssData;

    private Typeface
            robotolight,
            robotothin,
            robotomedium;

    private LayoutInflater inflater;
    private LinearLayout feedViewContainer;

    public LayoutInflater getInflater() {
        return inflater;
    }
    public LinearLayout getFeedViewContainer() {
        return feedViewContainer;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        inflater = getLayoutInflater();

        robotolight     = AssetedTypeface.getRobotolight();
        robotothin      = AssetedTypeface.getRobotothin();
        robotomedium    = AssetedTypeface.getRobotoMedium();
        Typeface robotobold
                        = AssetedTypeface.getRobotobold();

        TextView countryTextView = (TextView) findViewById(R.id.countryTextView);
        Button finishButton = (Button) findViewById(R.id.finishButton);

        finishButton.setTypeface(robotobold);

        Intent i = getIntent();
        country = i.getStringExtra("location");
        countryTextView.setText(country);

        RssFeed feed = new RssFeed();
        feed.setDate("2017-04-06");

        feedViewContainer = (LinearLayout) findViewById(R.id.feedViewContainer);
        new RssFeedProvider(getApplicationContext()).getAll(feed, new RequestListener() {
            @Override
            public void onError(ServerException e) {
                Log.e("DEBUG_GALLO", e.toString());
            }

            @Override
            public void onSuccess(Object obj) {
                rssData = (List<RssFeed>) obj;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        RssFeedView view = new RssFeedView(getInflater());

                        for(int n = 0; n < rssData.size(); n++)
                            getFeedViewContainer().addView(view.createView(rssData.get(n)));
                    }
                });
            }
        });
    }

}
