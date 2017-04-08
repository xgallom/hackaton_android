package revolware.com.hackaton_android.data_access.exception;

/**
 * Created by xgallom on 08-Apr-17.
 */

public class InternalErrorException extends ServerException {

    public InternalErrorException() {
    }

    public InternalErrorException(String content) {
        super(content);
    }

    @Override
    public String getMessage() {
        return "Internal server error.";
    }

    @Override
    public int getCode() {
        return INTERNAL_ERROR_CODE;
    }
}