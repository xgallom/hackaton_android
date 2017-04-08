package revolware.com.hackaton_android.activities;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import revolware.com.hackaton_android.R;
import revolware.com.hackaton_android.data_access.notification.NotificationListener;
import revolware.com.hackaton_android.data_access.notification.NotificationReceiver;
import revolware.com.hackaton_android.data_access.notification.NotificationScheduler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new NotificationScheduler(new NotificationListener() {
            @Override
            public void onNotificationSchedulerFinished() {
                Intent i = new Intent(MainActivity.this, Search.class);
                startActivity(i);
                finish();
            }
        }).execute(getApplicationContext(), true);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
