package revolware.com.hackaton_android.data_access.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

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
    private String date;
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

    public String getDate()
    {
        return date;
    }
    public void setDate(String date)
    {
        this.date = date;
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

        rv.setTitle(jsonObject.getString("title"));
        rv.setLocation(jsonObject.getString("location"));
        rv.setDate(jsonObject.getString("date"));
        rv.setLatitude(jsonObject.getDouble("latitude"));
        rv.setLongitude(jsonObject.getDouble("longitude"));

        return rv;
    }

    @Override
    public String toString() {
        return getTitle();
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
