package revolware.com.hackaton_android.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import revolware.com.hackaton_android.R;

public class InfoInput extends AppCompatActivity {
    LinearLayout departButton, arriveButton;
    TextView location;

    Date dateFrom, dateTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_input);

        LinearLayout departButton = (LinearLayout) findViewById(R.id.depart);
        LinearLayout arriveButton = (LinearLayout) findViewById(R.id.arrive);

        departButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialogTheme dialogfragment = new DatePickerDialogTheme();
                dialogfragment.setTarget(R.id.leaveDateText);

                dialogfragment.show(getFragmentManager(), "DatePickerDialogTheme");
            }
        });

        arriveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialogTheme dialogfragment = new DatePickerDialogTheme();
                dialogfragment.setTarget(R.id.arriveDateText);

                dialogfragment.show(getFragmentManager(), "DatePickerDialogTheme");
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

        public int getTarget() {
            return target;
        }
        public void setTarget(int target) {
            this.target = target;
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
            TextView v = (TextView) getActivity().findViewById(target);

            v.setText(String.valueOf(day) + "." + String.valueOf(month) + "." + String.valueOf(year));
        }
    }
}