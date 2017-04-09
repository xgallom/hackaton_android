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

import java.util.ArrayList;
import java.util.List;

import revolware.com.hackaton_android.R;
import revolware.com.hackaton_android.data_access.RequestListener;
import revolware.com.hackaton_android.data_access.exception.ServerException;
import revolware.com.hackaton_android.data_access.fonts.AssetedTypeface;
import revolware.com.hackaton_android.data_access.model.Risk;
import revolware.com.hackaton_android.data_access.model.RiskChance;
import revolware.com.hackaton_android.data_access.model.RssFeed;
import revolware.com.hackaton_android.data_access.risk.RiskProvider;
import revolware.com.hackaton_android.data_access.rssfeed.RssFeedProvider;
import revolware.com.hackaton_android.views.RiskChanceView;
import revolware.com.hackaton_android.views.RssFeedView;

public class LocationActivity extends AppCompatActivity {
    private String country;
    private List<RssFeed> rssData;
    private List<RiskChance> riskChanceData;


    private LayoutInflater inflater;
    private LinearLayout feedViewContainer;
    private LinearLayout riskChanceViewContainer;

    public LayoutInflater getInflater() {
        return inflater;
    }
    public LinearLayout getFeedViewContainer() {
        return feedViewContainer;
    }
    public LinearLayout getRiskChanceViewContainer() { return riskChanceViewContainer; }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        inflater = getLayoutInflater();

        Typeface robotolight     = AssetedTypeface.getRobotolight();
        Typeface robotothin      = AssetedTypeface.getRobotothin();
        Typeface robotobold      = AssetedTypeface.getRobotobold();



        TextView countryTextView = (TextView) findViewById(R.id.countryTextView);
        TextView newsTextView = (TextView) findViewById(R.id.newsText);
        Button finishButton = (Button) findViewById(R.id.finishButton);

        finishButton.setTypeface(robotobold);
        countryTextView.setTypeface(robotothin);
        newsTextView.setTypeface(robotolight);


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

        riskChanceViewContainer = (LinearLayout) findViewById(R.id.riskChanceViewContainer);
        new RiskProvider(getApplicationContext()).getAll(null, new RequestListener() {
            @Override
            public void onError(ServerException e) {
                Log.e("DEBUG_GALLO", e.toString());
            }

            @Override
            public void onSuccess(Object obj) {
                riskChanceData = new ArrayList<RiskChance>();
                List<Risk> risks = (List<Risk>) obj;

                int x = 0;
                for(int n = 0; n < risks.size(); n++) {
                    RiskChance rc = new RiskChance();
                    rc.setRisk(risks.get(n));
                    rc.setChance(x);
                    x += 71;
                    if(x > 100)
                        x -= 100;

                    riskChanceData.add(rc);
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        RiskChanceView view = new RiskChanceView(getInflater());

                        for(int n = 0; n < riskChanceData.size(); n++)
                            getRiskChanceViewContainer().addView(view.createView(riskChanceData.get(n)));

                        getRiskChanceViewContainer().addView(view.createView(null));
                    }
                });
            }
        });
    }

}
