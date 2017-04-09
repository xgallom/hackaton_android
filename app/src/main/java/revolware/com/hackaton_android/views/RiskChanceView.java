package revolware.com.hackaton_android.views;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import revolware.com.hackaton_android.R;
import revolware.com.hackaton_android.data_access.fonts.AssetedTypeface;
import revolware.com.hackaton_android.data_access.model.RiskChance;

/**
 * Created by xgallom on 09-Apr-17.
 */

public class RiskChanceView extends CustomView<RiskChance> {
    private int iteration;

    public RiskChanceView(LayoutInflater inf)
    {
        super(inf);
        iteration = 0;

    }

    @Override
    public View createView(RiskChance obj) {
        if(obj == null)
            return getInflater().inflate(R.layout.view_risk_chance0, null);

        View rv = getInflater().inflate(iteration++ %2 == 0 ?
            R.layout.view_risk_chance1 : R.layout.view_risk_chance2
            , null
        );

        TextView nameTextView = (TextView) rv.findViewById(R.id.nameTextView);
        TextView percentageTextView = (TextView) rv.findViewById(R.id.percentageTextView);

        nameTextView.setTypeface(AssetedTypeface.getRobotolight());
        percentageTextView.setTypeface(AssetedTypeface.getRobotothin());

        nameTextView.setText(obj.getRisk().getName());
        percentageTextView.setText(String.valueOf(obj.getChance()) + " %");

        ImageView threatIcon = (ImageView) rv.findViewById(R.id.iconImageView);
       // threatIcon.setImageDrawable(drawable.);


        return rv;
    }

}
