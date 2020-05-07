package com.e.pianoforkids;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.e.pianoforkids.View.LoginActivity;
import com.e.pianoforkids.View.MainMenuActivity;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(SCREEN_ORIENTATION_LANDSCAPE);
        Intent pianoIntent = new Intent(this, MainMenuActivity.class);
        startActivity(pianoIntent);
    }
}
