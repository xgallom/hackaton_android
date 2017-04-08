package revolware.com.hackaton_android.data_access.country;

import android.content.Context;

import java.util.List;

import revolware.com.hackaton_android.data_access.Provider;
import revolware.com.hackaton_android.data_access.RequestListener;
import revolware.com.hackaton_android.data_access.exception.ConnectionFailedException;
import revolware.com.hackaton_android.data_access.exception.InternalErrorException;
import revolware.com.hackaton_android.data_access.exception.ServerException;
import revolware.com.hackaton_android.data_access.model.Country;
import revolware.com.hackaton_android.data_access.model.RssFeed;
import revolware.com.hackaton_android.data_access.settings.InternetSettings;
import revolware.com.hackaton_android.data_access.util.Internet;

/**
 * Created by xgallom on 08-Apr-17.
 */

public class CountryProvider extends Provider<Country> {

    public CountryProvider(Context context) {
        super(context);
    }

    @Override
    public void get(final Country obj, final RequestListener listener) {
        final Internet internet = new Internet(getContext());
        final InternetSettings settings = new InternetSettings(getContext());

        new Thread(new Runnable() {
            @Override
            public void run() {
                Country country = null;
                CountryServerHandler serverHandler;

                if (!internet.connectedToWifi() && !internet.connectedToMobile()) {
                    if (listener != null)
                        listener.onError(new ConnectionFailedException("Please check your Internet connection."));
                } else if (settings.hasConnection()) {
                    serverHandler = new CountryServerHandler(getContext());
                    try {
                        country = serverHandler.get(obj);
                    } catch (ServerException e) {
                        if (listener != null)
                            listener.onError(e);
                    }
                }
                if (listener != null) {
                    if (country != null)
                        listener.onSuccess(country);
                    else
                        listener.onError(new InternalErrorException("data not available"));
                }
            }
        }).start();
    }

    @Override
    public void getAll(final Country obj, final RequestListener listener)  {
        final Internet internet = new Internet(getContext());
        final InternetSettings settings = new InternetSettings(getContext());

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Country> countries = null;
                CountryServerHandler serverHandler;

                if (!internet.connectedToWifi() && !internet.connectedToMobile()) {
                    if (listener != null)
                        listener.onError(new ConnectionFailedException("Please check your Internet connection."));
                } else if (settings.hasConnection()) {
                    serverHandler = new CountryServerHandler(getContext());
                    try {
                        countries = serverHandler.getAll(obj);
                    } catch (ServerException e) {
                        if (listener != null)
                            listener.onError(e);
                    }
                }
                if (listener != null) {
                    if (countries != null)
                        listener.onSuccess(countries);
                    else
                        listener.onError(new InternalErrorException("data not available"));
                }
            }
        }).start();
    }
}
