package com.example.splitthebill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Spinner spinTip;
    EditText edtTxtBill, edtTxtPartySize;
    TextView txtTotal, txtTipAmt;
    Button btnSubmit;
    double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hook widgets
        spinTip = findViewById(R.id.spinTip);
        edtTxtBill = findViewById(R.id.numBill);
        edtTxtPartySize = findViewById(R.id.numParty);
        txtTotal = findViewById(R.id.txtTotal);
        btnSubmit = findViewById(R.id.btnSubmit);
        txtTipAmt = findViewById(R.id.txtTipAmt);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calculate stuff
                System.out.println(Integer.parseInt(spinTip.getSelectedItem().toString()));
                total = (Double.parseDouble(edtTxtBill.getText().toString()) * Integer.parseInt(spinTip.getSelectedItem().toString()) / 100 + Double.parseDouble(edtTxtBill.getText().toString())) / Integer.parseInt(edtTxtPartySize.getText().toString()) ;
                //total = Integer.parseInt(edtTxtBill.getText().toString()) * 1.01 / Integer.parseInt(edtTxtPartySize.getText().toString());

                txtTipAmt.setText(String.format("Total Tip is $%.2f", Double.parseDouble(edtTxtBill.getText().toString()) * Integer.parseInt(spinTip.getSelectedItem().toString()) / 100));
                txtTotal.setText(String.format("$%.2f per person",total));
            }
        });

    }
}