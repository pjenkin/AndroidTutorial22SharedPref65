package com.example.sharedpref65;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // variables for use in SharedPreferences
    EditText usernameInput;
    EditText passwordInput;
    TextView resultText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Setup shared preferences variables - these are to be saved to sharedpreferences file on device
        usernameInput = findViewById(R.id.username_input);
        passwordInput =  findViewById(R.id.input_password);
        resultText = (TextView) findViewById(R.id.result_text);
    }

    // Save the user's login info on click
    public void saveButtonClicked(View view)
    {
        saveLoginInfo();
    }

    // Display the user's login info on click
    public void displayButtonClicked(View view)
    {
        displayLoginInfo();
    }


    // Save user's login info to SharedPreferences
    public void saveLoginInfo()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        // Context.MODE_PRIVATE - for this app only
        // Enter values in SharedPreferences db on device
        SharedPreferences.Editor editor = sharedPreferences.edit();             // start working on db
        editor.putString("username", usernameInput.getText().toString());        // key/value pair
        editor.putString("password", passwordInput.getText().toString());        // key/value pair
        editor.apply();                                                         // update db

        Toast.makeText(this, "Info saved!", Toast.LENGTH_LONG).show();
    }

    // Print out the the saved user info (even after restart of device)
    public void displayLoginInfo()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        // Context.MODE_PRIVATE - for this app only
        String username = sharedPreferences.getString("username","");      // fetch username using key
        String password = sharedPreferences.getString("password","");      // fetch username using key
        // Supply no default (2nd parameter)
        resultText.setText(username + " " + password);      // display the login credentials in a text view

        // This will display previously saved information even after a restart of the device.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
