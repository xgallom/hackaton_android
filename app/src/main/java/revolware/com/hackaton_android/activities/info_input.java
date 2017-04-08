package revolware.com.hackaton_android.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
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

import revolware.com.hackaton_android.R;

public class info_input extends AppCompatActivity {
    Button fromButt,toButt;
    TextView location;
    int Num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_input);
        Num=0;

        fromButt = (Button) findViewById(R.id.from);
        toButt = (Button) findViewById(R.id.to);

        Calendar c = Calendar.getInstance();

        fromButt.setText("Leave");
        toButt.setText("Arrive");
       // fromButt.setText(c.get(Calendar.DAY_OF_MONTH) + c.get(Calendar.MONTH) + c.get(Calendar.YEAR));
       // toButt.setText(c.get(Calendar.DAY_OF_MONTH) + c.get(Calendar.MONTH) + c.get(Calendar.YEAR));

        fromButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogfragment = new DatePickerDialogTheme1();

                dialogfragment.show(getFragmentManager(), "Theme 1");
                Num=0;

            }
        });

        toButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogfragment = new DatePickerDialogTheme2();
                Num=1;

                dialogfragment.show(getFragmentManager(), "Theme 2");
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

    public static class DatePickerDialogTheme1 extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final java.util.Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            Log.e("date", calendar.toString());

            DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(),
                    AlertDialog.THEME_DEVICE_DEFAULT_DARK, this, year, month, day);

            return datepickerdialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {

            Button fromButt = (Button) getActivity().findViewById(R.id.from);

            fromButt.setText("Leave\n"+day + "." + getMonth(month) + "." + year);



        }}

        public static class DatePickerDialogTheme2 extends DialogFragment implements DatePickerDialog.OnDateSetListener {

            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                final java.util.Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                Log.e("date", calendar.toString());

                DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(),
                        AlertDialog.THEME_DEVICE_DEFAULT_DARK, this, year, month, day);

                return datepickerdialog;
            }

            public void onDateSet(DatePicker view, int year, int month, int day) {

                Calendar cal=Calendar.getInstance();
                SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
                String month_name = month_date.format(cal.getTime());

                Button toButt = (Button) getActivity().findViewById(R.id.to);

                toButt.setText("Arrive\n"+day + "." + getMonth(month) + "." + year);


            }
        }

    public static String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month];
    }
}
