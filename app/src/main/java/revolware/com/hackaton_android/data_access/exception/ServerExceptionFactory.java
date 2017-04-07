package revolware.com.hackaton_android.data_access.exception;

import android.content.res.Resources;

/**
 * Created by xgallom on 08-Apr-17.
 */

public class ServerExceptionFactory {

    public ServerException getServerException (int code){
        switch(code){
            case ServerException.ATTEMPT_LIMIT_EXCEEDED_CODE:
                return new AttemptLimitExceededException();
            case ServerException.ACCESS_DENIED_CODE:
                return new AccessDeniedException();
            case ServerException.NOT_FOUND_CODE:
                return new NotFoundException();
            default:
                return new InternalErrorException();
        }
    }

}