package revolware.com.hackaton_android.data_access;

import java.io.IOException;
import java.net.HttpURLConnection;

import revolware.com.hackaton_android.data_access.exception.ServerNotAvailableException;

/**
 * Created by xgallom on 08-Apr-17.
 */

public enum Server {
    A("http://10.18.1.19:4000/api/");

    //local server fo testing purposes only!
    //,B("http://10.10.10.22:8080/api/");

    private URI uri;

    Server(String path) {
        this.uri = new URI(path);
    }

    public URI getUri() {
        return uri;
    }

    public boolean isAvailable () {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) uri.getURL().openConnection();
            connection.setConnectTimeout(2000);
            connection.connect();
            connection.disconnect();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static Server getAvailable() throws ServerNotAvailableException {
        if(A.isAvailable())
            return A;
        //if(B.isAvailable())
        //    return B;
        throw new ServerNotAvailableException();
    }
}