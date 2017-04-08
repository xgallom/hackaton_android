package revolware.com.hackaton_android.data_access.settings;

import android.content.Context;
import android.content.SharedPreferences;

import revolware.com.hackaton_android.data_access.Server;
import revolware.com.hackaton_android.data_access.exception.ServerNotAvailableException;

/**
 * Created by xgallom on 08-Apr-17.
 */

public class ServerSettings extends Settings {

    public ServerSettings(Context context) {
        super(context);
    }

    /**
     * This method reads preffered server or find available one
     * @return stroed or available server
     * @throws ServerNotAvailableException
     */
    public Server getServer () throws ServerNotAvailableException {
        SharedPreferences preferences = getContext().getSharedPreferences("server", Context.MODE_PRIVATE);
        String serverName = preferences.getString("server", null);
        if(serverName == null){
            SharedPreferences.Editor editor = preferences.edit();
            Server server = Server.getAvailable();
            editor.putString("server", server.name());
            return server;
        }else{
            return Server.valueOf(serverName);
        }
    }

    /**
     * Call this method everytime when the stored server is not available
     * @throws ServerNotAvailableException
     */
    public void switchServer () throws ServerNotAvailableException {
        SharedPreferences preferences = getContext().getSharedPreferences("server", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Server server = Server.getAvailable();
        editor.putString("server", server.name());
    }

}