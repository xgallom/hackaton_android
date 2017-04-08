package revolware.com.hackaton_android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import revolware.com.hackaton_android.R;
import revolware.com.hackaton_android.data_access.fonts.AssetedTypeface;
import revolware.com.hackaton_android.data_access.notification.NotificationListener;
import revolware.com.hackaton_android.data_access.notification.NotificationScheduler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AssetedTypeface.setMgr(getAssets());

        new NotificationScheduler(new NotificationListener() {
            @Override
            public void onNotificationSchedulerFinished() {
            }
        }).execute(getApplicationContext(), true);

        Intent i = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
