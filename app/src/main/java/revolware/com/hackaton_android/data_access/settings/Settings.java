package revolware.com.hackaton_android.data_access.settings;

import android.content.Context;

/**
 * Created by xgallom on 08-Apr-17.
 */

public abstract class Settings {

    private Context context;

    public Settings(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }

}