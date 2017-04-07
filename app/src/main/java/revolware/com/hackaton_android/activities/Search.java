package revolware.com.hackaton_android.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.widget.Button;

import revolware.com.hackaton_android.R;

public class Search extends AppCompatActivity {
    SearchView search;
    Button butt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_search);

       // search = (SearchView) this.findViewById(R.id.search);

      /*  butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Search.this, info_input.class);
                i.putExtra( "searchText" ,search.getQuery());
                startActivity(i);
                finish();
            }
        });*/

    }
}
