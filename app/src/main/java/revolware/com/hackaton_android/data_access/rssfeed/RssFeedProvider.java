package revolware.com.hackaton_android.data_access.rssfeed;

import android.content.Context;

import java.util.List;

import revolware.com.hackaton_android.data_access.Provider;
import revolware.com.hackaton_android.data_access.RequestListener;
import revolware.com.hackaton_android.data_access.exception.ConnectionFailedException;
import revolware.com.hackaton_android.data_access.exception.InternalErrorException;
import revolware.com.hackaton_android.data_access.exception.ServerException;
import revolware.com.hackaton_android.data_access.model.RssFeed;
import revolware.com.hackaton_android.data_access.settings.InternetSettings;
import revolware.com.hackaton_android.data_access.util.Internet;

/**
 * Created by xgallom on 08-Apr-17.
 */

public class RssFeedProvider extends Provider<RssFeed> {

    public RssFeedProvider(Context context) {
        super(context);
    }

    @Override
    public void get(final RssFeed obj, final RequestListener listener) {
        final Internet internet = new Internet(getContext());
        final InternetSettings settings = new InternetSettings(getContext());

        new Thread(new Runnable() {
            @Override
            public void run() {
                RssFeed feed = null;
                RssFeedServerHandler serverHandler;

                if (!internet.connectedToWifi() && !internet.connectedToMobile()) {
                    if (listener != null)
                        listener.onError(new ConnectionFailedException("Please check your Internet connection."));
                } else if (settings.hasConnection()) {
                    serverHandler = new RssFeedServerHandler(getContext());
                    try {
                        feed = serverHandler.get(obj);
                    } catch (ServerException e) {
                        if (listener != null)
                            listener.onError(e);
                    }
                }
                if (listener != null) {
                    if (feed != null)
                        listener.onSuccess(feed);
                    else
                        listener.onError(new InternalErrorException("data not available"));
                }
            }
        }).start();
    }

    @Override
    public void getAll(final RssFeed obj, final RequestListener listener)  {
        final Internet internet = new Internet(getContext());
        final InternetSettings settings = new InternetSettings(getContext());

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<RssFeed> feeds = null;
                RssFeedServerHandler serverHandler;

                if (!internet.connectedToWifi() && !internet.connectedToMobile()) {
                    if (listener != null)
                        listener.onError(new ConnectionFailedException("Please check your Internet connection."));
                } else if (settings.hasConnection()) {
                    serverHandler = new RssFeedServerHandler(getContext());
                    try {
                        feeds = serverHandler.getAll(obj);
                    } catch (ServerException e) {
                        if (listener != null)
                            listener.onError(e);
                    }
                }
                if (listener != null) {
                    if (feeds != null)
                        listener.onSuccess(feeds);
                    else
                        listener.onError(new InternalErrorException("data not available"));
                }
            }
        }).start();
    }
}
