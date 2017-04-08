package revolware.com.hackaton_android.data_access.exception;

/**
 * Created by xgallom on 08-Apr-17.
 */

public class ConnectionFailedException extends ServerException {

    public ConnectionFailedException() {
    }

    public ConnectionFailedException(String content) {
        super(content);
    }

    @Override
    public String getMessage() {
        return "Unable to connect to the Internet.";
    }

}