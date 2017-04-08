package revolware.com.hackaton_android.data_access.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xgallom on 08-Apr-17.
 */

public class Country {
    private String country;

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public static Country fromJSONObject (JSONObject jsonObject) throws JSONException {
        Country rv = new Country();

        try {
            rv.setCountry(jsonObject.getString("country"));
        } catch(JSONException e) {
            rv.setCountry("");
        }

        return rv;
    }

    @Override
    public String toString() {
        return getCountry();
    }

    public static List<String> toString(final List<Country> countries)
    {
        List<String> rv = new ArrayList<String>();

        for(int n = 0; n < countries.size(); n++)
            rv.add(countries.get(n).toString());

        return rv;
    }

}
