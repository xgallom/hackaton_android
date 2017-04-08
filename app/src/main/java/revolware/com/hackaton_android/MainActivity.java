package revolware.com.hackaton_android;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import revolware.com.hackaton_android.data_access.notification.NotificationReceiver;
import revolware.com.hackaton_android.data_access.notification.NotificationScheduler;

public class MainActivity extends AppCompatActivity {
    NotificationReceiver receiver;
    IntentFilter filterLock, filterNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("DEBUG_GALLO", "onCreate");

        receiver = new NotificationReceiver();
        filterLock  = new IntentFilter("android.intent.action.SCREEN_ON");
//        filterNet   = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        getApplicationContext().registerReceiver(receiver, filterLock);
//        getApplicationContext().registerReceiver(receiver, filterNet);
        NotificationScheduler.startScheduling(getApplicationContext());
    }

    @Override
    protected void onDestroy()
    {
        Log.e("DEBUG_GALLO", "onDestroy");
//        getApplicationContext().unregisterReceiver(receiver);

        super.onDestroy();
    }
}
