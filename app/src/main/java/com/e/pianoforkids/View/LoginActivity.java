package com.e.pianoforkids.View;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.e.pianoforkids.R;

public class LoginActivity extends AppCompatActivity {

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Intent pianoIntent = new Intent(this, MainMenuActivity.class);
        startActivity(pianoIntent);
    }

    public void rigisterNewUser(View view) {

        Context context = getApplicationContext();
        CharSequence text;
        Toast toast = Toast.makeText(context, "Create new user", Toast.LENGTH_SHORT);
        toast.show();
    }
}
