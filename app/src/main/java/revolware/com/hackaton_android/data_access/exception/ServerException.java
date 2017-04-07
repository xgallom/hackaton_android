package revolware.com.hackaton_android.data_access.exception;

/**
 * Created by xgallom on 08-Apr-17.
 */

public abstract class ServerException extends Exception {

    public static final int ACCESS_DENIED_CODE = 0b00000000;
    public static final int INTERNAL_ERROR_CODE = 0b00000001;
    public static final int NOT_FOUND_CODE = 0b00000010;
    public static final int CREATED_CODE = 0b00000011;
    public static final int UPDATED_CODE = 0b00000100;
    public static final int DELETED_CODE = 0b00000101;

    public static final int INVALID_CREDENTIALS_CODE = 0b00010000;
    public static final int ATTEMPT_LIMIT_EXCEEDED_CODE = 0b00010001;
    public static final int INVALID_ACCOUNT_CODE = 0b00010010;
    public static final int INVALID_PASSWORD_CODE = 0b00010011;

    private String content;

    public ServerException() {
    }
    public ServerException(String content) {
        this.content = content;
    }

    public int getCode() {
        return -1;
    };
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}