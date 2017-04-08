package revolware.com.hackaton_android.data_access;

import android.content.Context;

import java.util.List;

import revolware.com.hackaton_android.data_access.exception.ServerException;

/**
 * Created by xgallom on 08-Apr-17.
 */

public abstract class Handler<T> {

    private Context context;

    public Handler(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * This method will return single object which meets criteria specified by parameter
     * @param obj criteria
     * @return single object
     * @throws ServerException
     */
    public T get(T obj) throws ServerException {
        return null;
    }

    /**
     * This method will return list of objects which meets criteria specified by parameter
     * @param obj criteria
     * @return list of object
     * @throws ServerException
     */
    public List<T> getAll(T obj) throws ServerException {
        return null;
    }

    /**
     * This method will create a new object
     * @param obj object to create
     * @throws ServerException
     */
    public void create(T obj) throws ServerException {}

    /**
     * This method will create all objects
     * @param obj list of objects to create
     * @throws ServerException
     */
    public void createAll(List<T> obj) throws ServerException {}
}