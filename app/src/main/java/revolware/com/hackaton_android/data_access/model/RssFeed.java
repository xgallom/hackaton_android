package revolware.com.hackaton_android.data_access.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

import static revolware.com.hackaton_android.data_access.model.RssFeed.e_type.e_critical;
import static revolware.com.hackaton_android.data_access.model.RssFeed.e_type.e_notice;
import static revolware.com.hackaton_android.data_access.model.RssFeed.e_type.e_warning;

/**
 * Created by xgallom on 08-Apr-17.
 */

public class RssFeed {
    public enum e_type {
        e_notice,
        e_warning,
        e_critical
    };

    private String title;
    private String desc;
    private String date;
    private String link;
    private String location;
    private e_type type;
    private Double latitude;
    private Double longitude;

    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDesc()
    {
        return desc;
    }
    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getDate()
    {
        return date;
    }
    public void setDate(String date)
    {
        this.date = date;
    }

    public String getLink()
    {
        return link;
    }
    public void setLink(String link)
    {
        this.link = link;
    }

    public String getLocation()
    {
        return location;
    }
    public void setLocation(String location)
    {
        this.location = location;
    }

    public e_type getType()
    {
        return type;
    }
    public void setType(e_type type)
    {
        this.type = type;
    }

    public Double getLatitude()
    {
        return latitude;
    }
    public void setLatitude(Double latitude)
    {
        this.latitude = latitude;
    }

    public Double getLongitude()
    {
        return longitude;
    }
    public void setLongitude(Double longitude)
    {
        this.longitude = longitude;
    }

    public static RssFeed fromJSONObject (JSONObject jsonObject) throws JSONException {
        RssFeed rv = new RssFeed();

        try {
            rv.setTitle(jsonObject.getString("title"));
        } catch(JSONException e) {
            rv.setTitle("");
        }
        try {
        rv.setDesc(jsonObject.getString("description"));
        } catch(JSONException e) {
            rv.setDesc("");
        }
        try {
        rv.setDate(jsonObject.getString("date"));
        } catch(JSONException e) {
            rv.setDate("");
        }
        try {
        rv.setLink(jsonObject.getString("link"));
        } catch(JSONException e) {
            rv.setLink("");
        }
        try {
            rv.setLocation(jsonObject.getString("location"));
        } catch(JSONException e) {
            rv.setLocation("");
        }
        try {
            rv.setType(strToType(jsonObject.getString("type")));
        } catch(JSONException e) {
            rv.setType(e_notice);
        }
        try {
        rv.setLatitude(jsonObject.getDouble("latitude"));
        } catch(JSONException e) {
            rv.setLatitude(0.0);
        }
        try {
        rv.setLongitude(jsonObject.getDouble("longitude"));
        } catch(JSONException e) {
            rv.setLongitude(0.0);
        }

        return rv;
    }

    @Override
    public String toString() {
        String rv = getTitle();

        String[] words = rv.split(" ");

        rv = "";
        for(int n = 0; n < words.length; n++) {
            rv += words[n];
            if((n + 1) % 5 == 0)
                rv += "\n";
            else
                rv += " ";
        }

        return rv;
    }

    public static String typeToStr(final e_type type)
    {
        switch(type)
        {
            default:
            case e_notice:
                return "notice";

            case e_warning:
                return "warning";

            case e_critical:
                return "critical";
        }
    }

    public static e_type strToType(final String type)
    {
        switch(type)
        {
            default:
            case "notice":
                return e_notice;

            case "warning":
                return e_warning;

            case "critical":
                return e_critical;
        }
    }
}
