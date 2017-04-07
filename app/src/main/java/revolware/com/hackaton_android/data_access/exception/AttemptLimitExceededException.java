package revolware.com.hackaton_android.data_access.exception;

/**
 * Created by xgallom on 08-Apr-17.
 */

public class AttemptLimitExceededException extends ServerException {

    public AttemptLimitExceededException() {
    }

    public AttemptLimitExceededException(String content) {
        super(content);
    }

    @Override
    public String getMessage() {
        return "Unable to login - exceeded maximal number of attempts.";
    }

    @Override
    public int getCode() {
        return 0;
    }
}