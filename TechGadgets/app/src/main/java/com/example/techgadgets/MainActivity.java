package com.example.techgadgets;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] items = {"144Hz Monitor", "HDMI Switcher", "Bluetooth Headphones", "RGB Mouse", "USB Cable"};
        ArrayAdapter<String> aa = new ArrayAdapter<>(this, R.layout.activity_main, R.id.gadget, items);

        setListAdapter(aa);

        // todo - remove this later. only for reference later
//        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://google.com")));

    }

    protected void onListItemClick(ListView lv, View v, int position, long  id){
        switch(position){
            case 0:
                // monitor
                startActivity(new Intent(MainActivity.this, monitor.class));
                break;
            case 1:
                // hdmi switcher
                startActivity(new Intent(MainActivity.this, hdmi.class));
                break;
            case 2:
                // headphones
                startActivity(new Intent(MainActivity.this, headphones.class));
                break;
            case 3:
                // mouse
                startActivity(new Intent(MainActivity.this, mouse.class));
                break;
            case 4:
                // usb extender
                startActivity(new Intent(MainActivity.this, usb.class));
                break;
        }
    }

}