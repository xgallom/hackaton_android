package revolware.com.hackaton_android.views;

import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by xgallom on 09-Apr-17.
 */

public abstract class CustomView<T> {
    private LayoutInflater inf;

    LayoutInflater getInflater() {
        return inf;
    }
    void setInflater(LayoutInflater inf) {
        this.inf = inf;
    }

    public CustomView(LayoutInflater inf)
    {
        this.inf = inf;
    }

    public View createView(T obj) {
        return null;
    }
}
