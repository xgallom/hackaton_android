package revolware.com.hackaton_android.data_access.exception;

/**
 * Created by xgallom on 08-Apr-17.
 */

public class NotFoundException extends ServerException {

    public NotFoundException(String content) {
        super(content);
    }

    public NotFoundException() {
    }

    @Override
    public String getMessage() {
        return "Not found.";
    }

    public int getCode () {
        return NOT_FOUND_CODE;
    }
}