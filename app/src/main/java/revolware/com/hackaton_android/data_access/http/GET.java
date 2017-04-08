package revolware.com.hackaton_android.data_access.http;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;

import revolware.com.hackaton_android.data_access.URI;
import revolware.com.hackaton_android.data_access.exception.ServerException;
import revolware.com.hackaton_android.data_access.exception.ServerExceptionFactory;
import revolware.com.hackaton_android.data_access.exception.ServerNotAvailableException;

/**
 * Created by xgallom on 08-Apr-17.
 */

public class GET {

    /**
     * This method provides HTTP connection and returns response body with GET method
     * @param uri URI of the resource
     * @param headers custom headers
     * @return response body
     * @throws ServerException
     */
    public static String getResponseBody(URI uri, HashMap<String, String> headers) throws ServerException {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            //Set up connection
            connection = (HttpURLConnection) uri.getURL().openConnection();
            connection.setRequestProperty("Accept", "application/json");
            for(String key : headers.keySet()) {
                connection.setRequestProperty(key, headers.get(key));
            }
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setUseCaches(false);

            //If request is successful return response body
            //If not this method will throw error based on error code provided in response
            if (connection.getResponseCode() < 400) {
                reader = new BufferedReader( new InputStreamReader( connection.getInputStream() ) );
                String response = new String(); String line;
                while( (line = reader.readLine()) != null )
                    response += line;

                return response;
            }else{
                //Get response body
                reader = new BufferedReader( new InputStreamReader( connection.getErrorStream() ) );
                String response = new String(); String line;
                while( (line = reader.readLine()) != null )
                    response += line;
                //Make JSON out of the response and throw exception
                JSONObject jsonObject = new JSONObject(response);
                ServerExceptionFactory factory = new ServerExceptionFactory();
                ServerException e = factory.getServerException(jsonObject.getInt("status"));
                e.setContent(jsonObject.getString("message"));
                throw e;
            }
        } catch (IOException e) {
            throw new ServerNotAvailableException();
        } catch (JSONException e) {}
        finally {
            if(reader != null)
                try { reader.close(); } catch (IOException e) {}
            if(connection != null)
                connection.disconnect();
        }
        //If something bad happens null will be returned
        return null;
    }


    /**
     * This method provides HTTP connection and returns response header
     * @param uri URI of the resource
     * @param headers custom headers
     * @param header header key
     * @return response body
     * @throws ServerException
     */
    public static String getResponseHeader(URI uri, HashMap<String, String> headers, String header) throws ServerException {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            //Set up connection
            connection = (HttpURLConnection) uri.getURL().openConnection();
            connection.setRequestProperty("Accept", "application/json");
            for(String key : headers.keySet()) {
                connection.setRequestProperty(key, headers.get(key));
            }
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setUseCaches(false);

            //If request is successful return response body
            //If not this method will throw error based on error code provided in response
            if (connection.getResponseCode() < 400) {
                return connection.getHeaderField(header);
            }else{
                //Get response body
                reader = new BufferedReader( new InputStreamReader( connection.getErrorStream() ) );
                String response = new String(); String line;
                while( (line = reader.readLine()) != null )
                    response += line;
                //Make JSON out of the response and throw exception
                JSONObject jsonObject = new JSONObject(response);
                ServerExceptionFactory factory = new ServerExceptionFactory();
                ServerException e = factory.getServerException(jsonObject.getInt("status"));
                e.setContent(jsonObject.getString("message"));
                throw e;
            }
        } catch (IOException e) {
            throw new ServerNotAvailableException();
        } catch (JSONException e) {} finally {
            if(reader != null)
                try { reader.close(); } catch (IOException e) {}
            if(connection != null)
                connection.disconnect();
        }
        //If something bad happens null will be returned
        return null;
    }
}
