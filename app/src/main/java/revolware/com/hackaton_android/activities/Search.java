package revolware.com.hackaton_android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import revolware.com.hackaton_android.R;

public class Search extends AppCompatActivity {
    SearchView search;
    Button butt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final EditText editText = (EditText) findViewById(R.id.editText2);
        ImageButton butt = (ImageButton) findViewById(R.id.imageButton);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Search.this, info_input.class);
                i.putExtra("location" ,editText.getText());
                startActivity(i);
                finish();
            }
        });
    }
}
