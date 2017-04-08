package revolware.com.hackaton_android.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import revolware.com.hackaton_android.R;
import revolware.com.hackaton_android.data_access.RequestListener;
import revolware.com.hackaton_android.data_access.country.CountryProvider;
import revolware.com.hackaton_android.data_access.exception.ServerException;
import revolware.com.hackaton_android.data_access.fonts.AssetedTypeface;
import revolware.com.hackaton_android.data_access.model.Country;

public class SearchActivity extends AppCompatActivity {
    SearchView search;
    Button butt;
    private AutoCompleteTextView textView;
    private ArrayAdapter<String> adapter;
    private List<String> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(android.R.style.Theme);
        setContentView(R.layout.activity_search);

        Typeface robotolight = AssetedTypeface.getRobotolight();
        TextView tx = (TextView) findViewById(R.id.textView2);
        tx.setTypeface(robotolight);


        textView = (AutoCompleteTextView) findViewById(R.id.searchText);

        final AppCompatActivity activity = this;

        countries = new ArrayList<>();
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_dropdown_item_1line);
        textView.setAdapter(adapter);

        new CountryProvider(getApplicationContext()).getAll(null, new RequestListener() {
            @Override
            public void onError(ServerException e) {
                Log.e("DEBUG_GALLO", e.toString());
            }

            @Override
            public void onSuccess(Object obj) {
                countries = Country.toString((List<Country>) obj);

                adapter.clear();
                adapter.addAll(countries);
                adapter.notifyDataSetChanged();
            }
        });

        textView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            accept(textView.getText().toString());
                        }
                    });
                    return true;
                }
                else {
                    return false;
                }
            }
        });

        ImageButton butt = (ImageButton) findViewById(R.id.okButton);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accept(textView.getText().toString());
            }
        });
    }

    public void accept(String data)
    {
        Log.e("DEBUG_GALLO", data == null ? "null" : "not null");
        if(countries.isEmpty() || data.isEmpty())
            return;

        for(int n = 0; n < countries.size(); n++)
            if(data.toLowerCase().equals(countries.get(n).toLowerCase()))
            {
                Intent i = new Intent(SearchActivity.this, InfoInputActivity.class);
                i.putExtra("location", countries.get(n));
                startActivity(i);
                return;
            }

        refuse();
    }

    public void refuse()
    {
        Toast.makeText(getApplicationContext(),
                "Country not found.",
                Toast.LENGTH_SHORT).show();
    }
}
