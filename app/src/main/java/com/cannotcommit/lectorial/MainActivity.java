package com.cannotcommit.lectorial;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void getFieldInfo(View view) {

        EditText userEditText = (EditText) findViewById(R.id.id_login_edit_text);
        EditText passwordEditText = (EditText) findViewById(R.id.password_edit_text);

        String idText = userEditText.getText().toString();
        String passwordText = passwordEditText.getText().toString();

        SharedPreferences settings = getSharedPreferences("Username", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("username", idText);

        // Commit the edits!
        editor.commit();



        if (confirmLoginInfo(idText, passwordText)){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }


    }

    private boolean confirmLoginInfo(String email, String password){

        Log.v("MainActivity.java", email);
        Log.v("MainActivity.java", password);

        return true;
    }
}
