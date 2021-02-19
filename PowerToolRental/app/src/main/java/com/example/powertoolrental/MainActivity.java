package com.example.powertoolrental;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hook widgets
        Button btnCalc = findViewById(R.id.btnCompute);
        RadioButton radPowerWasher = findViewById(R.id.radPowerWasher);
        RadioButton radTiller = findViewById(R.id.radTiller);
        EditText edtDays = findViewById(R.id.editTextDays);
        final double costTiller = 68.99, costPowerWasher = 55.99;
        TextView txtTotal = findViewById(R.id.txtTotalCost);
        DecimalFormat tenth = new DecimalFormat("#.##");


        // display the icon in the action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);




        btnCalc.setOnClickListener(new View.OnClickListener(){

            int daysRequested;

            @Override
            public void onClick(View v) {

                // get days from textedit

                try{
                    daysRequested = Integer.parseInt(edtDays.getText().toString());
                }catch(NumberFormatException e){
                    Toast.makeText(MainActivity.this, "Please enter a valid number of days", Toast.LENGTH_LONG).show();
                }


                if(daysRequested > 7 || daysRequested <= 0){
                    // requested too many days. display error message
                    Toast.makeText(MainActivity.this, "Please enter 7 days or less", Toast.LENGTH_LONG).show();
                    txtTotal.setText("");
                }else{
                    // calculate cost
                    if(radPowerWasher.isChecked()){
                        // calculate for power washer
                        txtTotal.setText("The total cost is $"+ tenth.format(costPowerWasher*daysRequested));
                    }else if(radTiller.isChecked()){
                        // calculate for tiller
                        txtTotal.setText("The total cost is $"+ tenth.format(costTiller*daysRequested));
                    }else{
                        // no tool selected. show error
                        Toast.makeText(MainActivity.this, "Please select a power tool", Toast.LENGTH_LONG).show();
                        txtTotal.setText("");
                    }
                    // update text
                }
            }
        });

    }
}