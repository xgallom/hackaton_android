package revolware.com.hackaton_android.data_access.notification;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Random;

import revolware.com.hackaton_android.R;
import revolware.com.hackaton_android.data_access.model.RssFeed;

/**
 * Created by xgallom on 08-Apr-17.
 */

public class NotificationReceiver extends BroadcastReceiver {
    static int n = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        RssFeed feed = new RssFeed();
        feed.setTitle("Improvised explosive causes flight delay");
        feed.setLocation("Canada - Toronto International Airport");
        feed.setDate("2017-04-06");
        n++;
        n = n % 3;
        final RssFeed.e_type type = n == 0 ? RssFeed.e_type.e_notice : (n == 1 ? RssFeed.e_type.e_warning : RssFeed.e_type.e_critical);
        Log.e("DEBUG_GALLO", String.valueOf(n));
        feed.setType(RssFeed.strToType("warning"));

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(0, NotificationBuilder.buildFromRssFeed(context, feed));
    }
}
