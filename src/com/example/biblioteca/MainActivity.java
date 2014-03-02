package com.example.biblioteca;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    public final static String EXTRA_EMAIL = "com.example.bibliotecaremota.EMAIL";
    public final static String EXTRA_PASSWORD = "com.example.bibliotecaremota.PASSWORD";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /* Called when the user clicks the Log in button */
    public void sendLogin(View view) {
    	Intent intent = new Intent(this, ConnectActivity.class);
    	
    	EditText email = (EditText) findViewById(R.id.email);
    	String emailString = email.getText().toString();
    	intent.putExtra(EXTRA_EMAIL, emailString);
    	
    	EditText password = (EditText) findViewById(R.id.password);
    	String passwordString = password.getText().toString();
    	intent.putExtra(EXTRA_PASSWORD, passwordString);
    	
    	startActivity(intent);
    }
}
