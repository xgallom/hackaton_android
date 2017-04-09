package revolware.com.hackaton_android.data_access.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xgallom on 09-Apr-17.
 */

public class RiskChance {
    private Risk risk;
    private int chance;

    public Risk getRisk() {
        return risk;
    }
    public void setRisk(Risk risk) {
        this.risk = risk;
    }

    public int getChance() {
        return chance;
    }
    public void setChance(int chance) {
        this.chance = chance;
    }

    public static RiskChance fromJSONObject(JSONObject jsonObject) throws JSONException {
        RiskChance rv = new RiskChance();

        try {
            rv.setRisk(Risk.fromJSONObject(jsonObject.getJSONObject("risk")));
        } catch(JSONException e) {
            rv.setRisk(new Risk());
        }
        try {
            rv.setChance(jsonObject.getInt("chance"));
        } catch(JSONException e) {
            rv.setChance(0);
        }

        return rv;
    }
}
