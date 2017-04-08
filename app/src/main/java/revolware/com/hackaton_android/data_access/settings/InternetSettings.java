package revolware.com.hackaton_android.data_access.settings;

import android.content.Context;

import revolware.com.hackaton_android.data_access.util.Internet;

/**
 * Created by xgallom on 08-Apr-17.
 */

public class InternetSettings extends Settings {

    public InternetSettings(Context context) {
        super(context);
    }

    /**
     * This method defines when to download data about user from server
     * @return whether download or not
     */
    public boolean hasConnection () {
        Internet internet = new Internet(getContext());
        if(internet.connectedToWifi())
            return true;
        if(internet.connectedToMobile())
            return false;
        return false;
    }

}