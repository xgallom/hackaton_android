package revolware.com.hackaton_android.data_access;

import android.content.Context;

/**
 * Created by xgallom on 08-Apr-17.
 */

public abstract class Provider<T> {
    private Context context;

    public Provider(Context context){
        this.context = context;
    }

    /**
     * This method will return single object which meets criteria specified by parameter
     * @param obj criteria
     * @return single object
     */
    public void get(T obj, RequestListener listener) {
    }

    /**
     * This method will return list of objects which meets criteria specified by parameter
     * @param obj criteria
     * @return list of object
     */
    public void getAll(T obj, RequestListener listener)  {
    }

    public Context getContext() {
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }
}