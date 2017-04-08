package revolware.com.hackaton_android.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import revolware.com.hackaton_android.R;

public class info_input extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_input);


        RelativeLayout ln = (RelativeLayout) this.findViewById(R.id.main);

        LinearLayout linLay = new LinearLayout(this);
        linLay.setOrientation(LinearLayout.VERTICAL);
        linLay.setGravity(Gravity.CENTER_VERTICAL);
        linLay.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        ln.addView(linLay);

        DatePicker startTime = new DatePicker(this);
        DatePicker endTime = new DatePicker(this);
        linLay.addView(startTime);
        linLay.addView(endTime);




    }

    public void addView(LinearLayout main){

        LinearLayout linLay = new LinearLayout(this); // layout for text fields
        linLay.setOrientation(LinearLayout.HORIZONTAL);
        linLay.setGravity(Gravity.CENTER_VERTICAL);
        linLay.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1));

        main.addView(linLay);
    }
}
