package revolware.com.hackaton_android.data_access.notification;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import java.util.List;
import java.util.Random;

import revolware.com.hackaton_android.R;
import revolware.com.hackaton_android.data_access.RequestListener;
import revolware.com.hackaton_android.data_access.exception.ServerException;
import revolware.com.hackaton_android.data_access.model.RssFeed;
import revolware.com.hackaton_android.data_access.rssfeed.RssFeedProvider;

/**
 * Created by xgallom on 08-Apr-17.
 */

public class NotificationReceiver extends BroadcastReceiver {
    static int n = 0;
    static int mId = 0;


    @Override
    public void onReceive(final Context context, Intent intent) {
        RssFeedProvider provider = new RssFeedProvider(context);

        RssFeed feed = new RssFeed();
        feed.setDate("2017-04-06");

        final List<RssFeed> response;

        provider.getAll(feed, new RequestListener() {
            @Override
            public void onError(ServerException e) {
                Log.e("DEBUG_GALLO", e.toString());
            }

            @Override
            public void onSuccess(Object obj) {
                List<RssFeed> feeds = (List<RssFeed>) obj;

//                NotificationManager mNotificationManager =
//                        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationManagerCompat mNotificationManager =
                        NotificationManagerCompat.from(context);

                for(int n = 0; n < feeds.size(); n++)
                    mNotificationManager.notify(mId++, NotificationBuilder.buildFromRssFeed(context, feeds.get(n)));
            }
        });
    }
}
