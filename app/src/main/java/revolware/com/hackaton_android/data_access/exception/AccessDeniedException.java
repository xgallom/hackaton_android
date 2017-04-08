package revolware.com.hackaton_android.data_access.exception;

/**
 * Created by xgallom on 08-Apr-17.
 */

public class AccessDeniedException extends ServerException {

    public AccessDeniedException(String content) {
        super(content);
    }

    public AccessDeniedException() {

    }

    @Override
    public String getMessage() {
        return "Access denied.";
    }

    public int getCode () {
        return ACCESS_DENIED_CODE;
    }

}