package revolware.com.hackaton_android.data_access.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Debug;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import revolware.com.hackaton_android.R;
import revolware.com.hackaton_android.data_access.model.RssFeed;

import static android.graphics.Color.rgb;

/**
 * Created by xgallom on 08-Apr-17.
 */

public class NotificationBuilder {
    private static final String notifGroupKey = "guidana_notification_key";

    public static Notification buildFromRssFeed(final Context context, final RssFeed feed)
    {
        int icon = R.drawable.ic_notice;
        int color = 0;

        switch(feed.getType())
        {
            case e_notice: //#285c80
                icon = R.drawable.ic_notice;
                color = rgb(0x28, 0x5c, 0x80);
                break;

            case e_warning: //#e96941
                icon = R.drawable.ic_warning;
                color = rgb(0xe9, 0x69, 0x41);
                break;

            case e_critical: //#cd3f3f
                icon = R.drawable.ic_critical;
                color = rgb(0xcd, 0x3f, 0x3f);
                break;
        }

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(icon)
                        .setColor(color)
                        .setCategory(Notification.CATEGORY_SERVICE)

                        .setContentInfo(feed.getDate())
                        .setContentText(feed.getLocation())
                        .setContentTitle(feed.getTitle())
                ;

        Intent resultIntent = new Intent(Intent.ACTION_VIEW);
        resultIntent.setData(Uri.parse(feed.getLink()));

        // GROUPING
        mBuilder.setGroup(notifGroupKey);
        mBuilder.setGroupSummary(true);

        // CLICKABLE NOTIFICATION
        PendingIntent pending = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pending);
        mBuilder.setAutoCancel(true);


        // EXPANDABLE NOTIFICATION
        NotificationCompat.BigTextStyle bigTextStyle=
                new NotificationCompat.BigTextStyle();

        //bigTextStyle.setBigContentTitle(mBuilder.mContentTitle);
        bigTextStyle.setBigContentTitle(null);
        bigTextStyle.setSummaryText(mBuilder.mContentText);
        bigTextStyle.bigText(mBuilder.mContentTitle + "\n" + feed.getDesc());
        mBuilder.setStyle(bigTextStyle);

        return mBuilder.build();
    }
}
