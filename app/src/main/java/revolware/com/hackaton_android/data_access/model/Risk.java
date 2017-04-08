package revolware.com.hackaton_android.data_access.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xgallom on 08-Apr-17.
 */

public class Risk {
    private String key;
    private String name;

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public static Risk fromJSONObject(JSONObject jsonObject) throws JSONException {
        Risk rv = new Risk();

        try {
            rv.setKey(jsonObject.getString("key"));
        } catch(JSONException e) {
            rv.setKey("");
        }
        try {
            rv.setName(jsonObject.getString("name"));
        } catch(JSONException e) {
            rv.setName("");
        }

        return rv;
    }
}
