package revolware.com.hackaton_android.data_access.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by xgallom on 08-Apr-17.
 */

public class Internet {

    private Context context;

    public Internet (Context context){
        this.context = context;
    }

    public Context getContext() {
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * This method checks wifi connection
     * @return device is or is not connected to WIFI
     */
    public boolean connectedToWifi () {
        if(context == null)
            return false;
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if(info == null)
            return false;
        if(info.getType() == ConnectivityManager.TYPE_WIFI && info.getState() == NetworkInfo.State.CONNECTED)
            return true;
        return false;
    }

    /**
     * This method checks mobile network connection
     * @return device is or is not connected to mobile network
     */
    public boolean connectedToMobile () {
        if(context == null)
            return false;
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if(info == null)
            return false;
        if( (info.getType() == ConnectivityManager.TYPE_MOBILE || info.getType() == ConnectivityManager.TYPE_MOBILE_DUN) && info.getState() == NetworkInfo.State.CONNECTED)
            return true;
        return false;
    }

}