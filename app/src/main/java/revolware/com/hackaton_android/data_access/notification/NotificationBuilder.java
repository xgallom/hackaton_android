package revolware.com.hackaton_android.data_access.notification;

import android.app.Notification;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import revolware.com.hackaton_android.R;
import revolware.com.hackaton_android.data_access.model.RssFeed;

/**
 * Created by xgallom on 08-Apr-17.
 */

public class NotificationBuilder {
    public static Notification buildFromRssFeed(final Context context, final RssFeed feed)
    {
        int icon;
        int color;

        switch(feed.getType())
        {
            default:
            case e_notice: //#285c80
                icon = R.drawable.ic_notice;
                color = (0x28 << 16) | (0x5c << 8) | 0x80;
                break;

            case e_warning: //#e96941
                icon = R.drawable.ic_warning;
                color = (0xe9 << 16) | (0x69 << 8) | 0x41;
                break;

            case e_critical: //#cd3f3f
                icon = R.drawable.ic_critical;
                color = (0xcd << 16) | (0x3f << 8) | 0x3f;
                break;
        }
        Log.e("DEBUG_GALLO", String.valueOf(color));

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(icon)
//                        .setContentText(feed.getDate() + " " + feed.getLocation())
                        .setColor(color)
                        .setContentTitle(feed.getTitle());

        NotificationCompat.InboxStyle inboxStyle =
                new NotificationCompat.InboxStyle();

        String[] data;

//        data.

//        mBuilder.setStyle(inboxStyle);

        return mBuilder.build();
    }
}
