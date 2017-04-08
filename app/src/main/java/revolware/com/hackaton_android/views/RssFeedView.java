package revolware.com.hackaton_android.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import revolware.com.hackaton_android.R;
import revolware.com.hackaton_android.data_access.fonts.AssetedTypeface;
import revolware.com.hackaton_android.data_access.model.RssFeed;

/**
 * Created by xgallom on 09-Apr-17.
 */

public class RssFeedView extends CustomView<RssFeed> {
    public RssFeedView(LayoutInflater inf)
    {
        super(inf);
    }

    @Override
    public View createView(RssFeed obj) {
        View rv = getInflater().inflate(R.layout.view_rss_feed, null);

        TextView descriptionView = (TextView) rv.findViewById(R.id.descriptionView);
        descriptionView.setTypeface(AssetedTypeface.getRobotoMedium());
        descriptionView.setText(obj.getTitle());

        return rv;
    }

}
