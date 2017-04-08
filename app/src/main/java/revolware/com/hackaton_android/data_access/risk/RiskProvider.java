package revolware.com.hackaton_android.data_access.risk;

import android.content.Context;

import java.util.List;

import revolware.com.hackaton_android.data_access.Provider;
import revolware.com.hackaton_android.data_access.RequestListener;
import revolware.com.hackaton_android.data_access.exception.ConnectionFailedException;
import revolware.com.hackaton_android.data_access.exception.InternalErrorException;
import revolware.com.hackaton_android.data_access.exception.ServerException;
import revolware.com.hackaton_android.data_access.model.Risk;
import revolware.com.hackaton_android.data_access.settings.InternetSettings;
import revolware.com.hackaton_android.data_access.util.Internet;

/**
 * Created by xgallom on 08-Apr-17.
 */

public class RiskProvider extends Provider<Risk> {

    public RiskProvider(Context context) {
        super(context);
    }

    @Override
    public void get(final Risk obj, final RequestListener listener) {
        final Internet internet = new Internet(getContext());
        final InternetSettings settings = new InternetSettings(getContext());

        new Thread(new Runnable() {
            @Override
            public void run() {
                Risk risk = null;
                RiskServerHandler serverHandler;

                if (!internet.connectedToWifi() && !internet.connectedToMobile()) {
                    if (listener != null)
                        listener.onError(new ConnectionFailedException("Please check your Internet connection."));
                } else if (settings.hasConnection()) {
                    serverHandler = new RiskServerHandler(getContext());
                    try {
                        risk = serverHandler.get(obj);
                    } catch (ServerException e) {
                        if (listener != null)
                            listener.onError(e);
                    }
                }
                if (listener != null) {
                    if (risk != null)
                        listener.onSuccess(risk);
                    else
                        listener.onError(new InternalErrorException("data not available"));
                }
            }
        }).start();
    }

    @Override
    public void getAll(final Risk obj, final RequestListener listener)  {
        final Internet internet = new Internet(getContext());
        final InternetSettings settings = new InternetSettings(getContext());

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Risk> risks = null;
                RiskServerHandler serverHandler;

                if (!internet.connectedToWifi() && !internet.connectedToMobile()) {
                    if (listener != null)
                        listener.onError(new ConnectionFailedException("Please check your Internet connection."));
                } else if (settings.hasConnection()) {
                    serverHandler = new RiskServerHandler(getContext());
                    try {
                        risks = serverHandler.getAll(obj);
                    } catch (ServerException e) {
                        if (listener != null)
                            listener.onError(e);
                    }
                }
                if (listener != null) {
                    if (risks != null)
                        listener.onSuccess(risks);
                    else
                        listener.onError(new InternalErrorException("data not available"));
                }
            }
        }).start();
    }
}
