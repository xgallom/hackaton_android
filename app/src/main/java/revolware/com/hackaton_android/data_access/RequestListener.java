package revolware.com.hackaton_android.data_access;

import revolware.com.hackaton_android.data_access.exception.ServerException;

/**
 * Created by xgallom on 08-Apr-17.
 */

public interface RequestListener {

    /**
     * This method is called when request cannot be finished due to some error.
     * @param e exception which caused the call
     */
    public void onError(ServerException e);

    /**
     * This method is called when the request was finished without experiencing any problems
     * @param obj Custom object, this depends on specific implementation
     */
    public void onSuccess(Object obj);

}