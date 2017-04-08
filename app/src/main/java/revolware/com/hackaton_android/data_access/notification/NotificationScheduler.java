package revolware.com.hackaton_android.data_access.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by xgallom on 08-Apr-17.
 */

public class NotificationScheduler {
    private static final long MS_DELAY = 1 * 60 * 1000; // 30 min

    public static void startScheduling(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent notifIntent = new Intent("NOTIFICATION_SCHEDULER");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notifIntent, 0);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis() + MS_DELAY, MS_DELAY, pendingIntent);
    }

    public static void stopScheduling(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent notifIntent = new Intent(context, NotificationReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notifIntent, 0);

        alarmManager.cancel(pendingIntent);
    }
}
