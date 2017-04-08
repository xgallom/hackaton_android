package revolware.com.hackaton_android.activities;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import revolware.com.hackaton_android.R;
import revolware.com.hackaton_android.data_access.notification.NotificationReceiver;
import revolware.com.hackaton_android.data_access.notification.NotificationScheduler;

public class MainActivity extends AppCompatActivity {
    NotificationReceiver receiver;
    IntentFilter filterLock, filterNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationScheduler scheduler = new NotificationScheduler();
        scheduler.execute(getApplicationContext(), true);

        Intent i = new Intent(MainActivity.this, Search.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
