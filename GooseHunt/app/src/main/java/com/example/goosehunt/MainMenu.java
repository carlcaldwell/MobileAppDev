package com.example.goosehunt;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.squareup.picasso.Picasso;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainMenu extends AppCompatActivity {

    Boolean isDev = false;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int ACCESS_FINE_LOCATION = 44;
    TextView conditionTxt, temperatureTxt, errorText;
    ImageView weatherImage;
    private StringRequest stringRequest;
    // Queue for our API request
    private RequestQueue queue;
    String REQUEST_URL = "https://api.weatherapi.com/v1/current.json?key=dcd9a3f09502487395c201931211605&q=";
    String REQUEST_URL_END = "&aqi=no";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        conditionTxt = findViewById(R.id.condition);
        temperatureTxt = findViewById(R.id.temperature);
        errorText = findViewById(R.id.errorText);
        weatherImage = findViewById(R.id.weatherImage);

        Button b = findViewById(R.id.startHuntingBtn);
        b.setOnClickListener(e->{
            startActivity(new Intent(this, MainActivity.class));
        });



        // GET THE USERS LOCATION
        //Initialize fusedLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        //Check to see if you have permission, if not request it. This will ask the user for permission
        //if checkForLocationPermission is true, then permission is already granted, otherwise the method will ask the user for permission
        if (checkForLocationPermission()) {
            getLocation();
        }

        // SEND API REQUEST
        queue = Volley.newRequestQueue(this.getApplicationContext());

    }


    //////////////////////////////////////////////////////
    ///    API REQUEST     //
    //////////////////////////////////////////////////////

    // Get Request For JSONObject
    public void getData(double lat, double lon){

        if(isDev){
            errorText.setText("Dev mode. No Display");
            return;
        }
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        try {
            JSONObject object = new JSONObject();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, REQUEST_URL+lat+","+lon+REQUEST_URL_END, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONObject current = response.getJSONObject("current");
                        int temp = (int)current.getDouble("temp_f");
                        JSONObject condition = current.getJSONObject("condition");
                        String strCondition = condition.getString("text");
                        temperatureTxt.setText(temp+"");
                        conditionTxt.setText(strCondition);
                        Picasso.with(MainMenu.this).load("https:"+condition.getString("icon")).into(weatherImage);
                        return;
                    } catch (JSONException e) {
                        errorText.setText("Error Getting Weather from API");
                    }
                }
            }, error -> {
                System.out.println(error.toString());
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    //////////////////////////////////////////////////////
    ///    LOCATION     //
    //////////////////////////////////////////////////////

    //check for location permission
    //if checkForLocationPermission is true, then permission is already granted, otherwise the method will ask the user for permission
    private boolean checkForLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // calling ActivityCompat#requestPermissions here to request the missing permissions
            // to handle the case where the user grants the permission.
            ActivityCompat.requestPermissions(MainMenu.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
            return false;
        }
        else {
            return true;
        }
    }

    //This method is called after the user accepts or declines the permission request for location.
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case ACCESS_FINE_LOCATION:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                }  else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                    errorText.setText("Location permission is required to use the weather feature.");

                }
                return;
        }
        // Other 'case' lines to check for other
        // permissions this app might request.
    }


    private void getLocation() {

        //Check to see if you have permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // calling ActivityCompat#requestPermissions here to request the missing permissions
            errorText.setText("Permission Denied to access location");
            return;
        }

        //This will check the phones location
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                //Initialize location
                Location location = task.getResult();
                if (location != null) {
                    //Location isn't null
                    try {
                        //initialize the geoCoder
                        Geocoder geocoder = new Geocoder(MainMenu.this, Locale.getDefault());

                        //Initialize address list
                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );

                        //Set latitude on TextView
                        getData(addresses.get(0).getLatitude(), addresses.get(0).getLongitude());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    //location returned null from task.getResult()
                    String errorString = "Location is set to NULL on this device";
                    errorText.setText(errorString);
                }
            }
        });
    }


}