package revolware.com.hackaton_android.activities;

import android.content.Intent;
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

import revolware.com.hackaton_android.R;
import revolware.com.hackaton_android.data_access.RequestListener;
import revolware.com.hackaton_android.data_access.country.CountryProvider;
import revolware.com.hackaton_android.data_access.exception.ServerException;

public class Search extends AppCompatActivity {
    SearchView search;
    Button butt;
    private AutoCompleteTextView textView;
    private ArrayAdapter<String> adapter;
    private String[] countries;
    static final String[] data = {
            "Russia",
            "United States"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(android.R.style.Theme);
        setContentView(R.layout.activity_search);

        textView = (AutoCompleteTextView) findViewById(R.id.searchText);

        final AppCompatActivity a = this;

        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_dropdown_item_1line, data);
        textView.setAdapter(adapter);

        new CountryProvider(getApplicationContext()).getAll(null, new RequestListener() {
            @Override
            public void onError(ServerException e) {
                Log.e("DEBUG_GALLO", e.toString());
            }

            @Override
            public void onSuccess(Object obj) {
/*                List<String> list = Country.toString((List<Country>) obj);

                countries = new String[list.size()];
                for(int n = 0; n < list.size(); n++)
                    countries[n] = list.get(n);

                adapter.clear();
                adapter.addAll(countries);
                adapter.notifyDataSetChanged();
  */          }
        });

        textView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
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

            }
        });
    }

    private void accept(String data)
    {
        Intent i = new Intent(Search.this, InfoInput.class);
        i.putExtra("location", data);
        startActivity(i);
    }
}
