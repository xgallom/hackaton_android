package revolware.com.hackaton_android.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import revolware.com.hackaton_android.R;
import revolware.com.hackaton_android.data_access.fonts.AssetedTypeface;

public class InfoInputActivity extends AppCompatActivity {
    private String location;
    private Date
        dateArrive,
        dateDepart;

    TextView stayIntText;

    Date getDateArrive() {
        return dateArrive;
    }
    void setDateArrive(Date date) {
        dateArrive = date;
    }

    Date getDateDepart() {
        return dateDepart;
    }
    void setDateDepart(Date date) {
        dateDepart = date;
    }

    TextView getStayIntText() {
        return stayIntText;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_input);

        Typeface robotolight = AssetedTypeface.getRobotolight();
        Typeface robotothin = AssetedTypeface.getRobotothin();
        Typeface robotobold = AssetedTypeface.getRobotobold();

        setDateArrive(null);
        setDateDepart(null);

        Button type1 = (Button) findViewById(R.id.buttonType1);
        Button type2 = (Button) findViewById(R.id.buttonType2);
        Button type3 = (Button) findViewById(R.id.buttonType3);
        TextView tx = (TextView)findViewById(R.id.text);
        TextView leave1 = (TextView) findViewById(R.id.departText);
        TextView leave2 = (TextView) findViewById(R.id.leaveDateText);
        TextView arrive1 = (TextView) findViewById(R.id.arriveText);
        TextView arrive2 = (TextView) findViewById(R.id.arriveDateText);
        stayIntText = (TextView) findViewById(R.id.stayIntText);

        TextView locationView = (TextView) findViewById(R.id.location);

        tx.setTypeface(robotolight);
        type1.setTypeface(robotolight);
        type2.setTypeface(robotolight);
        type3.setTypeface(robotolight);
        locationView.setTypeface(robotothin);
        leave1.setTypeface(robotobold);
        leave2.setTypeface(robotolight);
        arrive1.setTypeface(robotobold);
        arrive2.setTypeface(robotolight);
        stayIntText.setTypeface(robotolight);

        Intent i = getIntent();
        location = i.getStringExtra("location");
        locationView.setText(location);


        LinearLayout departButton = (LinearLayout) findViewById(R.id.depart);
        LinearLayout arriveButton = (LinearLayout) findViewById(R.id.arrive);

        departButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialogTheme dialogfragment = new DatePickerDialogTheme();
                dialogfragment.setTarget(R.id.leaveDateText);
                dialogfragment.setIsDateArrive(false);

                dialogfragment.show(getFragmentManager(), "DatePickerDialogTheme");
            }
        });

        arriveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialogTheme dialogfragment = new DatePickerDialogTheme();
                dialogfragment.setTarget(R.id.arriveDateText);
                dialogfragment.setIsDateArrive(true);

                dialogfragment.show(getFragmentManager(), "DatePickerDialogTheme");
            }
        });

        type1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InfoInputActivity.this, LocationActivity.class);
                i.putExtra("location", location);
                startActivity(i);
            }
        });
    }


    public void addView(LinearLayout main){
        LinearLayout linLay = new LinearLayout(this); // layout for text fields
        linLay.setOrientation(LinearLayout.HORIZONTAL);
        linLay.setGravity(Gravity.CENTER_VERTICAL);
        linLay.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1));

        main.addView(linLay);
    }

    public static class DatePickerDialogTheme extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        private int target;
        private boolean
            isDateArrive;

        public int getTarget() {
            return target;
        }
        public void setTarget(int target) {
            this.target = target;
        }

        public boolean getIsDateArrive() {
            return isDateArrive;
        }
        public void setIsDateArrive(boolean isDateArrive) {
            this.isDateArrive = isDateArrive;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final java.util.Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                    R.style.DatepickerDialogTheme, this, year, month, day);

            return datePickerDialog;

        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            InfoInputActivity ii = (InfoInputActivity) getActivity();

            Calendar c = Calendar.getInstance();
            c.set(year, month, day);
            if(getIsDateArrive())
                ii.setDateArrive(c.getTime());
            else
                ii.setDateDepart(c.getTime());

            if(ii.getDateArrive() != null &&
                    ii.getDateDepart() != null)
            {
                if(ii.getDateArrive().getTime() - ii.getDateDepart().getTime() >= 0)
                {
                    ii.getStayIntText().setText("You are staying for " +
                            String.valueOf(
                            (ii.getDateArrive().getTime() - ii.getDateDepart().getTime())
                                    / (1000 * 60 * 60 * 24)
                            ) +
                            " days");

                    TextView v = (TextView) ii.findViewById(target);
                    v.setText(String.valueOf(day) + "." + String.valueOf(month +1) + "." + String.valueOf(year));
                    v.setTextSize(22);
                }
                else
                    Toast.makeText(ii.getApplicationContext(),
                            "You must arrive after leaving.",
                            Toast.LENGTH_SHORT).show();
            }
            else
            {
                TextView v = (TextView) ii.findViewById(target);
                v.setText(String.valueOf(day) + "." + String.valueOf(month +1) + "." + String.valueOf(year));
                v.setTextSize(22);
            }
        }
    }
}
