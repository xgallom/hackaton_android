package revolware.com.hackaton_android.data_access.rssfeed;

import android.content.Context;
import android.os.Debug;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import revolware.com.hackaton_android.data_access.Handler;
import revolware.com.hackaton_android.data_access.WebApiRefs;
import revolware.com.hackaton_android.data_access.exception.InternalErrorException;
import revolware.com.hackaton_android.data_access.exception.ServerException;
import revolware.com.hackaton_android.data_access.exception.ServerNotAvailableException;
import revolware.com.hackaton_android.data_access.http.GET;
import revolware.com.hackaton_android.data_access.model.RssFeed;
import revolware.com.hackaton_android.data_access.settings.ServerSettings;

/**
 * Created by xgallom on 08-Apr-17.
 */

public class RssFeedServerHandler extends Handler<RssFeed> {
    public RssFeedServerHandler(Context context) {
        super(context);
    }

    @Override
    public RssFeed get(RssFeed obj) throws ServerException {
        ServerSettings settings = new ServerSettings(getContext());

        HashMap< String, String > headers = new HashMap<>();
        String response = null;
        try{
            response = GET.getResponseBody(WebApiRefs.RSS_FEED.getURI(settings.getServer()), headers);
        } catch (ServerNotAvailableException e) {
            settings.switchServer();
            response = GET.getResponseBody(WebApiRefs.RSS_FEED.getURI(settings.getServer()), headers);
        }

        try {
            JSONObject jsonObject = new JSONObject(response);
            return RssFeed.fromJSONObject(jsonObject);
        } catch (JSONException e) {
            throw new InternalErrorException("JSON problem.");
        } catch (NullPointerException e){

        }

        return null;
    }

    @Override
    public List<RssFeed> getAll(RssFeed obj) throws ServerException {
        ServerSettings settings = new ServerSettings(getContext());

        HashMap< String, String > headers = new HashMap<>();
        String response = null;
        try{
            response = GET.getResponseBody(WebApiRefs.RSS_FEED.getURI(settings.getServer()).resolveTemplate("dateFrom", obj.getDate()), headers);
        } catch (ServerNotAvailableException e) {
            settings.switchServer();
            response = GET.getResponseBody(WebApiRefs.RSS_FEED.getURI(settings.getServer()).resolveTemplate("dateFrom", obj.getDate()), headers);
        }

        try {
            List<RssFeed> retval = new ArrayList<>();

            JSONArray jsonArray = new JSONArray(response);

            for(int i = 0; i < jsonArray.length(); i++) {
                retval.add(RssFeed.fromJSONObject(jsonArray.getJSONObject(i)));
            }

            return retval;
        } catch (JSONException e) {
            throw new InternalErrorException("JSON problem.");
        } catch (NullPointerException e){

        }

        return null;
    }

}

