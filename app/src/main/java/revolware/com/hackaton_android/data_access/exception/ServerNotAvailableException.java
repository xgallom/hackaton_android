package revolware.com.hackaton_android.data_access.exception;

/**
 * Created by xgallom on 08-Apr-17.
 */

public class ServerNotAvailableException extends ServerException {

    public ServerNotAvailableException() {
    }

    public ServerNotAvailableException(String content) {
        super(content);
    }

    @Override
    public String getMessage() {
        return "server not available.";
    }
}