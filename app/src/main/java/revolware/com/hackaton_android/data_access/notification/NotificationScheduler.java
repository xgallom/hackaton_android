package revolware.com.hackaton_android.data_access.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;

/**
 * Created by xgallom on 08-Apr-17.
 */

public class NotificationScheduler extends AsyncTask<Object, Void, Void> {
    private static final long MS_DELAY = 1 * 60 * 1000; // 30 min

    private NotificationListener listener;

    public NotificationScheduler(NotificationListener listener) {
        super();

        this.listener = listener;
    }


    protected Void doInBackground(Object... params) {
        if(((Boolean) params[1]) == true)
            startScheduling((Context) params[0]);
        else
            stopScheduling((Context) params[0]);

        return null;
    }

    protected void onPostExecute(Void v) {
        listener.onNotificationSchedulerFinished();
    }

    public static void startScheduling(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent notifIntent = new Intent("NOTIFICATION_SCHEDULER");

        NotificationReceiver receiver = new NotificationReceiver();

        IntentFilter filterLock  = new IntentFilter("android.intent.action.SCREEN_ON");
        context.registerReceiver(receiver, filterLock);
//        filterNet   = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
//        getApplicationContext().registerReceiver(receiver, filterNet);

//        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notifIntent, 0);

//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis() + MS_DELAY, MS_DELAY, pendingIntent);
    }

    public static void stopScheduling(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent notifIntent = new Intent(context, NotificationReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notifIntent, 0);

        alarmManager.cancel(pendingIntent);
    }
}
