package revolware.com.hackaton_android.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import revolware.com.hackaton_android.R;
import revolware.com.hackaton_android.data_access.fonts.AssetedTypeface;

public class LocationActivity extends AppCompatActivity {
    private Typeface
            robotolight,
            robotothin,
            robotomedium;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        robotolight     = AssetedTypeface.getRobotolight();
        robotothin      = AssetedTypeface.getRobotothin();
        robotomedium    = AssetedTypeface.getRobotoMedium();
        Typeface robotobold
                        = AssetedTypeface.getRobotobold();

        Button finishButton = (Button) findViewById(R.id.finishButton);

        finishButton.setTypeface(robotobold);
    }

}
