package com.example.catapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final Integer[] ALLOWED_CODES = new Integer[]{100, 101, 102, 200, 201, 202, 204, 206, 207, 300, 301, 302, 303, 304, 305, 307, 308, 400, 401, 402, 403, 404, 405, 406, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 418, 420, 421, 422, 423, 424, 425, 426, 429, 431, 444, 450, 451, 499, 500, 501, 502, 503, 504, 506, 507, 508, 509, 510, 511, 599};
    final String CAT_URL = "https://http.cat/";
    ImageView catImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hook widget
        catImage = findViewById(R.id.catImage);
        Picasso.get().load(getCatUrl(101)).into(catImage);

        // set up spinner
        Spinner codeSpinner = findViewById(R.id.codeSpinner);
        ArrayAdapter<Integer> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, ALLOWED_CODES);
        codeSpinner.setAdapter(spinnerArrayAdapter);

        // set button listener
        Button submit = findViewById(R.id.submitBtn);

        submit.setOnClickListener(e->{
            // get spinner value
            int code = (int) codeSpinner.getSelectedItem();
            // make sure the code is valid
            if(isAllowed(code)){
                 // load the cat image into the view
                Picasso.get().load(getCatUrl(code)).into(catImage);
            }else{
                Toast.makeText(this, "Code not found. Please select another", Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean isAllowed(int code){
        for( int x : ALLOWED_CODES){
            if (code == x) return true;
        }
        return false;
    }

    private String getCatUrl(int code){
        return CAT_URL+code;
    }
}