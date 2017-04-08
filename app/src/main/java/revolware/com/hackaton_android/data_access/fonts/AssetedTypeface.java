package revolware.com.hackaton_android.data_access.fonts;

import android.content.res.AssetManager;
import android.graphics.Typeface;

/**
 * Created by xgallom on 08-Apr-17.
 */

public class AssetedTypeface {
    private static Typeface
            robotolight = null,
            robotothin  = null,
            robotobold  = null;
    static AssetManager mgr = null;

    public static void setMgr(AssetManager m)
    {
        mgr = m;
    }

    public static Typeface getRobotolight()
    {
        if(robotolight == null)
            robotolight = Typeface.createFromAsset(mgr,  "fonts/Roboto-Light.ttf");

        return robotolight;
    }

    public static Typeface getRobotothin()
    {
        if(robotothin == null)
            robotothin = Typeface.createFromAsset(mgr,  "fonts/Roboto-Thin.ttf");

        return robotothin;
    }

    public static Typeface getRobotobold()
    {
        if(robotobold == null)
            robotobold = Typeface.createFromAsset(mgr,  "fonts/Roboto-Bold.ttf");

        return robotobold;
    }
}
